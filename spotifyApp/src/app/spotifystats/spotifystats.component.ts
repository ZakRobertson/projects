import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
@Component({
  selector: 'app-spotifystats',
  imports: [],
  templateUrl: './spotifystats.component.html',
  styleUrl: './spotifystats.component.scss'
})
export class SpotifyStatsComponent {
  year = 'all-time';

  constructor(private route: ActivatedRoute) {
  this.route.params.subscribe(params => {
    if (params['year']) {
    this.year = params['year'];
    } 
  });
}
}
