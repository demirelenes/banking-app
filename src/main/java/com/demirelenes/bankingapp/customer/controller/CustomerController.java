package com.demirelenes.bankingapp.customer.controller;

import com.demirelenes.bankingapp.account.controller.dto.AccountResponseDTO;
import com.demirelenes.bankingapp.account.entity.Account;
import com.demirelenes.bankingapp.customer.controller.dto.*;
import com.demirelenes.bankingapp.customer.entity.Customer;
import com.demirelenes.bankingapp.customer.service.ICustomerService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final ICustomerService customerService;
    private final ModelMapper mapper;

    public CustomerController(ICustomerService customerService, ModelMapper mapper) {
        this.customerService = customerService;
        this.mapper = mapper;
    }

    @PostMapping
    public CustomerResponseDTO createCustomer(@Valid @RequestBody CustomerRequestDTO newCustomer) {
        Customer customerEntity = mapper.map(newCustomer, Customer.class);
        Customer createdCustomer = customerService.createCustomer(customerEntity);
        return mapper.map(createdCustomer, CustomerResponseDTO.class);
    }

    @GetMapping
    public List<CustomerResponseDTO> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return customers.stream()
                .map(customer -> mapper.map(customer, CustomerResponseDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CustomerResponseDTO getCustomerById(@PathVariable("id") Long id) {
        Customer customer = customerService.getCustomerById(id);
        return mapper.map(customer, CustomerResponseDTO.class);
    }

    @GetMapping("/{id}/accounts")
    public List<AccountResponseDTO> getAccountsOfCustomer(@PathVariable("id") Long id) {
        List<Account> accounts = customerService.getAccountsOfCustomer(id);
        return accounts.stream()
                .map(account -> mapper.map(account, AccountResponseDTO.class))
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void deleteCustomerById(@PathVariable("id") Long id) {
        customerService.deleteCustomerById(id);
    }
}
