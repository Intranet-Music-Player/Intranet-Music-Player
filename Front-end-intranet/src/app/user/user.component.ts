import { Component, OnInit } from '@angular/core';
import { Playlist , Song} from './../entities/entities';

import { User } from '../entities/entities';
@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  private currentUser : any;
  private playlists: Playlist[];
  constructor() { }

  ngOnInit() {
      this.currentUser = JSON.parse(localStorage.getItem("currentUser"));
      this.playlists = this.currentUser.playlists;
      console.log(this.playlists);
      //console.log("CURRENT USER : " + this.currentUser.playlists[0].playlistName);
  }
}
