package com.autogarage.model.dto.car;

import com.autogarage.model.domain.car.Car;
import com.autogarage.model.domain.part.Part;
import com.autogarage.model.dto.part.PartDTO;

import java.util.ArrayList;
import java.util.List;

public class CarDTO {

    private Integer carID;

    private String licensePlate;

    private String brand;

    private String model;

    private Integer versionYear;

    private Integer customerID;

    private List<PartDTO> partDTOList;

    public CarDTO() {
    }

    public CarDTO(Car car) {
        this.carID = car.getCarID();
        this.licensePlate = car.getLicensePlate();
        this.brand = car.getBrand();
        this.model = car.getModel();
        this.versionYear = car.getVersionYear();
        this.customerID = car.getCustomer().getCustomerID();

        for (Part part: car.getParts()) {
            this.getPartDTOList().add(new PartDTO(part));
        }

    }

    public Integer getCarID() {
        return carID;
    }

    public void setCarID(Integer carID) {
        this.carID = carID;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getVersionYear() {
        return versionYear;
    }

    public void setVersionYear(Integer versionYear) {
        this.versionYear = versionYear;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public List<PartDTO> getPartDTOList() {
        if (partDTOList == null) {
            partDTOList = new ArrayList<>();
        }
        return partDTOList;
    }

    public void setPartDTOList(List<PartDTO> partDTOList) {
        this.partDTOList = partDTOList;
    }
}
