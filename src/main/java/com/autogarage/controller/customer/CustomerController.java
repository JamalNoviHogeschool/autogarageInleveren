package com.autogarage.controller.customer;

import com.autogarage.constants.AppsConstants;
import com.autogarage.exception.AppsException;
import com.autogarage.model.common.ResponseDTO;
import com.autogarage.model.dto.customer.CustomerDTO;
import com.autogarage.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping(value = "/saveCustomer", headers = "Accept=application/json")
    public ResponseEntity<ResponseDTO<CustomerDTO>> saveCustomer(@RequestBody CustomerDTO saveCustomerDTO) {
        ResponseDTO<CustomerDTO> response = new ResponseDTO<>();
        HttpStatus httpStatus;

        CustomerDTO customerDTO;
        try {
            customerDTO = this.customerService.saveCustomer(saveCustomerDTO);

            response.setResult(customerDTO);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
            httpStatus = HttpStatus.CREATED;
        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            response.setAppsErrorMessages(e.getAppsErrorMessages());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @PutMapping("/updateCustomer/{customerID}")
    public ResponseEntity<ResponseDTO<CustomerDTO>> updateCustomer(@PathVariable Integer customerID, @RequestBody CustomerDTO updateCustomerDTO) {
        ResponseDTO<CustomerDTO> response = new ResponseDTO<>();
        HttpStatus httpStatus;

        try {
            CustomerDTO personDTO = this.customerService.updateCustomer(customerID, updateCustomerDTO);

            response.setResult(personDTO);
            response.setResult(personDTO);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
            httpStatus = HttpStatus.CREATED;

        } catch (Exception e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @GetMapping(value = "/getCustomerByID/{customerID}", headers = "Accept=application/json")
    public ResponseEntity<ResponseDTO<CustomerDTO>> getCustomerByID(@PathVariable Integer customerID) {
        ResponseDTO<CustomerDTO> response = new ResponseDTO<>();
        HttpStatus httpStatus;

        try {
            CustomerDTO customerDTO = this.customerService.getCustomerByID(customerID);

            response.setResult(customerDTO);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
            httpStatus = HttpStatus.OK;
        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            response.setAppsErrorMessages(e.getAppsErrorMessages());
            httpStatus = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @GetMapping(value = "/getAllCustomers", headers = "Accept=application/json")
    public ResponseEntity<ResponseDTO<List<CustomerDTO>>> getAllCustomers() {
        ResponseDTO<List<CustomerDTO>> response = new ResponseDTO<>();
        HttpStatus httpStatus;

        try {
            List<CustomerDTO> customerList = this.customerService.getAllCustomers();

            response.setResult(customerList);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
            httpStatus = HttpStatus.OK;
        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            response.setAppsErrorMessages(e.getAppsErrorMessages());
            httpStatus = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @DeleteMapping("/deleteCustomer/{customerID}")
    public ResponseEntity<ResponseDTO<Boolean>> deleteCustomer(@PathVariable Integer customerID) {
        ResponseDTO<Boolean> response = new ResponseDTO<>();
        HttpStatus httpStatus;

        try {
            this.customerService.deleteCustomer(customerID);
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
