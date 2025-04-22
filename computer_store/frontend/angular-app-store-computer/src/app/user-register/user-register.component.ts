import {Component, inject, OnInit} from '@angular/core';
import {RouterModule} from '@angular/router';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {Router} from '@angular/router';

import {HttpClient} from '@angular/common/http';
import {CommonModule, NgClass} from '@angular/common';

@Component({
  selector: 'app-user-register',
  imports: [RouterModule, ReactiveFormsModule, NgClass, CommonModule],
  templateUrl: './user-register.component.html',
  styleUrl: './user-register.component.css'
})
export default class UserRegisterComponent {

  registerForm!: FormGroup;
  errorMessage: string = '';
  successMessage: string = '';

  constructor(
    private fb: FormBuilder,
    private http: HttpClient,
    private router: Router
  ) {
    this.registerForm = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(3)]],
      lastname: ['', [Validators.required, Validators.minLength(3)]],
      identificationDocument: ['', [Validators.required, Validators.minLength(3)]],
      numberPhone: ['', [Validators.required, Validators.minLength(3)]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', Validators.required]
    }, {validator: this.passwordMatchValidator});
  }

  passwordMatchValidator(formGroup: FormGroup) {
    const password = formGroup.get('password')?.value;
    const confirmPassword = formGroup.get('confirmPassword')?.value;
    return password === confirmPassword ? null : {mismatch: true};
  }

  onSubmit() {
    if (this.registerForm.valid) {
      const userData = {
        name: this.registerForm.value.name,
        lastname: this.registerForm.value.lastname,
        identificationDocument: this.registerForm.value.identificationDocument,
        numberPhone: this.registerForm.value.numberPhone,
        email: this.registerForm.value.email,
        password: this.registerForm.value.password
      };

      this.http.post('http://localhost:8080/store/user/create', userData)
        .subscribe({
          next: (response) => {
            this.successMessage = 'Registro exitoso! Redirigiendo...';
            this.errorMessage = '';
            setTimeout(() => {
              this.router.navigate(['user/login']);
            }, 2000);
          },
          error: (error) => {
            this.errorMessage = error.error.message || 'Error en el registro';
            this.successMessage = '';
          }
        });
    }
  }

}
