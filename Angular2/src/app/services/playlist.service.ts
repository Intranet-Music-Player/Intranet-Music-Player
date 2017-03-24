import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { Playlist } from './../entities/entities';

@Injectable()
export class PlaylistService {

  private playlistUrl: string = 'http://localhost:8080/app/playlists';
  private playistNew: string = 'http://localhost:8080/app/newPlaylist';

  constructor(private http: Http) { }

  getPlaylists(): Observable<Playlist[]> {
    return this.http.get(this.playlistUrl)
      .map((res: Response) => res.json())
      .catch(handleError)
  }

}
function handleError (error: any) {
  let errorMsg = error.message || 'ERROR -1-0-1'
  console.error(errorMsg);
  return Observable.throw(errorMsg);
}
