import {Component, inject, OnInit} from '@angular/core';
import {RouterModule} from '@angular/router';
import {FormBuilder} from '@angular/forms';

@Component({
  selector: 'app-user-register',
  imports: [RouterModule],
  templateUrl: './user-register.component.html',
  styleUrl: './user-register.component.css'
})
export default class UserRegisterComponent implements OnInit {

  //1 se inyectara el formulario
  private fb = inject(FormBuilder)

  ngOnInit(): void {
  }

}
