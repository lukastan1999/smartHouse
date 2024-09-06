import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RegistrationService } from '../../services/registration.service';


@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent {
  registrationForm: FormGroup;
  submitted = false;
  responseMessage: string = '';
  isSuccess: boolean = false;
  user = {
    name: '',
    surname: '',
    email: '',
    password: '',
    confirmPassword: '',
    address: '',
    phoneNumber: '',
    role: '' // "HOST" ili "GUEST"
  };

  constructor(private formBuilder: FormBuilder, private registrationService: RegistrationService) {
    this.registrationForm = this.formBuilder.group({
      name: ['', Validators.required],
      surname: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', Validators.required],
      address: ['', Validators.required],
      phoneNumber: ['', [Validators.required, Validators.pattern(/^\d{10}$/)]],
      role: ['', Validators.required]
    }, {
      validator: this.MustMatch('password', 'confirmPassword')
    });
  }

  get formControls() {
    return this.registrationForm.controls;
  }


  onSubmit() {
    this.submitted = true;
    if (this.registrationForm.invalid) {
      return;
    }

    console.log(this.registrationForm.value);
    const formValues = this.registrationForm.value;

    this.registrationService.registerUser(formValues).subscribe(
      response => {
        console.log('Registration successful', response);
        this.responseMessage = 'Registration successful. Please check your email to activate your account.';
        this.isSuccess = true;
      },
      error => {
        console.error('Registration failed', error);
        this.responseMessage = 'Registration failed. Please try again.';
        this.isSuccess = false;
      }
    );
  }

  // dodatni validator
  MustMatch(controlName: string, matchingControlName: string) {
    return (formGroup: FormGroup) => {
      const control = formGroup.controls[controlName];
      const matchingControl = formGroup.controls[matchingControlName];
      if (matchingControl.errors && !matchingControl.errors['mustMatch']) {
        return;
      }
      if (control.value !== matchingControl.value) {
        matchingControl.setErrors({ mustMatch: true });
      } else {
        matchingControl.setErrors(null);
      }
    };
  }
}
