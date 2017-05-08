import {Component, OnInit, NgModule, ViewContainerRef, ViewChild} from '@angular/core';
import { Playlist, Song } from './../entities/entities';
import { SongService } from './../services/song.service';
import { Overlay } from 'angular2-modal';
import { Modal } from 'angular2-modal/plugins/bootstrap';
import { PlaylistComponent } from 'app/playlist/playlist.component';
import { RatingModule } from "ngx-rating";
/**/
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
/**/
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
  songIdVal: any;
  addMessage: any;

  starsCount: number = 5;
  starsCounts: number[] = [];
  constructor(private songService: SongService, overlay: Overlay, vcRef: ViewContainerRef, public modal: Modal, private http: Http) {
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
  getValoration(star: number) {
    console.log(star);
  }
  /***/
  buttonEnabled: boolean = false;
  fileList: any;
  fileChange(e) {
    this.fileList = e.target.files;
    this.buttonEnabled = true;
  }

  uploadSong(newSongData: any) {
    console.log(newSongData.nameSong);
    console.log(newSongData.durationSong);
    console.log(newSongData.genereN);

    if (this.fileList.length > 0) {
      let file: File = this.fileList[0];
      let formData: FormData = new FormData();

      var uploadRequest: any = {
        nameSong: newSongData.nameSong,
        durationSong: newSongData.durationSong,
        genereName: newSongData.genereN
      }

      formData.append('uploadFile', file, file.name);
      //formData.append('fileInfo', uploadRequest);
      //console.log(file.size);
      formData.append('fileInfo', new Blob([JSON.stringify(uploadRequest)],
        {
          type: "application/json"
        }));
      let headers = new Headers();
      headers.append('Accept', 'application/json');
      let options = new RequestOptions({ headers: headers });
      this.http.post('http://localhost:8080/app/file', formData, options)
        /*.map((res: Response) => res.json())*/
        .catch(error => Observable.throw(error))
        .subscribe(
        data => {
          data = data;
          console.log(data);
        }
        ,
        error => console.log(error)
        )
    }
  }
  /***/
}
