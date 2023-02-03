package com.autogarage.model.domain.part;

import com.autogarage.model.domain.car.Car;
import com.autogarage.model.domain.repair.Repair;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "part")
public class Part {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "PART_ID")
    private Integer partID;

    @Column(name = "NAME")
    private String name;

    @Column(name = "MODEL")
    private String model;

    @Column(name = "PRICE")
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CAR_ID")
    private Car car;

    @OneToMany(mappedBy = "part")
    private Set<Repair> repairs;

    public Integer getPartID() {
        return partID;
    }

    public void setPartID(Integer partID) {
        this.partID = partID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Set<Repair> getRepairs() {
        if (repairs == null) {
            repairs = new HashSet<>();
        }
        return repairs;
    }

    public void setRepairs(Set<Repair> repairs) {
        this.repairs = repairs;
    }
}
