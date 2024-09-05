import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AccommodationService } from '../../services/accommodation.service';

@Component({
  selector: 'app-accommodation-details',
  templateUrl: './accommodation-details.component.html',
  styleUrls: ['./accommodation-details.component.css']
})
export class AccommodationDetailsComponent implements OnInit {

  accommodation: any;

  constructor(
    private route: ActivatedRoute,
    private accommodationService: AccommodationService
  ) {}

  ngOnInit(): void {
    // Get the ID from the route parameters
    this.route.params.subscribe(params => {
      const id = +params['id']; // Convert string to number
      if (id) {
        this.getAccommodationDetails(id);
      }
    });
  }

  getAccommodationDetails(id: number): void {
    this.accommodationService.getAccommodation(id).subscribe(
      data => {
        this.accommodation = data;
      },
      error => {
        console.error('Error fetching accommodation details', error);
      }
    );
  }
}
