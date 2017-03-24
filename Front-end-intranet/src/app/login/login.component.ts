import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { User } from '../entities/entities';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private userService : UserService ) { }

  ngOnInit() {
  }

  checkLogin(login : User){
    this.userService.checkUserLogin(login).subscribe();
  }

}
