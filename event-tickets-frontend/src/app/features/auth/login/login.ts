import { Component, OnInit } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../../../core/services/auth-service';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule,
        RouterLink
  ],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class LoginComponent  {
  loginForm;

  constructor(
    private fb:FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
    this.loginForm = this.fb.group({
      email:['',[Validators.required,Validators.email]],
      password: ['', Validators.required]
    });
  }

  //login

  login() {
    if(this.loginForm.invalid){
      return;
    }


    this.authService.login(this.loginForm.value as any).subscribe({
      next:(response)=>{
        this.authService.saveUser(response);
        if(response.role === 'ADMIN'){
          this.router.navigate(['/admin']);
        }else{
          this.router.navigate(['/customer']);
        }
      },
      error:(error)=>{
        console.error('Login failed:', error);
        alert('Login failed. Please check your credentials and try again.');
        
      }
    });
  }

}
