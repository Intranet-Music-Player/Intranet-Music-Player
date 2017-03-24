import { Injectable } from '@angular/core';
import { Http, Response, Headers , RequestOptions} from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { User } from 'app/entities/entities';

@Injectable()
export class UserService {
  private userlistUrl : string = 'http://localhost:8080/app/users';
  private userRegistrationUrl : string = 'http://localhost:8080/app/registration';
  private userloginUrl : string = 'http://localhost:8080/app/login';

  constructor(private http : Http){}

  addNewUser( user : User ) : Observable<User> {
    return this.http.post(this.userRegistrationUrl, JSON.stringify(user), {headers: getHeaders()})
                    .map((res : Response) => res.json())
                    .catch(handleError);      
  } 
  checkUserLogin( user : User ){
    return this.http.post(this.userloginUrl,JSON.parse(JSON.stringify(user || null )), {headers: getHeaders()})
                    .map((res : Response) => res.json())
                    .catch(handleError);     
  }
  
  }
  function getHeaders(){
        let headers = new Headers();
        headers.append('Accept', 'application/json' );
        headers.append('Content-Type','application/json');
        return headers;
  }

  function handleError (error: any) {
        let errorMsg = error.message || 'ERROR -1-0-1'
        console.error(errorMsg);
        return Observable.throw(errorMsg);
  }

