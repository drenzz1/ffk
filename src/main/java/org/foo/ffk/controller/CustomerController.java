package org.foo.ffk.controller;

import org.foo.ffk.dto.CustomerDTO;
import org.foo.ffk.jwt.JWTUtil;
import org.foo.ffk.service.CustomerService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;
    private final JWTUtil jwtUtil;


    public CustomerController(CustomerService customerService, JWTUtil jwtUtil) {
        this.customerService = customerService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping
    public List<CustomerDTO> getCustomers() {
        return customerService.listAllCustomers();
    }

    @GetMapping("{id}")
    public CustomerDTO getCustomer(@PathVariable("id") Long id) {
        return customerService.getCustomer(id);
    }
    @PostMapping
    public ResponseEntity<?> registerCustomer(@RequestBody CustomerRegistrationRequest request){

        customerService.addCustomer(request);
        String jwtToken = jwtUtil.issueToken(request.email(),"ROLE_USER");

        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION,jwtToken)
                .build();
    }
    @DeleteMapping("{id}")
    public void deleteCustomer(@PathVariable("id")Long id){
        customerService.deleteCustomerById(id);
    }
    @PutMapping("{id}")
    public void editCustomer(@PathVariable("id")Long id,@RequestBody CustomerUpdateRequest updateRequest){
        customerService.updateCustomer(id,updateRequest);
    }

}