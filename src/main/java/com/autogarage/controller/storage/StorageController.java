package com.autogarage.controller.storage;

import com.autogarage.constants.AppsConstants;
import com.autogarage.exception.AppsException;
import com.autogarage.model.common.ResponseDTO;
import com.autogarage.service.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(value = "api/storage")
public class StorageController {

    @Autowired
    private StorageService storageService;

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public ResponseEntity<ResponseDTO<String>> uploadFile(@RequestParam("file") MultipartFile uploadFile) {
        ResponseDTO<String> response = new ResponseDTO<>();
        HttpStatus httpStatus;

        try {
            String fileName = this.storageService.uploadFile(uploadFile);

            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
            response.setResult(fileName);
            httpStatus = HttpStatus.CREATED;
        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            response.setAppsErrorMessages(e.getAppsErrorMessages());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @RequestMapping(value = "/loadFile/{fileName}", headers = "Accept=application/json", method = RequestMethod.GET)
    public ResponseEntity<byte[]> loadFile(@PathVariable String fileName) throws Exception {

        ResponseDTO<byte[]> response = new ResponseDTO<>();
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpStatus httpStatus;
        httpHeaders.setContentType(MediaType.TEXT_PLAIN);
        byte[] bytes = null;
        try {
            bytes = this.storageService.loadFile(fileName);

            httpStatus = HttpStatus.OK;
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
            response.setResult(bytes);

            httpHeaders.setContentLength(bytes.length);
            httpHeaders.setContentDispositionFormData("attachment", fileName);
            httpHeaders.add("fileName", fileName);

        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            response.setAppsErrorMessages(e.getAppsErrorMessages());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(bytes, httpHeaders, httpStatus);
    }
}
