import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SavingsPlan } from 'src/app/models/savingsplan.model';
import { SavingsPlanService } from 'src/app/services/savingsplan.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-createsavingsplan',
  templateUrl: './createsavingsplan.component.html',
  styleUrls: ['./createsavingsplan.component.css']
})
export class CreatesavingsplanComponent implements OnInit {
  form: FormGroup;
  savingsPlan: SavingsPlan = {};
  showMessage: Boolean = false;

  constructor(private service: SavingsPlanService, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      name: this.formBuilder.control("", Validators.required),
      goalAmount: this.formBuilder.control("", Validators.required),
      timeFrame: this.formBuilder.control("", Validators.required),
      riskLevel: this.formBuilder.control("", Validators.required),
      description: this.formBuilder.control("", Validators.required),
      status: this.formBuilder.control("", Validators.required)
    });
  }

  public addSavingsPlan() {
    this.savingsPlan = this.form.value;
    this.service.addSavingsPlan(this.savingsPlan).subscribe({
      next: (data) => {
        Swal.fire({
          title: 'Success!',
          text: 'Savings plan successfully added.',
          icon: 'success',
          confirmButtonText: 'OK'
        });
        this.form.reset();
      },
      error: (error) => {
        Swal.fire({
          title: 'Adding Failed',
          text: 'Plan Name Already exists!',
          icon: 'error',
          confirmButtonText: 'OK'
        });
      }
    });
  }

  public get name() {
    return this.form.get('name');
  }

  public get goalAmount() {
    return this.form.get('goalAmount');
  }

  public get timeFrame() {
    return this.form.get('timeFrame');
  }

  public get riskLevel() {
    return this.form.get('riskLevel');
  }

  public get description() {
    return this.form.get('description');
  }

  public get status() {
    return this.form.get('status');
  }
}