import { Component, OnInit, NgModule, ViewContainerRef } from '@angular/core';
import { Playlist, Song } from './../entities/entities';
import { SongService } from './../services/song.service';
import { Overlay } from 'angular2-modal';
import { Modal } from 'angular2-modal/plugins/bootstrap';
import { PlaylistComponent } from 'app/playlist/playlist.component';
@Component({
  selector: 'app-song',
  templateUrl: './song.component.html',
  styleUrls: ['./song.component.css']
})
export class SongComponent implements OnInit {
  file: File;
  namef: string;
  songs: Song[];
  playlists: any[];
  constructor(private songService: SongService, overlay: Overlay, vcRef: ViewContainerRef, public modal: Modal) {
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
  }

  addNew(newSong: any) {
    var songRequest: Song = {
      songId: newSong.songId,
      nameSong: newSong.nameSong,
      durationSong: newSong.durationSong,
      genereN: newSong.genereN
    };

    this.songService.addNewSong(songRequest).subscribe(
      err => { console.log(err); }
    )

  }
  addSongPlaylist(songId: any) {
    console.log("SONG ID " + songId);
  }
}
