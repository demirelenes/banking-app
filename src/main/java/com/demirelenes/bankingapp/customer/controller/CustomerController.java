package com.demirelenes.bankingapp.customer.controller;

import com.demirelenes.bankingapp.account.controller.dto.AccountResponseDTO;
import com.demirelenes.bankingapp.account.entity.Account;
import com.demirelenes.bankingapp.customer.controller.dto.*;
import com.demirelenes.bankingapp.customer.entity.Customer;
import com.demirelenes.bankingapp.customer.service.ICustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
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
    public CustomerResponseDTO createCustomer(@RequestBody CustomerRequestDTO newCustomer) {
        Customer customerEntity = mapper.map(newCustomer, Customer.class);
        Customer createdCustomer = customerService.createCustomer(customerEntity);
        return mapper.map(createdCustomer, CustomerResponseDTO.class);
    }

    @GetMapping("/{id}")
    public CustomerResponseDTO getCustomerById(@PathVariable("id") Long id) {
        Customer customer = customerService.getCustomerById(id);
        return mapper.map(customer, CustomerResponseDTO.class);
    }

    @GetMapping("/{id}/accounts")
    public Set<AccountResponseDTO> getAccountsOfCustomer(@PathVariable("id") Long id) {
        Set<Account> accounts = customerService.getCustomerById(id).getAccounts();
        return accounts
                .stream()
                .map(account -> mapper.map(account, AccountResponseDTO.class))
                .collect(Collectors.toSet());
    }

    @DeleteMapping("/{id}")
    public void deleteCustomerById(@PathVariable("id") Long id) {
        customerService.deleteCustomerById(id);
    }
}
