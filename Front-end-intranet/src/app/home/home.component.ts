import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'app/entities/entities';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  private currentUser : User;
  constructor( private router: Router) { }

  ngOnInit() {
  }

  logOut(){
    sessionStorage.removeItem("currentUser");
    this.router.navigate(['login']);
  }
}
