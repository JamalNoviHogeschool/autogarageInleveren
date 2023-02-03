package com.autogarage.model.domain.customer;

import com.autogarage.model.domain.car.Car;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "CUSTOMER_ID")
    private Integer customerID;

    @Column(name = "NAME", nullable = false, length = 50)
    private String name;

    @Column(name = "SURNAME", length = 50)
    private String surname;

    @Column(name = "ADDRESS", length = 512)
    private String address;

    @Column(name = "ZIP_CODE")
    private String zipCode;

    @Column(name = "CITY", length = 50)
    private String city;

    @Column(name = "PHONE_NUMBER", nullable = false, length = 20)
    private String phoneNumber;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @OneToMany(mappedBy = "customer")
    private Set<Car> cars;

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Car> getCars() {
        if (cars == null) {
            cars = new HashSet<>();
        }
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    public void addCar(Car car) {
        car.setCustomer(this);
        getCars().add(car);
    }
}
