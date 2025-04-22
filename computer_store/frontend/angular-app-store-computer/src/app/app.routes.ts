import { Routes } from '@angular/router';

export const routes: Routes = [

  {
    path: '',
    loadComponent: () => import('./home-page/home-page.component'),
  },

  {
    path: 'user/register',
    loadComponent: () => import('./user-register/user-register.component'),
  },

  {
    path: 'user/login',
    loadComponent: () => import('./user-login/user-login.component'),
  }


];
