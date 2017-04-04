import { Component, OnInit } from '@angular/core';
import { Playlist, Song } from './../entities/entities';

import { PlaylistService } from './../services/playlist.service';
import { SongService } from './../services/song.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  valor: any = { playlistName: '' };
  playlists: Playlist[];
  songs: Song[];
  
  constructor(private playlistService: PlaylistService, private songService: SongService) { }

  ngOnInit() {
    this.playlistService.getPlaylists().subscribe(playlists => this.playlists = playlists, err => { console.log(err) });
    this.songService.getSongs().subscribe(songs => this.songs = songs, err => { console.log(err) });

  }
  /*  searchValue = '';
  
    onKey(event: any) { // without type info
      this.searchValue = event.target.value;
      console.log(this.searchValue);
  
    }*/

}
