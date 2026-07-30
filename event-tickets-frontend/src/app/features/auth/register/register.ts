import { Component } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../../../core/services/auth-service';
import { Router } from '@angular/router';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-register',
  imports: [
    RouterLink,
    ReactiveFormsModule
  ],
  templateUrl: './register.html',
  styleUrl: './register.css',
})
export class RegisterComponent {
  registerForm;
  constructor(
    private fb : FormBuilder,
    private authService :AuthService,
    private router : Router
  ) {
    this.registerForm = this.fb.nonNullable.group({
    name: ['', Validators.required],

    email: ['', [Validators.required, Validators.email]],

    phone: ['', Validators.required],

    password: ['', [Validators.required, Validators.minLength(6)]]
    })
  }

  register() {
  if (this.registerForm.invalid) {
    return;
  }

  this.authService
    .register(this.registerForm.getRawValue())
    .subscribe({
      next: () => {
        alert('Registration successful');
        this.router.navigate(['/login']);
      },

      error: (err) => {
        console.error(err);
        alert('Registration failed');
      }
    });
}
}
