import { Component, OnInit } from '@angular/core';
import {Song, Genres} from "../entities/entities";
import {SongService} from "../services/song.service";

@Component({
  selector: 'app-song',
  templateUrl: './song.component.html',
  styleUrls: ['./song.component.css']
})
export class SongComponent implements OnInit {

  songs : Song[];


  constructor(private songService: SongService) { }

  ngOnInit() {
    this.loadSongs();
  }

  loadSongs() {
    this.songService.getSong().subscribe(
        songs => this.songs = songs,
        err => {
          console.log(err)
        }
    );
  }

  addNew(newSong : any) {
    var songRequest : Song = {
      songId : newSong.songId,
      nameSong : newSong.nameSong,
      durationSong : newSong.durationSong
    };
    var genereRequest : Genres = {
      genereId : null,
      genereName : newSong.genereName,
      genereImage : null
    };

    this.songService.addNewSong(songRequest, genereRequest).subscribe(
      err => {console.log(err);}
    )

  }



}
