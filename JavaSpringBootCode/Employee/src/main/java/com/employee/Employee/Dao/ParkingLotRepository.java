package com.employee.Employee.Dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.Employee.Model.Credentials;
import com.employee.Employee.Model.ParkingLot;


public interface ParkingLotRepository extends JpaRepository<ParkingLot, Long> {
    // Custom queries if needed
}