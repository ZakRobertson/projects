import { NgFor } from '@angular/common';
import { Component, output } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-time-select',
  imports: [],
  templateUrl: './time-select.component.html',
  styleUrl: './time-select.component.scss'
})
export class TimeSelectComponent {
  outputTimeframe = output<string>();

  onTimeframeChange(selection:  Event) {
    const timeframe = <string>(selection.target as HTMLSelectElement).value;
    this.outputTimeframe.emit(timeframe);
  }
}
