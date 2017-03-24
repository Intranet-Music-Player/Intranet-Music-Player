import { Component, OnInit } from '@angular/core';
import { Playlist } from './../entities/entities';
import { PlaylistService } from './../services/playlist.service';

@Component({
  selector: 'app-playlist',
  templateUrl: './playlist.component.html',
  styleUrls: ['./playlist.component.css']
})
export class PlaylistComponent implements OnInit {
  playlists: Playlist[];
  selectedPlaylist: Playlist;
  showListSong: boolean = true;
  hideme: any = {};

  constructor(private playlistService: PlaylistService) { }

  ngOnInit() {
    this.loadPlaylists();
  }

  loadPlaylists() {
    this.playlistService.getPlaylists()
      .subscribe(
      playlists => this.playlists = playlists,
      err => { console.log(err) });
  }
  onSelect(playlist: Playlist): void {
    this.selectedPlaylist = playlist;
    console.log(this.selectedPlaylist);
  }
  /*
  onShowListSong() {
    this.showListSong = !this.showListSong;
  }*/


  onShowListSong() {
    Object.keys(this.hideme).forEach(h => {
      this.hideme[h] = false;
    });
    this.hideme[this.selectedPlaylist.playlistId] = true;
  }




}
