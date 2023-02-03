package com.autogarage.model.domain.car;

import com.autogarage.model.domain.customer.Customer;
import com.autogarage.model.domain.part.Part;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "CAR_ID")
    private Integer carID;

    @Column(name = "LICENSE_PLATE", unique = true, nullable = false)
    private String licensePlate;

    @Column(name = "BRAND", length = 100)
    private String brand;

    @Column(name = "MODEL", length = 100)
    private String model;

    @Column(name = "VERSION_YEAR")
    private Integer versionYear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    @OneToMany(mappedBy = "car")
    private Set<Part> parts;

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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<Part> getParts() {
        if (parts == null) {
            parts = new HashSet<>();
        }
        return parts;
    }

    public void setParts(Set<Part> parts) {
        this.parts = parts;
    }
}
