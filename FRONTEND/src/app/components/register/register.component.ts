import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { IntegrationService } from '../../services/integration.service';
import { SessionStorageService } from '../../services/SessionStorageService';
import { SignupRequest } from '../../models/signup-request';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterLink
  ],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  request: SignupRequest = new SignupRequest();
  msg?: string;

  signupForm = new FormGroup({
    name:     new FormControl('', Validators.required),
    username: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required),
    address:  new FormControl('', Validators.required),
    mobileno: new FormControl('', Validators.required),
    age:      new FormControl('', Validators.required),
    terms:    new FormControl(false, Validators.requiredTrue)  // ← control para el checkbox
  });

  constructor(
    private integrationService: IntegrationService,
    private storage: SessionStorageService
  ) {}

  onSubmit() {
    if (this.signupForm.invalid) {
      console.log("Formulario inválido.");
      return;
    }

    this.storage.remove('auth-key');

    const { name, username, password, address, mobileno, age } = this.signupForm.value;
    this.request.name     = name!;
    this.request.username = username!;
    this.request.password = password!;
    this.request.address  = address!;
    this.request.mobileno = mobileno!;
    this.request.age      = age!;

    this.integrationService.doRegister(this.request).subscribe({
      next: (res: any) => {
        this.msg = res.response;
      },
      error: (err) => {
        console.error("Error recibido:", err);
      }
    });
  }
}
