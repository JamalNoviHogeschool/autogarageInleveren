package com.autogarage.controller.part;

import com.autogarage.constants.AppsConstants;
import com.autogarage.exception.AppsException;
import com.autogarage.model.common.ResponseDTO;
import com.autogarage.model.dto.part.PartDTO;
import com.autogarage.service.part.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/part")
public class PartController {

    @Autowired
    private PartService partService;

    @PostMapping(value = "/savePart", headers = "Accept=application/json")
    public ResponseEntity<ResponseDTO<PartDTO>> savePart(@RequestBody PartDTO savePartDTO) {
        ResponseDTO<PartDTO> response = new ResponseDTO<>();
        HttpStatus httpStatus;

        PartDTO partDTO;
        try {
            partDTO = this.partService.savePart(savePartDTO);

            response.setResult(partDTO);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
            httpStatus = HttpStatus.CREATED;
        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            response.setAppsErrorMessages(e.getAppsErrorMessages());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @PutMapping("/updatePart/{partID}")
    public ResponseEntity<ResponseDTO<PartDTO>> updatePart(@PathVariable Integer partID, @RequestBody PartDTO updatePartDTO) {
        ResponseDTO<PartDTO> response = new ResponseDTO<>();
        HttpStatus httpStatus;

        try {
            PartDTO partDTO = this.partService.updatePart(partID, updatePartDTO);

            response.setResult(partDTO);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
            httpStatus = HttpStatus.CREATED;

        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            response.setAppsErrorMessages(e.getAppsErrorMessages());
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @GetMapping(value = "/getPartByID/{partID}", headers = "Accept=application/json")
    public ResponseEntity<ResponseDTO<PartDTO>> getPartByID(@PathVariable Integer partID) {
        ResponseDTO<PartDTO> response = new ResponseDTO<>();
        HttpStatus httpStatus;

        try {
            PartDTO partDTO = this.partService.getPartByID(partID);

            response.setResult(partDTO);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
            httpStatus = HttpStatus.OK;
        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            response.setAppsErrorMessages(e.getAppsErrorMessages());
            httpStatus = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @GetMapping(value = "/getAllParts", headers = "Accept=application/json")
    public ResponseEntity<ResponseDTO<List<PartDTO>>> getAllCustomers() {
        ResponseDTO<List<PartDTO>> response = new ResponseDTO<>();
        HttpStatus httpStatus;

        try {
            List<PartDTO> partDTOList = this.partService.getAllParts();

            response.setResult(partDTOList);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
            httpStatus = HttpStatus.OK;
        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            response.setAppsErrorMessages(e.getAppsErrorMessages());
            httpStatus = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @DeleteMapping("/deletePart/{partID}")
    public ResponseEntity<ResponseDTO<Boolean>> deletePart(@PathVariable Integer partID) {
        ResponseDTO<Boolean> response = new ResponseDTO<>();
        HttpStatus httpStatus;

        try {
            this.partService.deletePart(partID);
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
