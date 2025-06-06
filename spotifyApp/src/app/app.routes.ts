import { Routes } from '@angular/router';

export const routes: Routes = [
    {
        path: '',
        pathMatch: 'full',
        loadComponent: () => import('./home/home.component').then(m => m.HomeComponent)
    }
    , {
        path: 'spotifystats',
        loadComponent: () => import('./spotifystats/spotifystats.component').then(m => m.SpotifyStatsComponent)
    },
    {
        path: 'spotifystats/:timeframe',
        loadComponent: () => import('./spotifystats/spotifystats.component').then(m => m.SpotifyStatsComponent)
    }
];
