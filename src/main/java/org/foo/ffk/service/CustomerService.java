package org.foo.ffk.service;

import org.foo.ffk.controller.CustomerRegistrationRequest;
import org.foo.ffk.controller.CustomerUpdateRequest;
import org.foo.ffk.dao.CustomerDao;
import org.foo.ffk.dto.CustomerDTO;
import org.foo.ffk.exception.DuplicateResourceException;
import org.foo.ffk.exception.RequestValidationException;
import org.foo.ffk.exception.ResourceNotFoundException;
import org.foo.ffk.mapper.CustomerDTOMapper;
import org.foo.ffk.model.Customer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CustomerService {
    private final CustomerDao customerDao;
    private final PasswordEncoder passwordEncoder;

    public CustomerService(CustomerDao customerDao, PasswordEncoder passwordEncoder, CustomerDTOMapper customerDTOMapper) {
        this.customerDao = customerDao;
        this.passwordEncoder = passwordEncoder;
        this.customerDTOMapper = customerDTOMapper;
    }

    private final CustomerDTOMapper customerDTOMapper;




    public List<CustomerDTO> listAllCustomers(){
        return customerDao.listAllCustomers().
                stream().map(
                customerDTOMapper
                ).collect(Collectors.toList());
    }
    public CustomerDTO getCustomer(Long id)  {
        return  customerDao
                .selectCustomerById(id)
                .map(customerDTOMapper)
                .orElseThrow(()->new ResourceNotFoundException("Customer with id [%s] not found ".formatted(id)));
    }
    public void addCustomer(CustomerRegistrationRequest customerRegistrationRequest){
        if (customerDao.existsPersonWithEmail(customerRegistrationRequest.email())){
            throw new DuplicateResourceException(
                    "email already exists "
            );
        }
        customerDao.insertCustomer(new Customer(customerRegistrationRequest.name(), customerRegistrationRequest.email(), passwordEncoder.encode(customerRegistrationRequest.password()), customerRegistrationRequest.age(), customerRegistrationRequest.gender()));
    }

    public void deleteCustomerById(Long id) {
        if (!customerDao.existsPersonWithId(id)){
            throw new ResourceNotFoundException("customer with id [%s] not found ".formatted(id));
        }
        customerDao.deleteCustomer(id);
    }

    public void updateCustomer(Long id, CustomerUpdateRequest updateRequest) {

        Customer customer = customerDao.selectCustomerById(id).
                orElseThrow(()->new ResourceNotFoundException("customer with id [%s] not found".formatted(id)));

        boolean change = false;

        if (updateRequest.name()!= null && !updateRequest.name().equals(customer.getName())){
            customer.setName(updateRequest.name());
            change = true;
        }
        if (updateRequest.age()!=null && !updateRequest.age().equals(customer.getAge())){
            customer.setAge(updateRequest.age());
            change = true;
        }
        if (updateRequest.email()!=null && !updateRequest.email().equals(customer.getEmail())){
            if (customerDao.existsPersonWithEmail(updateRequest.email())){
                throw new DuplicateResourceException(
                        "email already taken"
                );
            }
            customer.setEmail(updateRequest.email());
        }
        if (!change){
            throw new RequestValidationException(
                    "no data changes found "
            );
        }
        customerDao.updateCustomer(customer);
    }
}
