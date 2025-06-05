import { NgFor } from '@angular/common';
import { Component, output } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-year-select',
  imports: [NgFor],
  templateUrl: './year-select.component.html',
  styleUrl: './year-select.component.scss'
})
export class YearSelectComponent {
  years = [2015, 2016, 2017, 2018, 2019, 2020, 2021, 2022, 2023, 2024, 2025];
  yearControl = new FormControl("", Validators.required);

  outputYear = output<string>();

  onYearChange(selection:  Event) {
    const year = <string>(selection.target as HTMLSelectElement).value;
    this.outputYear.emit(year);
  }
}
