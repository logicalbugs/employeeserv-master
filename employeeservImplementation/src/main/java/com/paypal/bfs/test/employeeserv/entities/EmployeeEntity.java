package com.paypal.bfs.test.employeeserv.entities;

import com.paypal.bfs.test.employeeserv.api.model.Employee;

import javax.persistence.*;

@Table(name = "TBL_EMPLOYEES")
@Entity
public class EmployeeEntity {

    @GeneratedValue
    @Id
    @Column(name = "id")
    private Long id;
    /**
     * first name
     * (Required)
     *
     */
    @Column(name="first_name")
    private String firstName;
    /**
     * last name
     * (Required)
     *
     */
    @Column(name="last_name")
    private String lastName;
    /**
     * date of birth
     * (Required)
     *
     */
    @Column(name="date_of_birth")
    private String dateOfBirth;
    /**
     * address
     * (Required)
     *
     */
    @OneToOne(targetEntity = AddressEntity.class, cascade = CascadeType.ALL)
    private AddressEntity address;

    public EmployeeEntity() {
    }
    public EmployeeEntity(Employee employee) {
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.dateOfBirth = employee.getDateOfBirth();
        this.address = new AddressEntity(employee.getAddress());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    public Employee toEmployee(){
        Employee employee = new Employee();
        employee.setId(this.id.intValue());
        employee.setFirstName(this.firstName);
        employee.setLastName(this.lastName);
        employee.setDateOfBirth(this.dateOfBirth);
        employee.setAddress(this.address.toAddress());
        return employee;
    }
}
