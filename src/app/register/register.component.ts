
import { Component } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})

export class RegisterComponent {
  username: string = '';
  password: string = '';
  confirmPassword: string = '';
  passwordMismatch: boolean = false;
  passwordErrors: string[] = [];
  registrationSuccess: boolean = false;
  serverError:boolean=false;
  invalidUsername: boolean = false; 
  errorMsgFromServer:String='';

  constructor(private http: HttpClient) {}

  

  register() {
      // Check password validation criteria
      this.passwordErrors = [];
      if (this.password.length < 10) {
        this.passwordErrors.push('Password should be at least 10 characters long.');
    }
    if (!/[A-Z]/.test(this.password)) {
        this.passwordErrors.push('Password should include at least one uppercase letter.');
    }
    if (!/[a-z]/.test(this.password)) {
        this.passwordErrors.push('Password should include at least one lowercase letter.');
    }
    if (!/\d/.test(this.password)) {
        this.passwordErrors.push('Password should include at least one digit.');
    }
    if (!/[!@#$%^&*()_+{}\[\]:;<>,.?~\\-|=]/.test(this.password)) {
        this.passwordErrors.push('Password should include at least one special character.');
    }



    if (this.password !== this.confirmPassword) {
      this.passwordMismatch = true;
      return;
  } else {
      this.passwordMismatch = false;
      // Validate username format
      const usernameRegex = /^[a-z]+@gmail\.com$/;
      if (!usernameRegex.test(this.username)) {
          this.invalidUsername = true;
          return; // Stop the registration process
      } else {
          this.invalidUsername = false;
      }
      
      const credentials = {
        username: this.username,
        password: this.password,
        confirmPassword: this.confirmPassword
      };
     
      this.http.post('http://localhost:8080/register', credentials, { responseType: 'text' })
          .subscribe(
              response => {
                  console.log(response); // Log the plain text response from the server
                  if (response === 'Registration successful') {
                      this.registrationSuccess = true; // Set the success flag
                      this.serverError = false; // Clear the server error flag
                  } else {
                      this.serverError = true; // Set the server error flag for unexpected responses
                  }
              },
              (error: HttpErrorResponse) => {
                console.error(error);
                this.serverError = true;

                if (error.status === 400 && error.error) {
                  this.registrationSuccess = false; // Set the success flag
                    this.errorMsgFromServer = error.error; // Store the error message
                    // Handle the specific error case, e.g., display the error message
                } else {
                    // Handle other types of errors, if needed
                }
            }
              
          );

      // Perform further actions like submitting the form
  }
    
}
}
