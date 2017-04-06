import { Injectable } from '@angular/core';
import { Http, Response, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';

import { Song } from './../entities/entities';

@Injectable()
export class SongService {

  private songsUrl: string = 'http://localhost:8080/app/songs';
  private newSongUrl: string = 'http://localhost:8080/app/newSong';

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

  makeFileRequest(files: File[]) {
    return Observable.create(observer => {
      let formData: FormData = new FormData(),
        xhr: XMLHttpRequest = new XMLHttpRequest();

      for (let i = 0; i < files.length; i++) {
        formData.append("uploads[]", files[i], files[i].name);
      }

      xhr.onreadystatechange = () => {
        if (xhr.readyState === 4) {
          if (xhr.status === 200) {
            observer.next(JSON.parse(xhr.response));
            observer.complete();
          } else {
            observer.error(xhr.response);
          }
        }
      };

      xhr.open('POST', this.newSongUrl, true);
      xhr.send(formData);
    });
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
