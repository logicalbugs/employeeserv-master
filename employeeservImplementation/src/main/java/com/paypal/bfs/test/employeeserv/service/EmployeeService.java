package com.paypal.bfs.test.employeeserv.service;

import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.entities.EmployeeEntity;

import java.util.Optional;

public interface EmployeeService {

    Optional<Employee> employeeById(String id);

    Integer save(EmployeeEntity employee);
}
