import { Component, OnInit } from '@angular/core';
import { Playlist , Song } from './../entities/entities';
import { SongService } from './../services/song.service';
import { Observable } from 'rxjs/Observable';
import {Headers} from "@angular/http";
import {RequestOptions} from "http";

@Component({
  selector: 'app-song',
  templateUrl: './song.component.html',
  styleUrls: ['./song.component.css']
})
export class SongComponent implements OnInit {

  songs : Song;
  file : File;
  songlist : Song[];

  constructor(private songService : SongService) {  }

    ngOnInit() {
    this.loadSongs();
  }

  loadSongs(){
    this.songService.getSongs().subscribe(
          songs => this.songlist = songs ,
          err => { console.log(err)});
  }

  fileChange (event : any) {
    this.songService.fileChange(event);
  }

  addNew(newSong : any) {
    var songRequest : Song = {
      songId : newSong.songId,
      nameSong : newSong.nameSong,
      durationSong : newSong.durationSong,
      genereN : newSong.genereN
    };
    console.log(songRequest);
    this.songService.addNewSong(songRequest).subscribe(
      err => {console.log(err);}
    );
  }

}
function handleError(error: any) {
  let errorMsg = error.message || 'ERROR -1-0-1'
  console.error(errorMsg);
  return Observable.throw(errorMsg);
}
