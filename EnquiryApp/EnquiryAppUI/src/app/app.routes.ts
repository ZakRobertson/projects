import { Routes } from '@angular/router';
import { EnquiryListComponent } from './pages/enquiry-list/enquiry-list.component';
import { NewEnquiriesComponent } from './pages/new-enquiries/new-enquiries.component';
export const routes: Routes = [

    {
        path: '',
        pathMatch: 'full',
        redirectTo: 'list'
    },
    {
        path: 'list',
        component: EnquiryListComponent
    },
    {
        path: 'createNew',
        component: NewEnquiriesComponent
    }
];
