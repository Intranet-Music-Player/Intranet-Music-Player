import { Component, OnInit } from '@angular/core';
import { Playlist, Song } from './../entities/entities';
import { PlaylistService } from './../services/playlist.service';
import { UserService } from './../services/user.service';
import { User } from '../entities/entities';
import { NgZone } from '@angular/core';
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

  constructor(private playlistService: PlaylistService, private userService: UserService, private zone: NgZone) { }
  ngOnInit() {
    this.getCurrentUSer();
  }
  addNewPlaylist(newPlaylist: any) {
    var playlistResponse: any;
    var userlogin = this.currentUser.userlogin;
    var playlistRequest: any = {
      userlogin: userlogin,
      playlistName: newPlaylist.name
    }
    if (newPlaylist.name.length !== 0) {
      this.playlistService.addPlaylist(playlistRequest).subscribe(
        playlistResponse => {
          playlistResponse = playlistResponse;
          if (playlistResponse.success == true) {
            alert(playlistResponse.message);
          } else {
            alert(playlistResponse.message);
          }
        }
      );
      this.getCurrentUSer();
    } else {
      alert("NAME OF THE PLAYLIST SHOULD HAVE A NAME")
    }
  }
  getCurrentUSer() {
    this.currentUser = JSON.parse(sessionStorage.getItem("currentUser"));
    var userRequest: any = {
      userlogin: this.currentUser.userlogin
    }
    this.userService.getCurrentUser(userRequest).subscribe(
      getUser => {
        this.currentUser = getUser.user;
        this.playlists = this.currentUser.playlists;
      }
    )
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
    try {
      var removeRequest: any = {
        playlistId: this.selectedPlaylist.playlistId,
        userlogin: this.currentUser.userlogin
      }
      this.playlistService.removeUserPlaylist(removeRequest).subscribe();
      this.getCurrentUSer();
      location.reload();
    } catch (e) {
      alert("SELECT PLAYLIST TO DELETE");
    }
  }
}
