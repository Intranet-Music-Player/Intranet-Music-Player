import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { Playlist } from './../entities/entities';

@Injectable()
export class PlaylistService {

  private playlistUrl: string = 'http://localhost:8080/app/playlists';
  private playistNew: string = 'http://localhost:8080/app/newPlaylist';
  private searchUrl: string = 'http://localhost:8080/app/search';
  constructor(private http: Http) { }

  getPlaylists(): Observable<Playlist[]> {
    return this.http.get(this.playlistUrl)
      .map((res: Response) => res.json())
      .catch(handleError)
  }

  searchData(searchValue: string): Observable<Response> {
    return this.http.post(this.searchUrl, JSON.stringify(searchValue) , { headers: getHeaders() })
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