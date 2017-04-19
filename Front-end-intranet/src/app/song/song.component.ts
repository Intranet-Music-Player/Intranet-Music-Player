<<<<<<< HEAD
import { Component, OnInit, NgModule, ViewContainerRef } from '@angular/core';
import { Playlist, Song } from './../entities/entities';
import { SongService } from './../services/song.service';
import { Overlay } from 'angular2-modal';
import { Modal } from 'angular2-modal/plugins/bootstrap';
import { PlaylistComponent } from 'app/playlist/playlist.component';
=======
import { Component, OnInit , NgModule } from '@angular/core';
import { Playlist, Song } from './../entities/entities';
import { SongService } from './../services/song.service';
import { Observable } from 'rxjs/Observable';
>>>>>>> d0360986bf72bfb389ebfe216c760a7f2733ec16

@Component({
  selector: 'app-song',
  templateUrl: './song.component.html',
  styleUrls: ['./song.component.css']
})
export class SongComponent implements OnInit {
<<<<<<< HEAD
  file: File;
  namef: string;
  songs: Song[];
  playlists: any[];
  songIdVal: any;
  addMessage: any;

  constructor(private songService: SongService, overlay: Overlay, vcRef: ViewContainerRef, public modal: Modal) {
    overlay.defaultViewContainer = vcRef;
  }
  ngOnInit() {
=======

  songs : Song;
  file : File;
  songlist : Song[];

  constructor(private songService: SongService) { }

    ngOnInit() {
>>>>>>> d0360986bf72bfb389ebfe216c760a7f2733ec16
    this.loadSongs();
    this.playlists = JSON.parse(sessionStorage.getItem("currentUser")).playlists;
  }
<<<<<<< HEAD
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
    console.log(this.songs);
  }

  fileChange(event: any) {
=======

  loadSongs(){
    this.songService.getSongs().subscribe(
          songs => this.songlist = songs ,
          err => { console.log(err)});
  }

  fileChange (event : any) {
>>>>>>> d0360986bf72bfb389ebfe216c760a7f2733ec16
    this.songService.fileChange(event);
  }

  addNew(newSong: any) {
    var songRequest: Song = {
      songId: newSong.songId,
      nameSong: newSong.nameSong,
      durationSong: newSong.durationSong,
      genereN: newSong.genereN
    };
    console.log(songRequest);
    this.songService.addNewSong(songRequest).subscribe(
<<<<<<< HEAD
      err => { console.log(err); }
    );
=======
      err => {console.log(err);}
    );

>>>>>>> d0360986bf72bfb389ebfe216c760a7f2733ec16
  }
  songId(songId: any) {
    this.songIdVal = songId;
  }
  addSongPlaylist(playlistId: any) {
    var songPRequest: any = {
      songId: this.songIdVal,
      playlistId: playlistId
    }

<<<<<<< HEAD
    var removeSongResponse: any;
    this.songService.addSongToPlaylist(songPRequest).subscribe(
      getRemoveResponse => {
        removeSongResponse = getRemoveResponse;
        this.addMessage = removeSongResponse.message;
        /*---------------------------------------------------*/ 
        this.modal.alert()
          .size('lg')
          .showClose(true)
          .title('A simple Alert style modal window')
          .body(this.addMessage)
          .open();
        /*---------------------------------------------------*/          
      }
    );
  }
=======
}
function handleError(error: any) {
  let errorMsg = error.message || 'ERROR -1-0-1'
  console.error(errorMsg);
  return Observable.throw(errorMsg);
>>>>>>> d0360986bf72bfb389ebfe216c760a7f2733ec16
}
