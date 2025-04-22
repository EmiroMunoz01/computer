import {Component, inject, OnInit} from '@angular/core';
import {RouterModule} from '@angular/router';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {AuthService} from '../service/auth.service';

@Component({
  selector: 'app-user-register',
  imports: [RouterModule, ReactiveFormsModule],
  templateUrl: './user-register.component.html',
  styleUrl: './user-register.component.css'
})
export default class UserRegisterComponent implements OnInit {

  registerForm!: FormGroup;
  errorMessage: string = '';
  successMessage: string = '';
  isLoading: boolean = false; // Nuevo: para manejar estado de carga

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
  }

  ngOnInit(): void {
    this.createForm();
  }

  private createForm(): void {
    this.registerForm = this.fb.group({
      username: ['', [Validators.required, Validators.minLength(3)]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', Validators.required]
    }, {
      validators: this.passwordMatchValidator
    });
  }

  passwordMatchValidator(form: FormGroup) {
    const password = form.get('password')?.value;
    const confirmPassword = form.get('confirmPassword')?.value;
    return password === confirmPassword ? null : {mismatch: true};
  }

  onSubmit(): void {
    if (this.registerForm.invalid) return;

    this.isLoading = true;
    this.errorMessage = '';
    this.successMessage = '';

    this.authService.createUser(this.registerForm.value).subscribe({
      next: (response) => {
        this.handleSuccess(response);
        this.isLoading = false;
      },
      error: (error) => {
        this.handleError(error);
        this.isLoading = false;
      }
    });
  }

  private handleSuccess(response: any): void {
    this.successMessage = 'Registro exitoso! Redirigiendo...';
    this.errorMessage = '';
    setTimeout(() => {
      this.router.navigate(['/user/login']);
    }, 2000);
  }

  private handleError(error: any): void {
    this.errorMessage = error.error?.message || 'Error en el registro. Por favor intenta nuevamente.';
    this.successMessage = '';
  }
}
