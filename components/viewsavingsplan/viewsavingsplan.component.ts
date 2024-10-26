import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { SavingsPlan } from 'src/app/models/savingsplan.model';
import { SavingsPlanService } from 'src/app/services/savingsplan.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-viewsavingsplan',
  templateUrl: './viewsavingsplan.component.html',
  styleUrls: ['./viewsavingsplan.component.css']
})
export class ViewsavingsplanComponent implements OnInit {

  savingsPlanId: number;
  savingsPlan: SavingsPlan = {};
  savingsPlans: SavingsPlan[] = [];
  showMessage: Boolean = false;
  p:number=1;

  constructor(private service: SavingsPlanService, private activatedRoute: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.savingsPlanId = parseInt(this.activatedRoute.snapshot.params['id']);
    this.service.getSavingsPlanById(this.savingsPlanId).subscribe((data) => {
      this.savingsPlan = data;
      console.log(data);
    });
    this.getAllSavingsPlans();
  }

  public getAllSavingsPlans() {
    this.service.getAllSavingsPlans().subscribe((data) => {
      this.savingsPlans = data;
    });
  }

  public deleteSavingsPlan(id: number) {
    this.service.deleteSavingsPlan(id).subscribe(
      (data) => {
        this.getAllSavingsPlans();
        Swal.fire(
          'Deleted!',
          'Your savings plan has been deleted.',
          'success'
        );
      },
      (error) => {
        Swal.fire(
          'Error!',
          "Can't be deleted. The user has subscribed to the plan.",
          'error'
        );
      }
    );
  }
  public confirmDelete(savingsPlan: any) {
    if (this.showMessage) {
      Swal.fire(
        'Error!',
        "Can't be deleted. The user has subscribed to the plan.",
        'error'
      );
      this.showMessage=true
    } else {
      Swal.fire({
        title: 'Are you sure?',
        text: "You won't be able to revert this!",
icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, delete it!'
      }).then((result) => {
        if (result.isConfirmed) {
          this.deleteSavingsPlan(savingsPlan.savingsPlanId);
        }
      });
   }
  }
}