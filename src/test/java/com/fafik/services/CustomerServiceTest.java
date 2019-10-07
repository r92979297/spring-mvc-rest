package com.fafik.services;

import com.fafik.api.v1.mapper.CategoryMapper;
import com.fafik.api.v1.mapper.CustomerMapper;
import com.fafik.api.v1.model.CategoryDTO;
import com.fafik.api.v1.model.CustomerDTO;
import com.fafik.domain.Category;
import com.fafik.domain.Customer;
import com.fafik.repositories.CategoryRepository;
import com.fafik.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class CustomerServiceTest {

    private static final String FIRST_NAME ="TOM";
    private static final String LAST_NAME = "RIDDLE";
    private static final Long ID=2L;

    private CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;


    @Before
    public void setUp()  {
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerServiceImpl( customerRepository, CustomerMapper.INSTANCE);
    }

    @Test
    public void getAllCustomers() {
        List<Customer> customers = Arrays.asList(new Customer(),new Customer(),new Customer());

        when(customerRepository.findAll()).thenReturn(customers);

        List<CustomerDTO> customerDTOS = customerService.getAllCustomers();

        assertEquals(3,customerDTOS.size());
    }

    @Test
    public void getCustomerByID() {
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);

        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));

        CustomerDTO customerDTO = customerService.getCustomerById(ID);

        assertEquals(FIRST_NAME, customerDTO.getFirstName());
        assertEquals(LAST_NAME, customerDTO.getLastName());
        assertEquals(ID,customerDTO.getId());
    }

    @Test
    public void createNewCustomer() {

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(FIRST_NAME);

        Customer savedCustomer = new Customer();
        savedCustomer.setFirstName(customerDTO.getFirstName());
        savedCustomer.setLastName((customerDTO.getLastName()));
        savedCustomer.setId(ID);

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        CustomerDTO savedDto = customerService.createNewCustomer(customerDTO);

        assertEquals(customerDTO.getFirstName(),savedDto.getFirstName());
        assertEquals("/api/v1/customer/"+ID,savedDto.getCustomerUrl());
    }
}
