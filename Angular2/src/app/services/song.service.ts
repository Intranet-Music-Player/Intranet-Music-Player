import { Injectable } from '@angular/core';
import {Http, Response, Headers} from "@angular/http";
import {Observable} from "rxjs";
import {Song} from "../entities/entities";

@Injectable()
export class SongService {

  private songUrl: string = 'http://localhost:8080/app/songs';

  constructor(private http: Http) { }

  getSong() : Observable<Song[]> {
    return this.http.get(this.songUrl)
        .map((res : Response) => res.json())
        .catch(handleError);
  }

  private getHeaders(){
    let headers = new Headers();
    headers.append('Accept', 'application/json' );
    headers.append('Content-Type','application/json');
    return headers;
  }
}

function handleError (error : any ) {
  let errorMsg = error.message || 'ERROR';
  console.error(errorMsg);
  return Observable.throw(errorMsg)
}
