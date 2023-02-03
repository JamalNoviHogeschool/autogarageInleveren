package com.autogarage.service.customer;

import com.autogarage.dao.car.CarDao;
import com.autogarage.dao.customer.CustomerDao;
import com.autogarage.dao.part.PartDao;
import com.autogarage.dao.repair.RepairDao;
import com.autogarage.exception.AppsException;
import com.autogarage.model.domain.car.Car;
import com.autogarage.model.domain.customer.Customer;
import com.autogarage.model.domain.part.Part;
import com.autogarage.model.domain.repair.Repair;
import com.autogarage.model.dto.customer.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private CarDao carDao;

    @Autowired
    private PartDao partDao;

    @Autowired
    private RepairDao repairDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public CustomerDTO saveCustomer(CustomerDTO saveCustomerDTO) throws AppsException {
        Customer customer = new Customer();

        this.getCustomerByCustomerDTO(customer, saveCustomerDTO);

        customer = this.customerDao.saveAndFlush(customer);

        return new CustomerDTO(customer);
    }

    private void getCustomerByCustomerDTO(Customer customer, CustomerDTO customerDTO) {
        customer.setAddress(customerDTO.getAddress());
        customer.setCity(customerDTO.getCity());
        customer.setEmail(customerDTO.getEmail());
        customer.setName(customerDTO.getName());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customer.setSurname(customerDTO.getSurname());
        customer.setZipCode(customerDTO.getZipCode());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public CustomerDTO updateCustomer(Integer customerID, CustomerDTO updateCustomerDTO) throws AppsException {
        if (this.customerDao.existsById(customerID)) {
            Customer customer = this.customerDao.getReferenceById(customerID);

            this.getCustomerByCustomerDTO(customer, updateCustomerDTO);

            customer = this.customerDao.saveAndFlush(customer);

            return new CustomerDTO(customer);
        } else {
            throw new AppsException("Can't find customer with given ID");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public CustomerDTO getCustomerByID(Integer customerID) throws AppsException {
        if (this.customerDao.existsById(customerID)) {
            Customer customer = this.customerDao.getReferenceById(customerID);
            return new CustomerDTO(customer);
        } else {
            throw new AppsException("Can't find customer with given ID");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<CustomerDTO> getAllCustomers() throws AppsException {
        List<Customer> customerList = this.customerDao.findAll();
        List<CustomerDTO> customerDTOList = new ArrayList<>();

        for (Customer customer : customerList) {
            customerDTOList.add(new CustomerDTO(customer));
        }

        return customerDTOList;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Customer getCustomerEntityByID(Integer customerID) throws AppsException {
        if (customerID == null) {
            throw new AppsException("Invalid Customer ID");
        } else {
            if (this.customerDao.existsById(customerID)) {
                Customer customer = this.customerDao.getReferenceById(customerID);
                return customer;
            } else {
                throw new AppsException("Can't find customer with given ID");
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteCustomer(Integer customerID) throws AppsException {
        if (this.customerDao.existsById(customerID)) {
            Customer customer = this.customerDao.getReferenceById(customerID);

            for (Car car : customer.getCars()) {
                for (Part part : car.getParts()) {
                    this.repairDao.deleteAll(part.getRepairs());
                }
                this.partDao.deleteAll(car.getParts());
            }

            this.carDao.deleteAll(customer.getCars());
            this.customerDao.deleteById(customerID);
        } else {
            throw new AppsException("Can't find customer with given ID");
        }
    }
}
