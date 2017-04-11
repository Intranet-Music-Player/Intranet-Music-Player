import { Component, OnInit , NgModule } from '@angular/core';
import { Ng2Bs3ModalModule } from 'ng2-bs3-modal/ng2-bs3-modal';
import { Playlist, Song } from './../entities/entities';
import { SongService } from './../services/song.service';
import { Observable } from 'rxjs/Observable';
<<<<<<< HEAD
import {Headers} from "@angular/http";
import {RequestOptions} from "http";
=======
import { Headers } from "@angular/http";
>>>>>>> 4024d0caa0aa69c148860d650205eb77cf24f1c3

@Component({
  selector: 'app-song',
  templateUrl: './song.component.html',
  styleUrls: ['./song.component.css']
})
export class SongComponent implements OnInit {

<<<<<<< HEAD
  songs : Song;
  file : File;
  songlist : Song[];
=======
  file: File;
  namef: string;
  songs: Song[];
>>>>>>> 4024d0caa0aa69c148860d650205eb77cf24f1c3

  constructor(private songService: SongService) { }

    ngOnInit() {
    this.loadSongs();
  }

<<<<<<< HEAD
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
=======
  onChange(event: EventTarget) {
    let eventObj: MSInputMethodContext = <MSInputMethodContext>event;
    let target: HTMLInputElement = <HTMLInputElement>eventObj.target;
    let files: FileList = target.files;
    this.file = files[0];
    this.namef = this.file.name;
    console.log(this.file);
  }

  doAnythingWithFile() {
  }

  loadSongs() {
    this.songService.getSongs().subscribe(
      songs => this.songs = songs,
      err => { console.log(err) });
  }

  addNew(newSong: any) {
    var songRequest: Song = {
      songId: newSong.songId,
      nameSong: newSong.nameSong,
      durationSong: newSong.durationSong,
      genereN: newSong.genereN
>>>>>>> 4024d0caa0aa69c148860d650205eb77cf24f1c3
    };
    console.log(songRequest);
    this.songService.addNewSong(songRequest).subscribe(
<<<<<<< HEAD
      err => {console.log(err);}
    );
=======
      err => { console.log(err); }
    )

>>>>>>> 4024d0caa0aa69c148860d650205eb77cf24f1c3
  }
  addSongPlaylist(songId: any) {
    console.log("SONG ID " + songId);

  }


}
function handleError(error: any) {
  let errorMsg = error.message || 'ERROR -1-0-1'
  console.error(errorMsg);
  return Observable.throw(errorMsg);
}
