package com.demirelenes.bankingapp.customer.service;

import com.demirelenes.bankingapp.account.entity.Account;
import com.demirelenes.bankingapp.customer.entity.Customer;
import com.demirelenes.bankingapp.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CustomerService implements ICustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).get(); // Exception will be added
    }

    @Override
    public Set<Account> getAccountsOfCustomer(Long id) {
        return getCustomerById(id).getAccounts();
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }
}
