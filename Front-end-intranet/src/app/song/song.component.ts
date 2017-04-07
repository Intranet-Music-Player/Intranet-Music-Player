import { Component, OnInit } from '@angular/core';
import { Playlist , Song } from './../entities/entities';
import { SongService } from './../services/song.service';
import { UlploadService } from './../services/ulpload.service';
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

  constructor(private songService : SongService, private uploadService : UlploadService) {
    this.uploadService.progress.subscribe(
      data => {
        console.log('progress = '+data);
      });
  }

  ngOnInit() {
    this.loadSongs();
  }

  onChange(event) {
    console.log('onChange');
    var files = event.srcElement.files;
    console.log(files);
    this.uploadService.makeFileRequest('http://localhost:8080/app/upload', [], files).subscribe(() => {
      console.log('sent');
    });
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
    );
  }

}
function handleError(error: any) {
  let errorMsg = error.message || 'ERROR -1-0-1'
  console.error(errorMsg);
  return Observable.throw(errorMsg);
}
