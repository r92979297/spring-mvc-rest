package com.fafik.services;

import com.fafik.api.v1.mapper.CustomerMapper;
import com.fafik.api.v1.model.CustomerDTO;
import com.fafik.bootstrap.Bootstrap;
import com.fafik.domain.Customer;
import com.fafik.repositories.CategoryRepository;
import com.fafik.repositories.CustomerRepository;
import org.hibernate.internal.build.AllowSysOut;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;
import static org.mockito.AdditionalMatchers.not;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryServiceIT {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CategoryRepository categoryRepository;

    CustomerService customerService;

    @Before
    public void setUp() throws Exception {
        System.out.println("Loading Customer Data");
        System.out.println(customerRepository.findAll().size());

        Bootstrap bootstrap = new Bootstrap(categoryRepository,customerRepository);
        bootstrap.run();
        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
        System.out.println("-----> "+customerRepository.findAll().size());
    }

    @Test
    public void patchCustoemrUpdateFirstName() {
       String updatedName= "UpdatedName";
       long id = gatCustomerIdValue();

       Customer originalCustomer = customerRepository.getOne(id);
       assertNotNull(originalCustomer);

       String orginalFirsName = originalCustomer.getFirstName();
       String orginalLastName = originalCustomer.getLastName();

       CustomerDTO customerDTO = new CustomerDTO();
       customerDTO.setFirstName(updatedName);

       customerService.patchCustomer(id,customerDTO);

       Customer updatedCustomer = customerRepository.findById(id).get();

       assertNotNull(updatedName);
        assertEquals(updatedName,updatedCustomer.getFirstName());
        assertNotEquals(orginalFirsName,updatedCustomer.getFirstName());
        assertThat(orginalLastName,equalTo(updatedCustomer.getLastName()));
    }

    @Test
    public void patchCustomerUpdatedLastName() {
        String updatedName= "UpdatedName";
        long id = gatCustomerIdValue();

        Customer originalCustomer = customerRepository.getOne(id);
        assertNotNull(originalCustomer);

        String orginalFirsName = originalCustomer.getFirstName();
        String orginalLastName = originalCustomer.getLastName();

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setLastName(updatedName);

        customerService.patchCustomer(id,customerDTO);

        Customer updatedCustomer = customerRepository.findById(id).get();

        assertNotNull(updatedName);
        assertEquals(updatedName,updatedCustomer.getLastName());
        assertThat(orginalFirsName,equalTo(updatedCustomer.getFirstName()));
        assertNotEquals(orginalLastName,updatedCustomer.getLastName());
    }

    private Long gatCustomerIdValue(){
        List<Customer> customers = customerRepository.findAll();
        System.out.println("Customers Found: "+customers.size());

        //return first id
       return  customers.get(0).getId();
    }
}
