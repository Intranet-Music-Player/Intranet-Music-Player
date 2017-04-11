import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { Response } from '@angular/http';
import { User } from '../entities/entities';
import { Observable } from 'rxjs/Rx';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  currentUser: User;
  checkLoginResponse: any;
  constructor(private userService: UserService, private router: Router) {

  }

  ngOnInit() {
  }

  checkLogin(login: User) {
    this.userService.checkUserLogin(login).subscribe(
      checkuserLogin => {
        this.checkLoginResponse = checkuserLogin;
        console.log(this.checkLoginResponse.success);
        if (this.checkLoginResponse.success == true) {
          sessionStorage.setItem("currentUser", JSON.stringify(this.checkLoginResponse.user));
          this.router.navigate(['home','user']);
        } else {
          alert(this.checkLoginResponse.message);
        }
      }
    );
  }

}
