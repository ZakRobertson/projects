import { Component, Output, signal, EventEmitter, inject } from '@angular/core';
import { FormGroup, FormsModule, ReactiveFormsModule, FormControl,Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { YearSelectComponent } from "../year-select/year-select.component";
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-greeting',
  imports: [FormsModule, ReactiveFormsModule, YearSelectComponent],
  templateUrl: './greeting.component.html',
  styleUrl: './greeting.component.scss'
})
export class GreetingComponent {
  
  router = inject(Router);

  yearSelected = ""
  greetingForm = new FormGroup({
    name: new FormControl(''),
    accountName: new FormControl('', Validators.required),
  })
  
  setYear(year: string) {
    this.yearSelected = year;
    console.log("Year selected:", this.yearSelected);
  }

  handleSubmit() {
    if (this.greetingForm.valid && this.yearSelected) {
      if (this.yearSelected === 'all') {
        this.router.navigate(['/spotifystats']);
      } else {
        this.router.navigate([`/spotifystats/${this.yearSelected}`]);
      }
    }
  };
}
