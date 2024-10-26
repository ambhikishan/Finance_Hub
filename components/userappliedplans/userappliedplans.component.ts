import { Component, OnInit, ViewChild } from '@angular/core';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';
import { PlanApplication } from 'src/app/models/planApplication.model';
import { AuthService } from 'src/app/services/auth.service';
import { PlanApplicationformService } from 'src/app/services/planapplicationform.service';
import Swal from 'sweetalert2';
 
@Component({
  selector: 'app-userappliedplans',
  templateUrl: './userappliedplans.component.html',
  styleUrls: ['./userappliedplans.component.css']
})
export class UserappliedplansComponent implements OnInit {
  planApplications: PlanApplication[] = [];
  displayedApplications: PlanApplication[] = [];
  userId: number;
  planApplicationId: number;
  searchText: string = '';
  filters = {
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
 
  isEditPopupOpen = false;
  isDeletePopupOpen = false;
  selectedPlan: PlanApplication = null;
  planToDelete = null;
 
  constructor(
    private planApplicationformService: PlanApplicationformService,
    private authService: AuthService,
    private activatedRoute: ActivatedRoute,
    private sanitizer: DomSanitizer
  ) {}
 
  ngOnInit(): void {
    this.userId = this.authService.userId;
    console.log("user id ", this.userId);
    this.getAllPlanApplicationsByUserId(this.userId);
  }
 
  getAllPlanApplicationsByUserId(userId: number) {
    this.planApplicationformService.getAppliedPlans(userId).subscribe((data: any) => {
      this.planApplications = data;
      this.updateDisplayedApplications();
      console.log("get all ", this.planApplications);
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
 
  openEditPopup(planApplicationId: number, previousPlan: PlanApplication) {
    this.planApplicationId = planApplicationId;
    this.selectedPlan = previousPlan;
    Swal.fire({
      title: 'Edit Plan Application',
      html: `<input id="planName" class="swal2-input" placeholder="Plan Name" value="${this.selectedPlan.savingsPlan.name}">
             <input id="appliedAmount" class="swal2-input" placeholder="Applied Amount" value="${this.selectedPlan.appliedAmount}">
             <input id="applicationDate" class="swal2-input" placeholder="Application Date" value="${this.selectedPlan.applicationDate}">
             <input id="status" class="swal2-input" placeholder="Status" value="${this.selectedPlan.status}">`,
      focusConfirm: false,
      preConfirm: () => {
        this.selectedPlan.savingsPlan.name = (document.getElementById('planName') as HTMLInputElement).value;
        this.selectedPlan.appliedAmount = +(document.getElementById('appliedAmount') as HTMLInputElement).value;
        this.selectedPlan.applicationDate = (document.getElementById('applicationDate') as HTMLInputElement).value;
        this.selectedPlan.status = (document.getElementById('status') as HTMLInputElement).value;
        this.saveChanges();
      }
    });
  }

 
  closeEditPopup() {
    this.isEditPopupOpen = false;
    this.selectedPlan = null;
  }
 
  saveChanges() {
    this.planApplicationformService.updatePlanApplication(this.planApplicationId, this.selectedPlan).subscribe(() => {
      this.getAllPlanApplicationsByUserId(this.userId);
      this.closeEditPopup();
    });
  }
 
  confirmDelete(planApplicationId: number) {
    this.planApplicationId = planApplicationId;
    Swal.fire({
      title: 'Are you sure?',
      text: 'You won\'t be able to revert this!',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
      if (result.isConfirmed) {
        this.deletePlan();
      }
    });
  }

 
  closeDeletePopup() {
    this.isDeletePopupOpen = false;
    this.planToDelete = null;
  }
 
  deletePlan() {
    this.planApplicationformService.deletePlanApplication(this.planApplicationId).subscribe(() => {
      this.getAllPlanApplicationsByUserId(this.userId);
      Swal.fire(
        'Deleted!',
        'Your plan application has been deleted.',
        'success'
      );
    });
  }

 
  closePopups() {
    this.closeEditPopup();
    this.closeDeletePopup();
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
   
 
