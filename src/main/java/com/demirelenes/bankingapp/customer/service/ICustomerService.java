package com.demirelenes.bankingapp.customer.service;

import com.demirelenes.bankingapp.account.entity.Account;
import com.demirelenes.bankingapp.customer.entity.Customer;

import java.util.List;

public interface ICustomerService {

    Customer createCustomer(Customer customer);

    List<Customer> getAllCustomers();

    Customer getCustomerById(Long id);

    List<Account> getAccountsOfCustomer(Long id);

    void deleteCustomerById(Long id);
}
