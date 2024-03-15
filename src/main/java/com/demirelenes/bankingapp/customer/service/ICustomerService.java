package com.demirelenes.bankingapp.customer.service;

import com.demirelenes.bankingapp.customer.entity.Customer;

public interface ICustomerService {

    Customer createCustomer(Customer customer);

    Customer getCustomerById(Long id);

    void deleteCustomerById(Long id);
}
