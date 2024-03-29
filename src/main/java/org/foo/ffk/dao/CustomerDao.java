package org.foo.ffk.dao;

import org.foo.ffk.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDao {
    List<Customer> listAllCustomers();

    Optional<Customer> selectCustomerById(Long id);

    void insertCustomer(Customer customer);

    boolean existsPersonWithEmail(String email);

    void deleteCustomer(Long id);

    boolean existsPersonWithId(Long id);

    void updateCustomer(Customer update);

    Optional<Customer> selectUserByEmail(String email);

}