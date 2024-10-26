import { Component, OnInit } from '@angular/core';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { PlanApplication } from 'src/app/models/planApplication.model';
import { PlanApplicationformService } from 'src/app/services/planapplicationform.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-managerviewapplicationform',
  templateUrl: './managerviewapplicationform.component.html',
  styleUrls: ['./managerviewapplicationform.component.css']
})
export class ManagerviewapplicationformComponent implements OnInit {
  planApplications: PlanApplication[] = [];
  displayedApplications: PlanApplication[] = [];
  filters = {
    applicantName: '',
    planName: '',
    appliedAmount: '',
    applicationDate: '',
    status: ''
  };
  currentPage = 0;
  itemsPerPage = 5;
  totalPages = 0;
  sortColumn = '';
  sortOrder = 1; // 1 for ascending, -1 for descending
  newStatus: string;

  constructor(
    private planApplicationformService: PlanApplicationformService,
    private sanitizer: DomSanitizer
  ) {}

  ngOnInit(): void {
    this.getAllPlanApplications();
  }

  getAllPlanApplications() {
    this.planApplicationformService.getAllPlanApplication().subscribe((data) => {
      this.planApplications = data;
      this.updateDisplayedApplications();
      console.log('getall in comp ', this.planApplications);
    });
  }

  updateDisplayedApplications() {
    this.totalPages = Math.ceil(this.planApplications.length / this.itemsPerPage);
    this.displayedApplications = this.planApplications
      .filter(plan => this.filterConditions(plan))
      .sort((a, b) => this.sortData(a, b))
      .slice(this.currentPage * this.itemsPerPage, (this.currentPage + 1) * this.itemsPerPage);
  }

  filterConditions(plan: PlanApplication) {
    return (
      plan.user.username.toLowerCase().includes(this.filters.applicantName.toLowerCase()) &&
      plan.savingsPlan.name.toLowerCase().includes(this.filters.planName.toLowerCase()) &&
      plan.appliedAmount.toString().toLowerCase().includes(this.filters.appliedAmount.toLowerCase()) &&
      plan.applicationDate.toLowerCase().includes(this.filters.applicationDate.toLowerCase()) &&
      plan.status.toLowerCase().includes(this.filters.status.toLowerCase())
    );
  }

  sortData(a: PlanApplication, b: PlanApplication) {
    if (!this.sortColumn) return 0;
    const isAsc = this.sortOrder === 1;
    switch (this.sortColumn) {
      case 'applicantName':
        return this.compare(a.user.username, b.user.username, isAsc);
      case 'planName':
        return this.compare(a.savingsPlan.name, b.savingsPlan.name, isAsc);
      case 'appliedAmount':
        return this.compare(a.appliedAmount, b.appliedAmount, isAsc);
      case 'applicationDate':
        return this.compare(a.applicationDate, b.applicationDate, isAsc);
      case 'remarks':
        return this.compare(a.remarks, b.remarks, isAsc);
      case 'status':
        return this.compare(a.status, b.status, isAsc);
      default:
        return 0;
    }
  }

  compare(a: any, b: any, isAsc: boolean) {
    return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
  }

  sortTable(column: string) {
    if (this.sortColumn === column) {
      this.sortOrder = -this.sortOrder;
    } else {
      this.sortColumn = column;
      this.sortOrder = 1;
    }
    this.updateDisplayedApplications();
  }

  filterTable() {
    this.currentPage = 0; // reset to first page on filter
    this.updateDisplayedApplications();
  }

  prevPage() {
    if (this.currentPage > 0) {
      this.currentPage--;
      this.updateDisplayedApplications();
    }
  }

  nextPage() {
    if (this.currentPage < this.totalPages - 1) {
      this.currentPage++;
      this.updateDisplayedApplications();
    }
  }

  approve(planApplication: PlanApplication) {
    Swal.fire({
      title: 'Are you sure?',
      text: "You are about to approve this application!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, approve it!'
    }).then((result) => {
      if (result.isConfirmed) {
        planApplication.status = 'Approved';
        this.planApplicationformService.updatePlanApplication(planApplication.planApplicationId, planApplication).subscribe(() => {
          this.getAllPlanApplications();
          this.newStatus = planApplication.status;
          Swal.fire(
            'Approved!',
            'The application has been approved.',
            'success'
          );
        });
      }
    });
  }

  reject(planApplication: PlanApplication) {
    Swal.fire({
      title: 'Are you sure?',
      text: "You are about to reject this application!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, reject it!'
    }).then((result) => {
      if (result.isConfirmed) {
        planApplication.status = 'Rejected';
        this.planApplicationformService.updatePlanApplication(planApplication.planApplicationId, planApplication).subscribe(() => {
          this.getAllPlanApplications();
          this.newStatus = planApplication.status;
          Swal.fire(
            'Rejected!',
            'The application has been rejected.',
            'success'
          );
        });
      }
    });
  }

  getDocumentUrl(base64String: string): SafeResourceUrl {
    const url = `data:application/pdf;base64,${base64String}`;
    return this.sanitizer.bypassSecurityTrustResourceUrl(url);
  }

  getSortClass(column: string): string {
    if (this.sortColumn !== column) return '';
    return this.sortOrder === 1 ? 'asc' : 'desc';
  }
}

 
 
 