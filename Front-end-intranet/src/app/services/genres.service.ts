import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { Genres } from './../entities/entities';

@Injectable()
export class GenresService {

  private genereListUrl: string = 'http://localhost:8080/app/generes'

  constructor(private http: Http) { }

  getGenres(): Observable<Genres[]> {
    return this.http.get(this.genereListUrl)
      .map((res: Response) => res.json())
      .catch(handleError)
  }
}

function getHeaders() {
  let headers = new Headers();
  headers.append('Accept', 'application/json');
  return headers;
}

function handleError(error: any) {
  let errorMsg = error.message || 'ERROR -1-0-1'
  console.error(errorMsg);
  return Observable.throw(errorMsg);
}