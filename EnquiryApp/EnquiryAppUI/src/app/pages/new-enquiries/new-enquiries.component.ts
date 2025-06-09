import { Component, signal,inject } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {Enquiry} from '../../classes/enquiry-class/enquiryClass';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MasterService } from '../../service/master.service';
import { Observable } from 'rxjs';
import { AsyncPipe } from '@angular/common';

@Component({
  selector: 'app-new-enquiries',
  imports: [FormsModule, ReactiveFormsModule, AsyncPipe],
  templateUrl: './new-enquiries.component.html',
  styleUrl: './new-enquiries.component.css'
})
export class NewEnquiriesComponent {

  submitted = false;

  enquiryForm = new FormGroup({
    firstName: new FormControl('', Validators.required),
    lastName: new FormControl('', Validators.required),
    enquiryType: new FormControl(null, Validators.required),
    enquiryStatus: new FormControl(null, Validators.required),
    phoneNo: new FormControl('', Validators.required),
    email: new FormControl('', Validators.email),
    message: new FormControl('', Validators.required),
    resolution: new FormControl('')
  });

  MasterSrv= inject(MasterService);
  typeList: Observable<any> = new Observable<any>();
  statusList: Observable<any> = new Observable<any>();

  constructor() {
    this.typeList = this.MasterSrv.getTypes();
    this.statusList = this.MasterSrv.getStatus();
  }

  handleSubmit() {
    this.submitted = true;
    if (!this.enquiryForm.valid) {
      // Optionally, mark all fields as touched to show validation errors
      this.enquiryForm.markAllAsTouched();
      return;
    }
    const enquiryTypeId = Number(this.enquiryForm.value.enquiryType);
    const enquiryStatusId = Number(this.enquiryForm.value.enquiryStatus);

    const newEnquiry = new Enquiry(
      enquiryTypeId ?? null,
      enquiryStatusId ?? null,
      `${this.enquiryForm.value.firstName} ${this.enquiryForm.value.lastName}`,
      this.enquiryForm.value.phoneNo ?? '',
      this.enquiryForm.value.email ?? '',
      this.enquiryForm.value.message ?? '',
      this.enquiryForm.value.resolution ?? ''
    );
    

    this.MasterSrv.createEnquiry(newEnquiry).subscribe({
      next: (res: Enquiry) => {
        console.log('New Enquiry Submitted:', newEnquiry);
        debugger;
      },
      error: (error) => {
        console.error('Error creating enquiry:', error);
      }
    });
  
    // Reset form fields
    this.enquiryForm.reset();
    this.submitted = false; 
  }
}
