import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HeaderComponent } from './components/header/header.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, HeaderComponent],
  template: `
   \  <app-header></app-header>
      <main> 
        <router-outlet />
      </main>
  `,
  styles: [`
    main { 
      padding: 16px;
    }
    `],


})
export class AppComponent {
  title = 'hello-world';
}

/*
This is the main component of the Angular application.
It serves as the root component that bootstraps the application.
in this case, the template and styles are defined inline rather than in separate html and css files.
It imports the RouterOutlet for routing and the HeaderComponent for the header section.
*/ 