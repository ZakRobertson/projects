import { Component, Output, signal, EventEmitter, inject } from '@angular/core';
import { FormGroup, FormsModule, ReactiveFormsModule, FormControl,Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { TimeSelectComponent } from "../time-select/time-select.component";
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-greeting',
  imports: [FormsModule, ReactiveFormsModule, TimeSelectComponent],
  templateUrl: './greeting.component.html',
  styleUrl: './greeting.component.scss'
})
export class GreetingComponent {
  
  router = inject(Router);

  timeframeSelected = ""
  greetingForm = new FormGroup({
    name: new FormControl(''),
    accountName: new FormControl('', Validators.required),
  })
  
  setYear(year: string) {
    this.timeframeSelected = year;
    console.log("Year selected:", this.timeframeSelected);
  }

  handleSubmit() {
    if (this.greetingForm.valid && this.timeframeSelected) {
      if (this.timeframeSelected === 'all') {
        this.router.navigate(['/spotifystats']);
      } else {
        this.router.navigate([`/spotifystats/${this.timeframeSelected}`]);
      }
    }
  };
}
