import {Component, OnInit} from '@angular/core';
import {RouterModule} from '@angular/router';

@Component({
  selector: 'app-user-register',
  imports: [RouterModule],
  templateUrl: './user-register.component.html',
  styleUrl: './user-register.component.css'
})
export default class UserRegisterComponent implements OnInit{


  ngOnInit(): void {
  }

}
