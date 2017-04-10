import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { Playlist } from './../entities/entities';

@Injectable()
export class PlaylistService {

  private playlistUrl: string = 'http://localhost:8080/app/playlists';
  private playistNew: string = 'http://localhost:8080/app/playlist/add';
  private followPlaylist: string = 'http://localhost:8080/app/followPlaylist';
  private removePlaylist: string = 'http://localhost:8080/app/removePlaylist';
  constructor(private http: Http) { }

  getPlaylists(): Observable<Playlist[]> {
    return this.http.get(this.playlistUrl)
      .map((res: Response) => res.json())
      .catch(handleError)
  }
  addPlaylist(newPlaylist: any): Observable<any> {
    return this.http.post(this.playistNew, JSON.stringify(newPlaylist), { headers: getHeaders() })
      .map((response: Response) => response.json())
      .catch(handleError);
  }
  userFollowPlaylist(followRequest: any): Observable<any> {
    return this.http.post(this.followPlaylist, JSON.stringify(followRequest), { headers: getHeaders() })
      .map((response: Response) => response.json())
      .catch(handleError);
  }

  removeUserPlaylist(removeRequest: any): Observable<any> {
    return this.http.post(this.removePlaylist, JSON.stringify(removeRequest), { headers: getHeaders() })
      .map((response: Response) => response.json())
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