import { Component, Output, signal, EventEmitter } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-greeting',
  imports: [FormsModule],
  templateUrl: './greeting.component.html',
  styleUrl: './greeting.component.scss'
})
export class GreetingComponent {
  name = signal('');
  enterPressed = signal(false);
  @Output() nameEntered = new EventEmitter<boolean>();
  
  didntEnter() {
    this.nameEntered.emit(false);
  }

  enteredName() {
    this.enterPressed.set(true);
  }

  undo() {
    this.name.set('');
    this.enterPressed.set(false);
  }

  
}
