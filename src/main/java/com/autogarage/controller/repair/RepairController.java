package com.autogarage.controller.repair;

import com.autogarage.constants.AppsConstants;
import com.autogarage.exception.AppsException;
import com.autogarage.model.common.ResponseDTO;
import com.autogarage.model.dto.repair.RepairDTO;
import com.autogarage.service.repair.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/repair")
public class RepairController {

    @Autowired
    private RepairService repairService;

    @PostMapping(value = "/saveRepair", headers = "Accept=application/json")
    public ResponseEntity<ResponseDTO<RepairDTO>> saveRepair(@RequestBody RepairDTO saveRepairDTO) {
        ResponseDTO<RepairDTO> response = new ResponseDTO<>();
        HttpStatus httpStatus;

        RepairDTO RepairDTO;
        try {
            RepairDTO = this.repairService.saveRepair(saveRepairDTO);

            response.setResult(RepairDTO);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
            httpStatus = HttpStatus.CREATED;
        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            response.setAppsErrorMessages(e.getAppsErrorMessages());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @PutMapping("/updateRepair/{repairID}")
    public ResponseEntity<ResponseDTO<RepairDTO>> updateRepair(@PathVariable Integer repairID, @RequestBody RepairDTO updateRepairDTO) {
        ResponseDTO<RepairDTO> response = new ResponseDTO<>();
        HttpStatus httpStatus;

        try {
            RepairDTO RepairDTO = this.repairService.updateRepair(repairID, updateRepairDTO);

            response.setResult(RepairDTO);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
            httpStatus = HttpStatus.CREATED;

        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            response.setAppsErrorMessages(e.getAppsErrorMessages());
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @GetMapping(value = "/getRepairByID/{repairID}", headers = "Accept=application/json")
    public ResponseEntity<ResponseDTO<RepairDTO>> getRepairByID(@PathVariable Integer repairID) {
        ResponseDTO<RepairDTO> response = new ResponseDTO<>();
        HttpStatus httpStatus;

        try {
            RepairDTO RepairDTO = this.repairService.getRepairByID(repairID);

            response.setResult(RepairDTO);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
            httpStatus = HttpStatus.OK;
        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            response.setAppsErrorMessages(e.getAppsErrorMessages());
            httpStatus = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @GetMapping(value = "/getAllRepairs", headers = "Accept=application/json")
    public ResponseEntity<ResponseDTO<List<RepairDTO>>> getAllCustomers() {
        ResponseDTO<List<RepairDTO>> response = new ResponseDTO<>();
        HttpStatus httpStatus;

        try {
            List<RepairDTO> RepairDTOList = this.repairService.getAllRepairs();

            response.setResult(RepairDTOList);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
            httpStatus = HttpStatus.OK;
        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            response.setAppsErrorMessages(e.getAppsErrorMessages());
            httpStatus = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @DeleteMapping("/deleteRepair/{repairID}")
    public ResponseEntity<ResponseDTO<Boolean>> deleteRepair(@PathVariable Integer repairID) {
        ResponseDTO<Boolean> response = new ResponseDTO<>();
        HttpStatus httpStatus;

        try {
            this.repairService.deleteRepair(repairID);
            response.setResult(true);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
            httpStatus = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            response.setResult(false);
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(response, httpStatus);
    }
}
