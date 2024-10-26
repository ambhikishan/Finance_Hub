import { Component, OnInit } from '@angular/core';
import { Enquiry } from 'src/app/models/enquiry.model';
import { EnquiryService } from 'src/app/services/enquiry.service';
import Swal from 'sweetalert2';
 
@Component({
  selector: 'app-managerviewenquiries',
  templateUrl: './managerviewenquiries.component.html',
  styleUrls: ['./managerviewenquiries.component.css']
})
export class ManagerviewenquiriesComponent implements OnInit {
  enquiries: Enquiry[] = [];
  paginatedEnquiries: Enquiry[] = [];
  currentPage = 1;
  itemsPerPage = 4;
  totalPages: number;
  pages: number[];
 
  constructor(private enquiryService: EnquiryService) { }
 
  ngOnInit(): void {
    this.getAllEnquiries();
  }
 
  getAllEnquiries(): void {
    this.enquiryService.getAllEnquiries().subscribe((data: any) => {
      this.enquiries = data;
      this.updatePaginatedEnquiries();
    });
  }
 
  filterByMessage(fMessage: string): void {
    if (fMessage === "") {
      this.getAllEnquiries();
    } else {
      this.enquiryService.getAllEnquiries().subscribe((data: any) => {
        this.enquiries = data.filter(e => e.message.includes(fMessage));
        this.updatePaginatedEnquiries();
      });
    }
  }
 
  showUserProfile(user: any): void {
    Swal.fire({
      title: 'User Profile',
      html: `
        <p>User Name: ${user.username}</p>
        <p>Email: ${user.email}</p>
        <p>Mobile: ${user.mobileNumber}</p>
      `,
      icon: 'info',
      confirmButtonText: 'Close'
    });
  }
 
  setPage(page: number): void {
    if (page < 1 || page > this.totalPages) return;
    this.currentPage = page;
    this.updatePaginatedEnquiries();
  }
 
  updatePaginatedEnquiries(): void {
    this.totalPages = Math.ceil(this.enquiries.length / this.itemsPerPage);
    this.pages = Array.from({ length: this.totalPages }, (_, i) => i + 1);
    const start = (this.currentPage - 1) * this.itemsPerPage;
    const end = start + this.itemsPerPage;
    this.paginatedEnquiries = this.enquiries.slice(start, end);
  }
}
 