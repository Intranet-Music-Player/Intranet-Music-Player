import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { User } from '../entities/entities';
@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  constructor(private userService : UserService ) { }

  ngOnInit() {
  }

  registerNewUser(newUser : User){
    var userRequest : User = {
      username : newUser.username,
      userlogin : newUser.userlogin,
      password : newUser.password,
      email : newUser.email
    }
    this.userService.addNewUser(userRequest).subscribe(
            err => { console.log(err); }
    )
  }
}
