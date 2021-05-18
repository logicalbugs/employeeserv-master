package com.paypal.bfs.test.employeeserv.impl;

import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.repository.EmployeeRepository;
import com.paypal.bfs.test.employeeserv.service.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

public class EmployeeResourceImplTest extends AbstractTest {

    @MockBean
    private EmployeeService employeeService;
    @MockBean
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeResource employeeResource;
    String employee = "{\n" +
            "    \"first_name\":\"Manoj\",\n" +
            "    \"last_name\":\"Kumar\",\n" +
            "    \"date_of_birth\":\"1981-04-20\",\n" +
            "    \"address\":{\n" +
            "        \"line1\":\"Entry block 115, Waterville apartment\",\n" +
            "        \"line2\":\"Whitefield\",\n" +
            "        \"city\":\"Bangalore\",\n" +
            "        \"state\":\"karnataka\",\n" +
            "        \"country\":\"India\",\n" +
            "        \"zip_code\":\"560066\"\n" +
            "    }\n" +
            "}";
    String inValidEmployee = "{\n" +
            "    \"first_name\":\"\",\n" +
            "    \"last_name\":\"Kumar\",\n" +
            "    \"date_of_birth\":\"1981-04-20\",\n" +
            "    \"address\":{\n" +
            "        \"line1\":\"Entry block 115, Waterville apartment\",\n" +
            "        \"line2\":\"Whitefield\",\n" +
            "        \"city\":\"Bangalore\",\n" +
            "        \"state\":\"karnataka\",\n" +
            "        \"country\":\"India\",\n" +
            "        \"zip_code\":\"560066\"\n" +
            "    }\n" +
            "}";
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void whenPostRequestToEmployeesAndValidEmployee_thenCorrectResponse() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/bfs/employees")
                .content(employee)
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(org.springframework.http.MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void whenPostRequestToEmployeesAndInValidEmployee_thenCorrectResponse() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/bfs/employees")
                .content(inValidEmployee)
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void whenGetRequestToEmployeesAndValidEmployeeId_thenCorrectResponse() throws Exception {
        final Optional<Employee> employee = Optional.of(mapFromJson(this.employee, Employee.class));
        Mockito.when(employeeService.employeeById(Mockito.anyString())).thenReturn(employee);
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/bfs/employees/10001")
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(org.springframework.http.MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void whenGetRequestToEmployeesAndInValidEmployeeId_thenCorrectResponse() throws Exception {
        final Optional<Employee> employee = Optional.empty();
        Mockito.when(employeeService.employeeById(Mockito.anyString())).thenReturn(employee);
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/bfs/employees/10011")
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
