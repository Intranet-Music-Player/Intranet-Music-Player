import {Component, OnInit, NgModule, ViewContainerRef, ViewChild} from '@angular/core';
import { Playlist, Song } from './../entities/entities';
import { SongService } from './../services/song.service';
import { Overlay } from 'angular2-modal';
import { Modal } from 'angular2-modal/plugins/bootstrap';
import { PlaylistComponent } from 'app/playlist/playlist.component';

@Component({
  selector: 'app-song',
  templateUrl: './song.component.html',
  @View
  styleUrls: ['./song.component.css']
})
export class SongComponent implements OnInit {
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
    this.loadSongs();
    this.playlists = JSON.parse(sessionStorage.getItem("currentUser")).playlists;
  }
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

  /*fileChange(event: any) {
    this.songService.fileChange(event);
  }*/

  fileChange(event) {
    console.log('onChange');
    var files = event.srcElement.files;
    console.log(files);
    this.songService.makeFileRequest('http://localhost:8080/app/upload', [], files).subscribe(() => {
      console.log('sent');
    });
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
      err => { console.log(err); }
    );
  }
  songId(songId: any) {
    this.songIdVal = songId;
  }
  addSongPlaylist(playlistId: any) {
    var songPRequest: any = {
      songId: this.songIdVal,
      playlistId: playlistId
    }

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
}
