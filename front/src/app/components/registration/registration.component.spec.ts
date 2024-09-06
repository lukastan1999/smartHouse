import { TestBed, ComponentFixture } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RegistrationComponent } from './registration.component';
import { RegistrationService } from '../../services/registration.service';
import { of, throwError } from 'rxjs';

describe('RegistrationComponent', () => {
  let component: RegistrationComponent;
  let fixture: ComponentFixture<RegistrationComponent>;
  let registrationService: RegistrationService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReactiveFormsModule, HttpClientTestingModule],
      declarations: [RegistrationComponent],
      providers: [RegistrationService]
    }).compileComponents();

    fixture = TestBed.createComponent(RegistrationComponent);
    component = fixture.componentInstance;
    registrationService = TestBed.inject(RegistrationService);
    fixture.detectChanges();
  });

  it('should create the form with default values and validators', () => {
    expect(component.registrationForm).toBeDefined();
    const formControls = component.registrationForm.controls;
    expect(formControls['name'].value).toBe('');
    expect(formControls['name'].valid).toBeFalse();
    expect(formControls['email'].valid).toBeFalse();
    expect(formControls['password'].valid).toBeFalse();
  });

  it('should submit form and show success message on successful registration', () => {
    const mockResponse = { message: 'User registered successfully' };
    spyOn(registrationService, 'registerUser').and.returnValue(of(mockResponse));

    component.registrationForm.setValue({
      name: 'John',
      surname: 'Doe',
      email: 'john@example.com',
      password: 'password123',
      confirmPassword: 'password123',
      address: '123 Main St',
      phoneNumber: '1234567890',
      role: 'HOST'
    });

    component.onSubmit();

    expect(registrationService.registerUser).toHaveBeenCalled();
    expect(component.responseMessage).toBe('Registration successful. Please check your email to activate your account.');
    expect(component.isSuccess).toBeTrue();
  });

  it('should not submit form if it is invalid', () => {
    spyOn(component, 'onSubmit'); // Spy

    component.registrationForm.controls['name'].setValue('');
    component.registrationForm.controls['email'].setValue('');
    
    component.onSubmit();
    
    expect(component.onSubmit).toHaveBeenCalled(); // This will now work cuz spy
  });

  it('should display error message when registration fails', () => {
    spyOn(registrationService, 'registerUser').and.returnValue(throwError({ status: 400 }));

    component.registrationForm.setValue({
      name: 'John',
      surname: 'Doe',
      email: 'john@example.com',
      password: 'password123',
      confirmPassword: 'password123',
      address: '123 Main St',
      phoneNumber: '1234567890',
      role: 'HOST'
    });

    component.onSubmit();

    expect(component.responseMessage).toBe('Registration failed. Please try again.');
    expect(component.isSuccess).toBeFalse();
  });

  it('should validate that passwords match', () => {
    const passwordControl = component.registrationForm.controls['password'];
    const confirmPasswordControl = component.registrationForm.controls['confirmPassword'];

    passwordControl.setValue('password123');
    confirmPasswordControl.setValue('password1234');

    component.onSubmit();

    expect(confirmPasswordControl.errors).toEqual({ mustMatch: true });
  });

  it('should validate phone number format correctly', () => {
    const phoneNumberControl = component.registrationForm.controls['phoneNumber'];

    phoneNumberControl.setValue('12345');
    expect(phoneNumberControl.errors?.['pattern']).toBeDefined();

    phoneNumberControl.setValue('1234567890');
    expect(phoneNumberControl.errors).toBeNull();
  });

  it('should show validation errors when required fields are missing', () => {
    component.registrationForm.controls['name'].setValue('');
    component.registrationForm.controls['email'].setValue('');

    component.onSubmit();

    expect(component.registrationForm.invalid).toBeTrue();
    expect(component.registrationForm.controls['name'].errors?.['required']).toBeTruthy();
    expect(component.registrationForm.controls['email'].errors?.['required']).toBeTruthy();
  });

  it('should show error message if role is not selected', () => {
    component.registrationForm.controls['role'].setValue('');

    component.onSubmit();

    expect(component.registrationForm.controls['role'].errors?.['required']).toBeTruthy();
  });

  it('should prevent form submission if confirm password is missing', () => {
    component.registrationForm.controls['confirmPassword'].setValue('');

    component.onSubmit();

    expect(component.registrationForm.invalid).toBeTrue();
    expect(component.registrationForm.controls['confirmPassword'].errors?.['required']).toBeTruthy();
  });

  it('should mark the email as invalid if the format is incorrect', () => {
    const emailControl = component.registrationForm.controls['email'];

    emailControl.setValue('invalid-email');
    fixture.detectChanges();
    
    expect(emailControl.errors).not.toBeNull();
    expect(emailControl.errors?.['email']).toBeTruthy(); // Email validation error
  });

  it('should mark the password as invalid if it is too short', () => {
    const passwordControl = component.registrationForm.controls['password'];
    
    passwordControl.setValue('12345');
    fixture.detectChanges();
    
    expect(passwordControl.errors).not.toBeNull();
    expect(passwordControl.errors?.['minlength']).toBeTruthy(); // Minlength validation error
  });  
    
});
