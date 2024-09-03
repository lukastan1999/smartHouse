// import { Component } from '@angular/core';

// @Component({
//   selector: 'app-registration',
//   standalone: true,
//   imports: [],
//   templateUrl: './registration.component.html',
//   styleUrl: './registration.component.css'
// })
// export class RegistrationComponent {

// }

import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent {
  registrationForm: FormGroup;
  errorMessage: string | null = null;

  constructor(private fb: FormBuilder, private http: HttpClient, private router: Router) {
    this.registrationForm = this.fb.group({
      name: ['', Validators.required],
      surname: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      confirmPassword: ['', Validators.required],
      address: ['', Validators.required],
      phoneNumber: ['', Validators.required],
      role: ['GUEST', Validators.required],  // Default to GUEST, can be changed in the form
    });
  }

  onSubmit() {
    if (this.registrationForm.valid) {
      const registrationData = this.registrationForm.value;
      this.http.post('http://localhost:8080/api/register', registrationData)
        .subscribe(response => {
          console.log('Registration successful', response);
        }, error => {
          console.error('Registration failed', error);
        });
    }
  }
}
