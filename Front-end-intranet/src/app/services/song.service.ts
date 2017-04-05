import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { Song } from 'app/entities/entities';

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
  addNewSong( song : Song ) {
    return this.http.post(this.newSongUrl, JSON.stringify(song), {headers: getHeaders()})
      .map((response : Response) => {
        var res = response.json();
        if (res.message === "SONG ADDED") {
          alert("SONG ADDED")
        } else {
          alert ("SONG EXIST")
        }
      })
      .catch(handleError);
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