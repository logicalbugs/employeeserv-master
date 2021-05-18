package com.paypal.bfs.test.employeeserv.entities;

import com.paypal.bfs.test.employeeserv.api.model.Address;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Table(name = "TBL_ADDRESS")
@Entity
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @Column(name = "id")
    private Long id;
    /**
     *
     * (Required)
     *
     */
    @Column(name="line1")
    private String line1;
    @Column(name="line2")
    private String line2;
    /**
     *
     * (Required)
     *
     */
    @Column(name="city")
    private String city;
    /**
     *
     * (Required)
     *
     */
    @Column(name="state")
    private String state;
    /**
     *
     * (Required)
     *
     */
    @Column(name="country")
    private String country;
    /**
     *
     * (Required)
     *
     */
    @Column(name="zip_code")
    private Integer zipCode;

    public AddressEntity() {
    }
    public AddressEntity(Address address) {
        this.city = address.getCity();
        this.line1 = address.getLine1();
        this.line2 = address.getLine2();
        this.country = address.getCountry();
        this.state = address.getState();
        this.zipCode = address.getZipCode();
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Address toAddress() {
        Address address = new Address();
        address.setCity(this.city);
        address.setCountry(this.country);
        address.setLine1(this.line1);
        address.setLine2(this.line2);
        address.setZipCode(this.zipCode);
        return address;
    }
}
