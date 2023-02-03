package com.autogarage.model.dto.part;

import com.autogarage.model.domain.part.Part;
import com.autogarage.model.domain.repair.Repair;
import com.autogarage.model.dto.repair.RepairDTO;

import java.util.ArrayList;
import java.util.List;

public class PartDTO {

    private Integer partID;

    private String name;

    private String model;

    private Double price;

    private Integer carID;

    private List<RepairDTO> repairDTOList;

    public PartDTO() {
    }

    public PartDTO(Part part) {
        this.partID = part.getPartID();
        this.name = part.getName();
        this.model = part.getModel();
        this.price = part.getPrice();
        this.carID = part.getCar().getCarID();

        for (Repair repair : part.getRepairs()) {
            this.getRepairDTOList().add(new RepairDTO(repair));
        }
    }

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

    public Integer getCarID() {
        return carID;
    }

    public void setCarID(Integer carID) {
        this.carID = carID;
    }

    public List<RepairDTO> getRepairDTOList() {
        if (repairDTOList == null) {
            repairDTOList = new ArrayList<>();
        }
        return repairDTOList;
    }

    public void setRepairDTOList(List<RepairDTO> repairDTOList) {
        this.repairDTOList = repairDTOList;
    }
}
