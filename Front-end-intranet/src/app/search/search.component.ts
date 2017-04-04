import { Component, OnInit } from '@angular/core';
import { Playlist, Song } from './../entities/entities';
import { PlaylistService } from './../services/playlist.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  playlists: Playlist[];
  playlistFilter: any = { playlistName : '' };
  constructor(private playlistService: PlaylistService) { }

  ngOnInit() {
    this.playlistService.getPlaylists()
      .subscribe(
      playlists => this.playlists = playlists,
      err => { console.log(err) });
  }
/*  searchValue = '';

  onKey(event: any) { // without type info
    this.searchValue = event.target.value;
    console.log(this.searchValue);

  }*/

}
