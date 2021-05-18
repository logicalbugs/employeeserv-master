package com.paypal.bfs.test.employeeserv.service.impl;

import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.entities.EmployeeEntity;
import com.paypal.bfs.test.employeeserv.repository.EmployeeRepository;
import com.paypal.bfs.test.employeeserv.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Optional<Employee> employeeById(String id) {
        final Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(Long.valueOf(id));
        if(employeeEntity.isPresent()) {
            return Optional.of(employeeEntity.get().toEmployee());
        }
        return Optional.empty();
    }

    @Override
    public Integer save(EmployeeEntity employee) {
        EmployeeEntity employeeEntity = employeeRepository.findByFirstNameAndLastNameAndDateOfBirth(employee.getFirstName(), employee.getLastName(), employee.getDateOfBirth());
        if(Objects.nonNull(employeeEntity))
            return employeeEntity.getId().intValue();
        return employeeRepository.save(employee).getId().intValue();
    }
}
