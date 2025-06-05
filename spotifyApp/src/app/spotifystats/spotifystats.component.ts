import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
@Component({
  selector: 'app-spotifystats',
  imports: [],
  templateUrl: './spotifystats.component.html',
  styleUrl: './spotifystats.component.scss'
})
export class SpotifyStatsComponent {
  timeframe: string | null = null;

  constructor(private route: ActivatedRoute) {
  this.route.params.subscribe(params => {
    if (params['timeframe'] == 'year') {
      this.timeframe = "last year"
    } else if (params['timeframe'] == '6-months') {
      this.timeframe = "last 6 months";
    } else {
      this.timeframe = "last month";
    }
  });
}
}
