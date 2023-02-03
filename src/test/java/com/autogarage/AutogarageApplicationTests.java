package com.autogarage;

import com.autogarage.constants.AppsConstants;
import com.autogarage.model.dto.car.CarDTO;
import com.autogarage.model.dto.user.UserDTO;
import com.autogarage.service.car.CarService;
import com.autogarage.service.user.UserService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AutogarageApplicationTests {

    @Autowired
    private CarService carService;

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
    }

    @Test
    @Order(1)
    public void registerUser() throws Exception {

        UserDTO userDTO = new UserDTO();

        userDTO.setUserName("Jamal");
        userDTO.setFirstName("Jamal");
        userDTO.setLastName("Khalilola");
        userDTO.setMobile("0683418030");
        userDTO.setEmail("jamal@gmail.com");
        userDTO.setPassword("1234");
        userDTO.setUserRole(AppsConstants.UserRole.ADMIN);

        UserDTO result = this.userService.registerUser(userDTO);
        assertNotNull(result.getUserID());
    }

    @Test
    @Order(2)
    public void getUserByID() throws Exception {
        UserDTO result = this.userService.getUserByID(2);
        assertNotNull(result);
    }

    @Test
    @Order(3)
    public void getAllUsers() throws Exception {
        List<UserDTO> results = this.userService.getAllUsers();
        assertNotNull(results);
    }

    @Test
    @Order(4)
    public void saveCar() throws Exception {

        CarDTO carDTO = new CarDTO();

        //This should be always unique
        carDTO.setLicensePlate("TS-460-G");
        carDTO.setBrand("BMW");
        carDTO.setModel("RS_55");
        carDTO.setVersionYear(2018);
        carDTO.setCustomerID(1);

        CarDTO result = this.carService.saveCar(carDTO);
        assertNotNull(result.getCarID());
    }

    @Test
    @Order(5)
    public void updateCar() throws Exception {

        CarDTO carDTO = new CarDTO();
        //This should be always unique
        carDTO.setLicensePlate("TS-460-G");
        carDTO.setBrand("BMW");
        carDTO.setModel("RS_55");
        carDTO.setVersionYear(2018);
        carDTO.setCustomerID(1);

        CarDTO result = this.carService.updateCar(2, carDTO);
        assertNotNull(result.getCarID());
    }

    @Test
    @Order(6)
    public void getCarByID() throws Exception {
        CarDTO result = this.carService.getCarByID(4);
        assertNotNull(result);
    }

    @Test
    @Order(7)
    public void getAllCars() throws Exception {
        List<CarDTO> results = this.carService.getAllCars();
        assertNotNull(results);
    }
}
