package com.autogarage.model.domain.repair;

import com.autogarage.model.domain.part.Part;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "repair")
public class Repair {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "REPAIR_ID")
    private Integer repairID;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PART_ID")
    private Part part;

    public Integer getRepairID() {
        return repairID;
    }

    public void setRepairID(Integer repairID) {
        this.repairID = repairID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }
}
