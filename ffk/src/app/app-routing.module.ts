import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./components/login/login.component";
import {CustomersComponent} from "./components/customers/customers.component";
import {AuthGuard} from "./services/guard/access-guard.service";

const routes: Routes = [
  {
    path: 'login', component: LoginComponent,
  },
  {
    path: 'customers', component: CustomersComponent,
    canActivate:[AuthGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
