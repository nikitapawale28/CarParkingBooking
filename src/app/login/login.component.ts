import { Component } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router'; // Import Router

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  username: string = '';
  password: string = '';
  loginSuccess:boolean =false;
  serverError:boolean =false;
  errorMsgFromServer:string='';

  constructor(private http: HttpClient, private router: Router) {}

  headers = new HttpHeaders({'Access-Control-Allow-Origin' : '*'})




  login() {
    
    const credentials = { username: this.username, password: this.password };
  
    this.http.post('http://localhost:8080/login', credentials, { responseType: 'text' })
    .subscribe(
        response => {
            console.log(response); // Log the plain text response from the server
            if (response === 'login successful') {
                this.loginSuccess = true; // Set the success flag
                this.serverError = false; // Clear the server error flag

                // Redirect to a new page on successful login
                this.router.navigate(['/home', this.username]);// Adjust the route path
            } else {
                this.serverError = true; // Set the server error flag for unexpected responses
            }
        },
        (error: HttpErrorResponse) => {
          console.error(error);
          this.serverError = true;

          if (error.status === 400 && error.error) {
            this.loginSuccess = false; // Set the success flag
              this.errorMsgFromServer = error.error; // Store the error message
              // Handle the specific error case, e.g., display the error message
          } else {
              // Handle other types of errors, if needed
          }
      }
        
    );




  }
}
