package com.fafik.controller.v1;

import com.fafik.api.v1.model.CategoryDTO;
import com.fafik.api.v1.model.CustomerDTO;
import com.fafik.services.CategoryService;
import com.fafik.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest extends AbstractRestController{

    private static final String FIRST_NAME ="TOM";
    private static final String LAST_NAME = "RIDDLE";
    private static final Long ID=2L;

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    private MockMvc mockMvc;
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void testListCustomers() throws  Exception{
        CustomerDTO customer1 = new CustomerDTO();
        customer1.setId(1L);
        customer1.setFirstName(FIRST_NAME);

        CustomerDTO customer2 = new CustomerDTO();
        customer2.setId(2L);


        List<CustomerDTO> customers = Arrays.asList(customer1,customer2);

        when(customerService.getAllCustomers()).thenReturn(customers);

        mockMvc.perform(get(CustomerController.BASE_URL+"/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers",hasSize(2)));



    }

    @Test
    public void testGetCustomerById() throws Exception{
        CustomerDTO customer1 = new CustomerDTO();
        customer1.setId(ID);
        customer1.setFirstName(FIRST_NAME);
        customer1.setLastName(LAST_NAME);



        when(customerService.getCustomerById(anyLong())).thenReturn(customer1);

        mockMvc.perform(get(CustomerController.BASE_URL+"/"+ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName",equalTo(FIRST_NAME)));

    }

    @Test
    public void createNewCustomer() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("Fred");
        customerDTO.setLastName("Flinstone");

        CustomerDTO returnDto = new CustomerDTO();
        returnDto.setFirstName(customerDTO.getFirstName());
        returnDto.setLastName(customerDTO.getLastName());
        returnDto.setCustomerUrl(CustomerController.BASE_URL+"/1");

        when(customerService.createNewCustomer(customerDTO)).thenReturn(returnDto);

        mockMvc.perform(MockMvcRequestBuilders.post(CustomerController.BASE_URL+"/")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(customerDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName",equalTo("Fred")))
                .andExpect(jsonPath("$.lastName",equalTo("Flinstone")))
                .andExpect(jsonPath("$.customerUrl",equalTo(CustomerController.BASE_URL+"/1")));
    }

    @Test
    public void testUpdateCustomer() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("Fred");
        customerDTO.setLastName("Flinstone");

        CustomerDTO returnDto = new CustomerDTO();
        returnDto.setFirstName(customerDTO.getFirstName());
        returnDto.setLastName(customerDTO.getLastName());
        returnDto.setCustomerUrl(CustomerController.BASE_URL+"/1");

        when(customerService.saveCustomerDTO(anyLong(),any(CustomerDTO.class))).thenReturn(returnDto);

        mockMvc.perform(put(CustomerController.BASE_URL+"/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(customerDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName",equalTo("Fred")))
                .andExpect(jsonPath("$.lastName",equalTo("Flinstone")))
                .andExpect(jsonPath("$.customerUrl",equalTo(CustomerController.BASE_URL+"/1")));
    }

    @Test
    public void testPatchCustomer() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("Fred");

        CustomerDTO returnDto = new CustomerDTO();
        returnDto.setFirstName(customerDTO.getFirstName());
        returnDto.setLastName("Flinstone");
        returnDto.setCustomerUrl(CustomerController.BASE_URL+"/1");

        when(customerService.patchCustomer(anyLong(),any(CustomerDTO.class))).thenReturn(returnDto);

        mockMvc.perform(patch(CustomerController.BASE_URL+"/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName",equalTo("Fred")))
                .andExpect(jsonPath("$.lastName",equalTo("Flinstone")))
                .andExpect(jsonPath("$.customerUrl",equalTo(CustomerController.BASE_URL+"/1")));
    }

    @Test
    public void testDeleteCustomer() throws Exception{
        mockMvc.perform(delete(CustomerController.BASE_URL+"/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(customerService).deleteCustomerById(anyLong());
    }
}
