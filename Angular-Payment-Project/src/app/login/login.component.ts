import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { DataService } from '../services/data.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;

  constructor(private router: Router, private dataService: DataService) {
    this.loginForm = new FormGroup({
      username: new FormControl('', [Validators.required,
      Validators.minLength(6),
      Validators.maxLength(18)
      ]),
      password: new FormControl('', [Validators.required,
      Validators.minLength(6),
      Validators.maxLength(18)
      ])
    });
  }

  onLogin() {
    
    this.dataService.authenticate(this.username.value,this.password.value);
   
  }

  ngOnInit(): void {
  }
  get username() {
    return this.loginForm.controls['username'];
  }
  get password() {
    return this.loginForm.controls['password'];
  }

}
