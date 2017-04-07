import { Component, OnInit } from '@angular/core';
import { Playlist, Song} from './../entities/entities';

import { PlaylistService } from './../services/playlist.service';
import { SongService } from './../services/song.service';


@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  valor: any = { playlistName: '' };
  filtre: string = '';
  playlists: Playlist[];
  songs: Song[];

  constructor(private playlistService: PlaylistService, private songService: SongService ) { }

  ngOnInit() {
    this.playlistService.getPlaylists().subscribe(playlists => this.playlists = playlists, err => { console.log(err) });
    this.songService.getSongs().subscribe(songs => this.songs = songs, err => { console.log(err) });
  }
  filterBy(filtre: any) {
    console.log(filtre);
    if (filtre == "playlists")
      this.valor = { playlistName: '' };
    else if (filtre == "songs")
      this.valor = { nameSong: '' };
    else if (filtre == "albums")
      console.log("FILTERING BY ALBUM NAME");
  }
}
