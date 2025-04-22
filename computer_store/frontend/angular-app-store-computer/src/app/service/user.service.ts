import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {User_Entity} from '../model/user_entity.interface';
import {User_Entity_Dto} from '../model/user_entity_dto.interface';


@Injectable({
  providedIn: 'root',
})

export class UserService {

  private http = inject(HttpClient)
  private apiURL = 'http://localhost:8080/store'

  createUser(user_entity_dto: User_Entity_Dto) {
    return this.http.post<User_Entity>(`${this.apiURL}/user/create`, user_entity_dto)
  }


}
