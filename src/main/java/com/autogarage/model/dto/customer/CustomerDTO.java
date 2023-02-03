package com.autogarage.model.dto.customer;

import com.autogarage.model.domain.car.Car;
import com.autogarage.model.domain.customer.Customer;
import com.autogarage.model.dto.car.CarDTO;

import java.util.ArrayList;
import java.util.List;

public class CustomerDTO {

    private Integer customerID;

    private String name;

    private String surname;

    private String address;

    private String zipCode;

    private String city;

    private String phoneNumber;

    private String email;

    private List<CarDTO> carDTOList;

    public CustomerDTO() {
    }

    public CustomerDTO(Customer customer) {
        this.customerID = customer.getCustomerID();
        this.name = customer.getName();
        this.surname = customer.getSurname();
        this.address = customer.getAddress();
        this.zipCode = customer.getZipCode();
        this.city = customer.getCity();
        this.phoneNumber = customer.getPhoneNumber();
        this.email = customer.getPhoneNumber();

        for (Car car : customer.getCars()) {
            this.getCarDTOList().add(new CarDTO(car));
        }
    }

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

    public List<CarDTO> getCarDTOList() {
        if (carDTOList == null) {
            carDTOList = new ArrayList<>();
        }
        return carDTOList;
    }

    public void setCarDTOList(List<CarDTO> carDTOList) {
        this.carDTOList = carDTOList;
    }
}
