import { Component, signal } from '@angular/core';
import { GreetingComponent } from '../components/greeting/greeting.component';
import { CounterComponent } from '../components/counter/counter.component';

@Component({
  selector: 'app-home',
  imports: [GreetingComponent, CounterComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {
  homeMessage = signal('Hello, World!'); 

  keyUpHandler(event: KeyboardEvent) { 
    // event: KeyboardEvent specifies that the event is a keyboard event, which is useful
    // for type checking and accessing properties specific to keyboard events, like `key`.
    console.log(`user pressed the ${event.key} key`); 
    /*
    this string is surrounded by backticks, which allows us to use template literals.
    template literals allow us to embed expressions inside a string using ${expression} syntax.
    in a normal string, the ${expression} would be printed as is instead of being evaluated.
    */
  }
 
  count = signal(0);
}
