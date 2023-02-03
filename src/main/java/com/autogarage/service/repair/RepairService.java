package com.autogarage.service.repair;

import com.autogarage.dao.repair.RepairDao;
import com.autogarage.exception.AppsException;
import com.autogarage.model.domain.part.Part;
import com.autogarage.model.domain.repair.Repair;
import com.autogarage.model.dto.repair.RepairDTO;
import com.autogarage.service.part.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RepairService {

    @Autowired
    private RepairDao repairDao;

    @Autowired
    private PartService partService;

    @Transactional(propagation = Propagation.REQUIRED)
    public RepairDTO saveRepair(RepairDTO saveRepairDTO) throws AppsException {
        Repair repair = new Repair();

        this.getRepairByRepairDTO(repair, saveRepairDTO);

        repair = this.repairDao.saveAndFlush(repair);

        return new RepairDTO(repair);
    }

    private void getRepairByRepairDTO(Repair repair, RepairDTO repairDTO) throws AppsException {
        repair.setName(repairDTO.getName());
        repair.setPrice(repairDTO.getPrice());

        Part part = this.partService.getPartEntityByID(repairDTO.getPartID());
        repair.setPart(part);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public RepairDTO updateRepair(Integer repairID, RepairDTO updateRepairDTO) throws AppsException {
        if (repairID != null) {
            if (this.repairDao.existsById(repairID)) {
                Repair repair = this.repairDao.getReferenceById(repairID);

                this.getRepairByRepairDTO(repair, updateRepairDTO);

                return new RepairDTO(repair);
            } else {
                throw new AppsException("Can't find repair with given ID");
            }
        } else {
            throw new AppsException("Repair ID is not valid");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public RepairDTO getRepairByID(Integer repairID) throws AppsException {
        if (repairID != null) {
            if (this.repairDao.existsById(repairID)) {
                Repair repair = this.repairDao.getReferenceById(repairID);

                return new RepairDTO(repair);
            } else {
                throw new AppsException("Can't find repair with given ID");
            }
        } else {
            throw new AppsException("Repair ID is not valid");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<RepairDTO> getAllRepairs() throws AppsException {
        List<Repair> repairList = this.repairDao.findAll();
        List<RepairDTO> repairDTOList = new ArrayList<>();

        for (Repair repair : repairList) {
            repairDTOList.add(new RepairDTO(repair));
        }

        return repairDTOList;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteRepair(Integer repairID) throws AppsException {
        if (this.repairDao.existsById(repairID)) {
            this.repairDao.deleteById(repairID);
        } else {
            throw new AppsException("Can't find repair with given ID");
        }
    }
}
