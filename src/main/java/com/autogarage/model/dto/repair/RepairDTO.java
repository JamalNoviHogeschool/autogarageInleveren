package com.autogarage.model.dto.repair;

import com.autogarage.model.domain.repair.Repair;

public class RepairDTO {

    private Integer repairID;

    private String name;

    private Double price;

    private Integer partID;

    public RepairDTO() {
    }

    public RepairDTO(Repair repair) {
        this.repairID = repair.getRepairID();
        this.name = repair.getName();
        this.price = repair.getPrice();
        this.partID = repair.getPart().getPartID();
    }

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

    public Integer getPartID() {
        return partID;
    }

    public void setPartID(Integer partID) {
        this.partID = partID;
    }
}
