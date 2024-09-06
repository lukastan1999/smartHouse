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
  newAvailableDates: string = '';
  responseMessage: string = '';

  constructor(
    private route: ActivatedRoute,
    private accommodationService: AccommodationService
  ) {}

  ngOnInit(): void {
    // id uzimamo sa rute
    this.route.params.subscribe(params => {
      const id = +params['id'];
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

  redefineAccommodation(): void {

    const datesArray = this.newAvailableDates.split(',').map(date => date.trim());

    const redefineDto = {
      id: this.accommodation.id,
      datumi: datesArray
    };

    this.accommodationService.redefineAccommodation(redefineDto).subscribe(
      response => {
        this.responseMessage = response.message;
      },
      error => {
        console.error('Error redefining accommodation', error);
        this.responseMessage = 'Error redefining accommodation';
      }
    );
  }
}
