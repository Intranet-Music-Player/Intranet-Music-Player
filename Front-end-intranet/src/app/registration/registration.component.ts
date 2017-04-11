import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { User } from '../entities/entities';
import { Router } from '@angular/router';
@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  constructor(private userService: UserService, private router : Router) { }

  ngOnInit() {
  }

  registerNewUser(newUser: User) {
    var response : any;
    this.userService.addNewUser(newUser).subscribe(
      register => {
        response = register;
        if (response.success == true){
          this.router.navigate(['login']);
        } else {
          alert(response.message);
        }
      }
    );
  }
}
