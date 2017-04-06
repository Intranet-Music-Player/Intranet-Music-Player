import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { Playlist } from './../entities/entities';

@Injectable()
export class PlaylistService {

  private playlistUrl: string = 'http://localhost:8080/app/playlists';
  private playistNew: string = 'http://localhost:8080/app/playlist/add';

  constructor(private http: Http) { }

  getPlaylists(): Observable<Playlist[]> {
    return this.http.get(this.playlistUrl)
      .map((res: Response) => res.json())
      .catch(handleError)
  }
  addPlaylist(newPlaylist: any): Observable<any> {
    console.log(newPlaylist);
    return this.http.post(this.playistNew, JSON.stringify(newPlaylist), { headers: getHeaders() })
      .map((response: Response) => {   
        var res = response.json(); 
        if( res.success ==true){
          alert(res.message);
        } else{
          alert(res.message);         
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