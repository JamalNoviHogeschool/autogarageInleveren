package com.autogarage.service.car;

import com.autogarage.dao.car.CarDao;
import com.autogarage.exception.AppsException;
import com.autogarage.model.domain.car.Car;
import com.autogarage.model.domain.customer.Customer;
import com.autogarage.model.domain.part.Part;
import com.autogarage.model.dto.car.CarDTO;
import com.autogarage.service.customer.CustomerService;
import com.autogarage.service.part.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarDao carDao;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PartService partService;

    @Transactional(propagation = Propagation.REQUIRED)
    public CarDTO saveCar(CarDTO saveCarDTO) throws AppsException {
        Car car = new Car();

        this.getCarByCarDTO(car, saveCarDTO);

        car = this.carDao.saveAndFlush(car);

        return new CarDTO(car);
    }

    private Car getCarByCarDTO(Car car, CarDTO carDTO) throws AppsException {
        car.setLicensePlate(carDTO.getLicensePlate());
        car.setBrand(carDTO.getBrand());
        car.setModel(carDTO.getModel());
        car.setVersionYear(carDTO.getVersionYear());

        Customer customer = this.customerService.getCustomerEntityByID(carDTO.getCustomerID());
        car.setCustomer(customer);

        return car;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public CarDTO updateCar(Integer carID, CarDTO updateCarDTO) throws AppsException {
        if (this.carDao.existsById(carID)) {
            Car car = this.carDao.getReferenceById(carID);

            this.getCarByCarDTO(car, updateCarDTO);

            car = this.carDao.saveAndFlush(car);

            return new CarDTO(car);
        } else {
            throw new AppsException("Can't find car with given ID");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public CarDTO getCarByID(Integer carID) throws AppsException {
        if (carID == null) {
            throw new AppsException("Invalid Car ID");
        } else {
            if (this.carDao.existsById(carID)) {
                Car car = this.carDao.getReferenceById(carID);
                return new CarDTO(car);
            } else {
                throw new AppsException("Can't find car with given ID");
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<CarDTO> getAllCars() throws AppsException {
        List<Car> carList = this.carDao.findAll();
        List<CarDTO> carDTOList = new ArrayList<>();

        for (Car car : carList) {
            carDTOList.add(new CarDTO(car));
        }

        return carDTOList;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Car getCarEntityByID(Integer carID) throws AppsException {
        if (carID == null) {
            throw new AppsException("Invalid Car ID");
        } else {
            if (this.carDao.existsById(carID)) {
                Car car = this.carDao.getReferenceById(carID);
                return car;
            } else {
                throw new AppsException("Can't find car with given ID");
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteCar(Integer carID) throws AppsException {
        if (this.carDao.existsById(carID)) {
            Car car = this.carDao.getReferenceById(carID);

            for (Part part : car.getParts()) {
                this.partService.deletePart(part.getPartID());
            }

            this.carDao.deleteById(carID);
        } else {
            throw new AppsException("Can't find car with given ID");
        }
    }
}
