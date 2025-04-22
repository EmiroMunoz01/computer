import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {User_Entity} from '../model/user_entity.interface';

@Injectable({
  providedIn: 'root',
})


export class AuthService {


  private http = inject(HttpClient)
  private apiURL = 'http://localhost:8080/store'


  createUser(user_entity: User_Entity) {
    return this.http.post<User_Entity>(`${this.apiURL}/user/create`, user_entity)
  }


}
