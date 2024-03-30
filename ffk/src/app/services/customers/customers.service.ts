import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {CustomerDTO} from "../../models/customer-dto";
import {Observable} from "rxjs";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class CustomersService {

  private readonly customerUrl:string = `${environment.api.baseUrl}/api/v1/customers`;


  constructor(
    private http :HttpClient
  ) { }

  listOfCustomers():Observable<CustomerDTO[]>{
    return this.http.get<CustomerDTO[]>(this.customerUrl)


  }


}
