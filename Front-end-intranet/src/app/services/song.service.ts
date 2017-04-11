import { Injectable } from '@angular/core';
import {Http, Response, Headers, RequestOptions} from '@angular/http';
import { Observable } from 'rxjs/Observable';
<<<<<<< HEAD

import { Song } from './../entities/entities';
import {ContentType} from "@angular/http/src/enums";
=======
import { Song } from 'app/entities/entities';
>>>>>>> 4024d0caa0aa69c148860d650205eb77cf24f1c3

@Injectable()
export class SongService {

  private songsUrl: string = 'http://localhost:8080/app/songs';
  private newSongUrl: string = 'http://localhost:8080/app/newSong';
  private uploadUrl: string = 'http://localhost:8080/app/upload';

  constructor(private http: Http) { }

  getSongs(): Observable<Song[]> {
    return this.http.get(this.songsUrl)
      .map((res: Response) => res.json())
      .catch(handleError)
  }

  addNewSong(song: Song) {
    console.log(JSON.stringify(song));
    return this.http.post(this.newSongUrl, JSON.stringify(song), {headers: getHeaders()})
      .map((response: Response) => {
        var res = response.json();
        if (res.message === "SONG ADDED") {
          alert("SONG ADDED")
        } else {
          alert("SONG EXIST")
        }
      })
      .catch(handleError);
  }

  fileChange(event) {
    let fileList: FileList = event.target.files;
    if (fileList.length > 0) {
      let file: File = fileList[0];
      let formData: FormData = new FormData();
      formData.append('uploadFile', file, file.name);

      let headers = new Headers();
      headers.append('Content-Type', 'multipart/form-data, boundary=HereGoes');
      headers.append('Accept', 'application/json');

      let options = new RequestOptions({headers: headers});

      this.http.post(`${this.uploadUrl}`, formData, options)
        .map(res => res.json())
        .catch(error => Observable.throw(error))
        .subscribe(
          data => console.log('success'),
          error => console.log(error)
        )
    }
  }

}
function handleError(error: any) {
  let errorMsg = error.message || 'ERROR -1-0-1'
  console.error(errorMsg);
  return Observable.throw(errorMsg);
}
function getHeaders() {
  let headers = new Headers();
  headers.append('Accept', 'application/json');
  headers.append('Content-Type', 'application/json');
  return headers;
}