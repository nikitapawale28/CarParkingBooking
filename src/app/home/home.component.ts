import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{
  currentTab: string = 'home';
  showBookingForm:boolean=false;
  car_model: string = '';
  plate_number: string = '';
  booking_duration: number = 1; // Default duration in hours
  booking_id: number =1;
  username:any='';
  bookingSuccessMsg:string='';
  bookingSuccess:boolean=false;


  constructor(private http: HttpClient, private router: Router, private activatedRoute: ActivatedRoute) {}

  setActiveTab(tab: string) {
    this.currentTab = tab;
}
  selectedParking: any; // To store the selected parking lot for details
  parkingLots: { id: number; booking_status: boolean; booked_at_string: string; available_at_string: string }[] = [];
  accountInformation: {
    username: string;
    password: string;
    activeBookings: {
        carModel: string;
        plateNumber: string;
        bookingDuration: number;
        bookedAt: Date;
        availableAt: Date;
    }[];
    pastBookings: {
        carModel: string;
        plateNumber: string;
        bookingDuration: number;
        bookedAt: Date;
        availableAt: Date;
    }[];
} = {
    username: '',
    password: '',
    activeBookings: [],
    pastBookings: []
};
  ngOnInit() {
    this.fetchParkingLotDetails();
    this.activatedRoute.paramMap.subscribe(params => {
      this.username = params.get('username');
      console.log('Username:', this.username); // Do whatever you need with the username
    });
}

fetchParkingLotDetails() {
    this.http.get<any[]>('http://localhost:8080/parkingLot')
        .subscribe(response => {
            this.parkingLots = response;
        });
}
showParkingDetails(parking: any) {
  this.selectedParking = parking;
  this.bookingSuccess=false;
}

  

// Function to confirm booking
confirmBooking(parking: any) {
  // Your booking confirmation logic here
  console.log('Booking confirmed:', this.car_model, this.plate_number, this.booking_duration);
  // Reset the form and hide it

 


  // Create a BookingDetails instance with the entered data
  const bookingDetails = {
    booking_id: this.booking_id,
    car_model:this.car_model,
    plate_number: this.plate_number, 
    booking_duration: this.booking_duration, 
    username: this.username,
    parking_id:parking.id
};

 

  // Make an HTTP POST request to your Spring Boot API endpoint
  this.http.post<any[]>('http://localhost:8080/createBooking', bookingDetails)
    .subscribe(
      response => {
    
        // Reset the form and hide it
        this.car_model = '';
        this.plate_number = '';
        this.booking_duration = 1;
        this.showBookingForm = false;
        this.parkingLots = response;
        this.bookingSuccess=true;
        this.bookingSuccessMsg="Congratulations you have successfully booked the parking lot "+  this.selectedParking.id;
        this.selectedParking = response.find(parking => parking.id === this.selectedParking.id);
        

      },
      error => {
        console.error('Error confirming booking:', error);
      }
    );



  
}
fetchAccountInformation(){
  this.http.post<any>('http://localhost:8080/getBookingsForAccount', this.username)
  .subscribe(
    response => {
  
      this.accountInformation=response;
      

    },
    error => {
      console.error('Error confirming booking:', error);
    }
  );

}
// Function to cancel booking
cancelBooking() {
  this.showBookingForm = false;
}
  showTab(tab: string) {
      this.currentTab = tab;
  }

  logout() {
      // Perform logout actions if needed, e.g., clear user session
      // Then navigate to the login page or any other appropriate route
      this.router.navigate(['/login']); // Adjust the route path
  }
}




