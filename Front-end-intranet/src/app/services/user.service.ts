import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Rx';
import { User } from 'app/entities/entities';


@Injectable()
export class UserService {
  private userlistUrl: string = 'http://localhost:8080/app/users';
  private userRegistrationUrl: string = 'http://localhost:8080/app/registration';
  private userloginUrl: string = 'http://localhost:8080/app/login';
  private currentUserUrl : string = 'http://localhost:8080/app/userlogin';

  constructor(private http: Http) { }

  addNewUser(user: User): Observable<any> {
    return this.http.post(this.userRegistrationUrl, JSON.stringify(user), { headers: getHeaders() })
      .map((response: Response) =>  response.json())
      .catch(handleError);
  }

  checkUserLogin(user: User): Observable<any> {
    return this.http.post(this.userloginUrl, JSON.stringify(user), { headers: getHeaders() })
      .map((response: Response) => response.json())
      .catch(handleError);
  }

  getCurrentUser(currentUser : any) : Observable<any> {
    return this.http.post(this.currentUserUrl, JSON.stringify(currentUser),   { headers: getHeaders() })
    .map((response : Response ) => response.json())
    .catch(handleError);
  }
  
}

function getHeaders() {
  let headers = new Headers();
  headers.append('Accept', 'application/json');
  headers.append('Content-Type', 'application/json');
  return headers;
}

function handleError(error: any) {
  let errorMsg = error.message || 'SERVER ERROR'
  console.error(errorMsg);
  return Observable.throw(errorMsg);
}

