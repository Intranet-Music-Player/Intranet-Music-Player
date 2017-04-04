import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { Song } from './../entities/entities';

@Injectable()
export class SongService {

  private songsUrl: string = 'http://localhost:8080/app/songs';


  constructor(private http: Http) { }

  getSongs(): Observable<Song[]> {
    return this.http.get(this.songsUrl)
      .map((res: Response) => res.json())
      .catch(handleError)
  }
  addNewSong(newSong: any): Observable<any> {
    let songRequest: any = {
      namesong: newSong.songName,
      durationsong: newSong.songDuration,
      genereName: newSong.genereName
    };
    return this.http.post('http://localhost:8080/app/song', JSON.stringify(songRequest), { headers: getHeaders() })
      .map((response: Response) => {
        var res = response.json();
        console.log(res.message);
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
