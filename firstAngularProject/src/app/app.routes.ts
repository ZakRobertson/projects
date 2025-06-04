import { Routes } from '@angular/router';

export const routes: Routes = [{
    path: '',
    pathMatch: 'full', 
    // This means that the empty path will match only if the URL is exactly empty.
    //the default pathMatch strategy is 'prefix', which means that the path will match if
    // the URL starts with the specified path.
    loadComponent: () => import('./home/home.component').then(m => m.HomeComponent) 
}

, {
    path: 'todos',
    loadComponent: () => import('./todos/todos.component').then(m => m.TodosComponent)   
}];

// This code defines the routes for the Angular application. 
// it allows us to navigate between different components by connecting them to specific URLS.

//angular defaults to the empty path ('') when the entered path does not match any of the defined routes.
// we can use the '**' path (i.e., wildcard path) to catch all unmatched routes and redirect them
// to a specific component or page, for example a "not found" page.