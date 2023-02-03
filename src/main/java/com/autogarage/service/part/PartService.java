package com.autogarage.service.part;

import com.autogarage.dao.part.PartDao;
import com.autogarage.dao.repair.RepairDao;
import com.autogarage.exception.AppsException;
import com.autogarage.model.domain.car.Car;
import com.autogarage.model.domain.part.Part;
import com.autogarage.model.dto.part.PartDTO;
import com.autogarage.service.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PartService {

    @Autowired
    private PartDao partDao;

    @Autowired
    private CarService carService;

    @Autowired
    private RepairDao repairDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public PartDTO savePart(PartDTO savePartDTO) throws AppsException {
        Part part = new Part();

        this.getPartByPartDTO(part, savePartDTO);

        part = this.partDao.saveAndFlush(part);

        return new PartDTO(part);
    }

    private void getPartByPartDTO(Part part, PartDTO partDTO) throws AppsException {
        part.setName(partDTO.getName());
        part.setModel(partDTO.getModel());
        part.setPrice(partDTO.getPrice());

        Car car = this.carService.getCarEntityByID(partDTO.getCarID());
        part.setCar(car);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public PartDTO updatePart(Integer partID, PartDTO updatePartDTO) throws AppsException {
        if (partID != null) {
            if (this.partDao.existsById(partID)) {
                Part part = this.partDao.getReferenceById(partID);

                this.getPartByPartDTO(part, updatePartDTO);

                part = this.partDao.saveAndFlush(part);

                return new PartDTO(part);
            } else {
                throw new AppsException("Can't find part with given ID");
            }
        } else {
            throw new AppsException("Part ID is not valid");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<PartDTO> getAllParts() throws AppsException {
        List<Part> partList = this.partDao.findAll();
        List<PartDTO> partDTOList = new ArrayList<>();

        for (Part part : partList) {
            partDTOList.add(new PartDTO(part));
        }

        return partDTOList;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public PartDTO getPartByID(Integer partID) throws AppsException {
        if (partID != null) {
            if (this.partDao.existsById(partID)) {
                Part part = this.partDao.getReferenceById(partID);
                return new PartDTO(part);
            } else {
                throw new AppsException("Can't find part with given ID");
            }
        } else {
            throw new AppsException("Part ID is not valid");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Part getPartEntityByID(Integer partID) throws AppsException {
        if (partID != null) {
            if (this.partDao.existsById(partID)) {
                Part part = this.partDao.getReferenceById(partID);
                return part;
            } else {
                throw new AppsException("Can't find part with given ID");
            }
        } else {
            throw new AppsException("Part ID is not valid");

        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deletePart(Integer partID) throws AppsException {
        if (this.partDao.existsById(partID)) {
            Part part = this.partDao.getReferenceById(partID);

            this.repairDao.deleteAll(part.getRepairs());

            this.partDao.deleteById(partID);
        } else {
            throw new AppsException("Can't find part with given ID");
        }
    }
}
