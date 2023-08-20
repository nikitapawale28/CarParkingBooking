package com.employee.Employee.Dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.Employee.Model.Credentials;



public interface EmployeeRepository extends JpaRepository<Credentials, String> {
	
	
}
