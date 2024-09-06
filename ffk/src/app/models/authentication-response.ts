import {CustomerDTO} from "./customer-dto";


export interface AuthenticationResponse{
  token?:string ;
  refresh?:string;
  customerDTO?:CustomerDTO;
}
