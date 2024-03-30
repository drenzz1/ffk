import {Component, OnInit} from '@angular/core';
import {CustomersService} from "../../services/customers/customers.service";
import {CustomerDTO} from "../../models/customer-dto";

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrl: './customers.component.css'
})
export class CustomersComponent implements OnInit{
  customers: CustomerDTO[] = [];

  constructor(private customerservice:CustomersService) {
  }

  ngOnInit(): void {
    this.findAllCustomers()
  }
  private findAllCustomers(){
    this.customerservice.listOfCustomers()
      .subscribe({
        next:(data)=>{
          this.customers=data
          console.log(data);
        }
      })
  }
}
