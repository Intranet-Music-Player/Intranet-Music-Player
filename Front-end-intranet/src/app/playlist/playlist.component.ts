import { Component, OnInit } from '@angular/core';
import { Playlist, Song } from './../entities/entities';
import { PlaylistService } from './../services/playlist.service';

@Component({
  selector: 'app-playlist',
  templateUrl: './playlist.component.html',
  styleUrls: ['./playlist.component.css']
})
export class PlaylistComponent implements OnInit {
  private currentUser: any;
  playlists: Playlist[];
  selectedPlaylist: Playlist;
  selectedSong: Song;
  hideme: any = {};
  hideNewPlaylist: boolean = false;

  hideShowNewPlaylist() {
    this.hideNewPlaylist = !this.hideNewPlaylist;
  }

  constructor(private playlistService: PlaylistService) { }

  ngOnInit() {
    this.loadPlaylists();
    this.currentUser = JSON.parse(sessionStorage.getItem("currentUser"));
  }

  loadPlaylists() {
    this.playlistService.getPlaylists()
      .subscribe(
      playlists => this.playlists = playlists,
      err => { console.log(err) });
  }
  onSelectPlaylist(playlist: Playlist): void {
    this.selectedPlaylist = playlist;
  }
  onSelectSong(song: Song) {
    this.selectedSong = song;
  }
  onShowListSong() {
    Object.keys(this.hideme).forEach(h => {
      this.hideme[h] = false;
    });
    this.hideme[this.selectedPlaylist.playlistId] = true;
  }
  following() {
    var followRequest : any = {
      playlistId : this.selectedPlaylist.playlistId,
      userlogin : this.currentUser.userlogin
    }
    this.playlistService.userFollowPlaylist(followRequest).subscribe();
  }
}
