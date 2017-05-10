import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';

import { Song } from './../entities/entities';

@Injectable()
export class SongService {

  private songsUrl: string = 'http://localhost:8080/app/songs';
  private newSongUrl: string = 'http://localhost:8080/app/newSong';
  private uploadUrl: string = 'http://localhost:8080/app/upload';
  private addSongToPlaylistUrl: string = 'http://localhost:8080/app/addSongToPlaylist';

  // constructor(private http: Http) { }

  constructor(private http: Http) {
    /*this.progress$ = Observable.create(observer => {
      this.progressObserver = observer
    }).share();*/
  }

  getSongs(): Observable<Song[]> {
    return this.http.get(this.songsUrl)
      .map((res: Response) => res.json())
      .catch(handleError)
  }

  addNewSong(song: Song) {
    return this.http.post(this.newSongUrl, JSON.stringify(song), { headers: getHeaders() })
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
  addSongToPlaylist(songPlaylist: any): Observable<any> {
    return this.http.post(this.addSongToPlaylistUrl, JSON.stringify(songPlaylist), { headers: getHeaders() })
      .map((response: Response) => response.json())
      .catch(handleError);
  }
  removeSong(removeRequest: any): Observable<any> {
    return this.http.post("http://localhost:8080/app/removeSong", JSON.stringify(removeRequest), { headers: getHeaders() })
      .map((response: Response) => response.json())
      .catch(handleError);
  }

  makeFileRequest(url: string, params: string[], files: File[]): Observable<any> {
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

      /*xhr.upload.onprogress = (event) => {
        this.progress = Math.round(event.loaded / event.total * 100);

        this.progressObserver.next(this.progress);
      };*/

      xhr.open('POST', url, true);
      xhr.send(formData);
    });
  }

  /*fileChange(event) {
    let fileList: FileList = event.target.files;
    if (fileList.length > 0) {
      let file: File = fileList[0];
      let formData: FormData = new FormData();
      formData.append('uploadFile', file, file.name);

      let headers = new Headers();
      headers.append("enctype", "multipart/form-data");
      headers.append('Accept', 'application/json');

      let options = new RequestOptions({headers: headers});

      this.http.post(`${this.uploadUrl}`, formData)
        .map(res => res.json())
        //.catch(error => Observable.throw(error))
        .subscribe(
          data => console.log('success'),
          error => console.log(error)
        )
    }
  }*/

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
