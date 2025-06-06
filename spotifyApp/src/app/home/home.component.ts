import { Component, signal} from '@angular/core';
import { GreetingComponent } from '../components/greeting/greeting.component';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-home',
  imports: [GreetingComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {
  nameEntered = signal(false);
  canNavigate = signal(this.nameEntered());
  onNameEntered(entered: boolean) {
    this.nameEntered.set(entered); //buggy
  }

  
}
