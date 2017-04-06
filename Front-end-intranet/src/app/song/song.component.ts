import { Component, OnInit } from '@angular/core';
import { Playlist , Song } from './../entities/entities';
import { SongService } from './../services/song.service';
import { Observable } from 'rxjs/Observable';
import {Headers} from "@angular/http";

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

  onChange(event: EventTarget) {
    let eventObj: MSInputMethodContext = <MSInputMethodContext> event;
    let target: HTMLInputElement = <HTMLInputElement> eventObj.target;
    let files: FileList = target.files;
    this.file= files[0];
    console.log(this.file);
  }

  doAnythingWithFile() {

  }

  loadSongs(){
    this.songService.getSongs().subscribe(
          songs => this.songlist = songs ,
          err => { console.log(err)});
  }

  addNew(newSong : any) {
    var songRequest : Song = {
      songId : newSong.songId,
      nameSong : newSong.nameSong,
      durationSong : newSong.durationSong,
      genereN : newSong.genereN,
      afile : newSong.file
    };
    console.log(songRequest);
    this.songService.addNewSong(songRequest).subscribe(
      err => {console.log(err);}
    )
    this.songService.makeFileRequest(newSong).subscribe(
      err => {console.log(err);}
    )

  }

}
function handleError(error: any) {
  let errorMsg = error.message || 'ERROR -1-0-1'
  console.error(errorMsg);
  return Observable.throw(errorMsg);
}
