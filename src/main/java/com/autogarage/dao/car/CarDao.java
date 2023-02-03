package com.autogarage.dao.car;

import com.autogarage.model.domain.car.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarDao extends JpaRepository<Car, Integer> {
}
