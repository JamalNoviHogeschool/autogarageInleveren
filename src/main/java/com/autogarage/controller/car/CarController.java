package com.autogarage.controller.car;

import com.autogarage.constants.AppsConstants;
import com.autogarage.exception.AppsException;
import com.autogarage.model.common.ResponseDTO;
import com.autogarage.model.dto.car.CarDTO;
import com.autogarage.service.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/car")
public class CarController {

    @Autowired
    private CarService carService;

    @PostMapping(value = "/saveCar", headers = "Accept=application/json")
    public ResponseEntity<ResponseDTO<CarDTO>> saveCar(@RequestBody CarDTO saveCarDTO) {
        ResponseDTO<CarDTO> response = new ResponseDTO<>();
        HttpStatus httpStatus;

        CarDTO CarDTO;
        try {
            CarDTO = this.carService.saveCar(saveCarDTO);

            response.setResult(CarDTO);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
            httpStatus = HttpStatus.CREATED;
        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            response.setAppsErrorMessages(e.getAppsErrorMessages());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @PutMapping("/updateCar/{carID}")
    public ResponseEntity<ResponseDTO<CarDTO>> updateCar(@PathVariable Integer carID, @RequestBody CarDTO updateCarDTO) {
        ResponseDTO<CarDTO> response = new ResponseDTO<>();
        HttpStatus httpStatus;

        try {
            CarDTO CarDTO = this.carService.updateCar(carID, updateCarDTO);

            response.setResult(CarDTO);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
            httpStatus = HttpStatus.CREATED;

        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            response.setAppsErrorMessages(e.getAppsErrorMessages());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @GetMapping(value = "/getCarByID/{carID}", headers = "Accept=application/json")
    public ResponseEntity<ResponseDTO<CarDTO>> getCarByID(@PathVariable Integer carID) {
        ResponseDTO<CarDTO> response = new ResponseDTO<>();
        HttpStatus httpStatus;

        try {
            CarDTO CarDTO = this.carService.getCarByID(carID);

            response.setResult(CarDTO);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
            httpStatus = HttpStatus.OK;
        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            response.setAppsErrorMessages(e.getAppsErrorMessages());
            httpStatus = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @GetMapping(value = "/getAllCars", headers = "Accept=application/json")
    public ResponseEntity<ResponseDTO<List<CarDTO>>> getAllCustomers() {
        ResponseDTO<List<CarDTO>> response = new ResponseDTO<>();
        HttpStatus httpStatus;

        try {
            List<CarDTO> CarDTOList = this.carService.getAllCars();

            response.setResult(CarDTOList);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
            httpStatus = HttpStatus.OK;
        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            response.setAppsErrorMessages(e.getAppsErrorMessages());
            httpStatus = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @DeleteMapping("/deleteCar/{carID}")
    public ResponseEntity<ResponseDTO<Boolean>> deleteCar(@PathVariable Integer carID) {
        ResponseDTO<Boolean> response = new ResponseDTO<>();
        HttpStatus httpStatus;

        try {
            this.carService.deleteCar(carID);
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
