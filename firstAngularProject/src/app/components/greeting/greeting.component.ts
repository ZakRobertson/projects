import { Component, input } from '@angular/core';

@Component({
  selector: 'app-greeting',
  imports: [],
  templateUrl: './greeting.component.html',
  styleUrl: './greeting.component.scss'
})
export class GreetingComponent {
  message = input('Default Greeting Message'); 
  // input is requesting a string input when the selector is used in a template, but if it isn't provided,
  // it will default to 'Default Greeting Message'.
}
