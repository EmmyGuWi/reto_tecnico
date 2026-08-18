import { Routes } from '@angular/router';
import { Loggin } from './loggin/loggin';
import { Form } from './form/form';
export const routes: Routes = [
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  },
  {
    path: 'login',
    component: Loggin
  },
  {
    path: 'form',
    component: Form
  },
  {
    path: '**',
    redirectTo: 'login'
  }

];
