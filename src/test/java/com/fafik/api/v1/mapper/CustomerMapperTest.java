package com.fafik.api.v1.mapper;

import com.fafik.api.v1.model.CustomerDTO;
import com.fafik.domain.Customer;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class CustomerMapperTest {

    private static final String FIRST_NAME="Tom";
    private static final String LAST_NAME="Riddle";
    private static final Long ID=2L;

    @Test
    public void customerToCustomerDTO() {
        Customer customer = new Customer();
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);
        customer.setId(ID);

        CustomerDTO customerDTO = CustomerMapper.INSTANCE.customerToCustomerDTO(customer);

        assertEquals(ID,customerDTO.getId());
        assertEquals(FIRST_NAME,customerDTO.getFirstName());
        assertEquals(LAST_NAME,customerDTO.getLastName());
    }

    @Test
    public void customerDtoToCustomer() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(FIRST_NAME);
        customerDTO.setLastName(LAST_NAME);
        customerDTO.setId(ID);

        Customer customer = CustomerMapper.INSTANCE.customerDtoToCustomer(customerDTO);

        assertEquals(ID,customer.getId());
        assertEquals(FIRST_NAME,customer.getFirstName());
        assertEquals(LAST_NAME,customer.getLastName());
    }
}