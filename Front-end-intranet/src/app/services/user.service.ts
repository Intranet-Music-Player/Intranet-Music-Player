import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Rx';
import { User } from 'app/entities/entities';
import { Router } from '@angular/router';

@Injectable()
export class UserService {
  private userlistUrl: string = 'http://localhost:8080/app/users';
  private userRegistrationUrl: string = 'http://localhost:8080/app/registration';
  private userloginUrl: string = 'http://localhost:8080/app/login';
  constructor(private http: Http, private router: Router) { }

  addNewUser(user: User): Observable<any> {
    return this.http.post(this.userRegistrationUrl, JSON.stringify(user), { headers: getHeaders() })
      .map((response: Response) => {
        var res = response.json();
        if (res.message === "WELCOME TO OUR APP"){
          this.router.navigate(['login']);
        } else {
          alert(res.message);
        }
      })
      .catch(handleError);
  }

  checkUserLogin(user: User): Observable<Response> {
    return this.http.post(this.userloginUrl, JSON.stringify(user), { headers: getHeaders() })
      .map((response: Response) => {
        var res = response.json();
        if (res.message === 'SUCCESFULL') {
          sessionStorage.setItem('currentUser',JSON.stringify(res.user));
          this.router.navigate(['home']);
        } else {
          alert("LOGIN OR PASSWORD INCORRECT")
          this.router.navigate(['login'])
        }
      })
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

