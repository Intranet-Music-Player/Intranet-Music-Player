import { Component, OnInit } from '@angular/core';
import { Playlist, Song } from './../entities/entities';
import { PlaylistService } from './../services/playlist.service';

import { User } from '../entities/entities';
@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  private currentUser: any;
  private playlists: Playlist[];
  selectedPlaylist: Playlist;
  hideme: any = {};


  constructor(private playlistService: PlaylistService) { }

  ngOnInit() {
    this.currentUser = JSON.parse(sessionStorage.getItem("currentUser"));
    this.playlists = this.currentUser.playlists;
    console.log(this.playlists);
    //console.log("CURRENT USER : " + this.currentUser.playlists[0].playlistName);
  }
  addNewPlaylist(newPlaylist: any) {
    var userlogin = this.currentUser.userlogin;
    //console.log(newPlaylist.name + "--/-/--"+ userlogin);
    var playlistRequest: any = {
      userlogin: userlogin,
      playlistName: newPlaylist.name
    }
    if (newPlaylist.name != null) {
      this.playlistService.addPlaylist(playlistRequest).subscribe();
      location.reload();
    } else {
      alert("NAME OF THE PLAYLIST SHOULD HAVE A NAME")
    }

  }

  onSelectPlaylist(playlist: Playlist): void {
    this.selectedPlaylist = playlist;
  }
  onShowListSong() {
    Object.keys(this.hideme).forEach(h => {
      this.hideme[h] = false;
    });
    this.hideme[this.selectedPlaylist.playlistId] = true;
  }
  removePlaylist() {
    console.log("THIS FUNCTION SHOULD REMOVE A PLAYLIST");
  }
}
