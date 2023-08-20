package com.employee.Employee.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.employee.Employee.Model.BookingDetails;
import com.employee.Employee.Model.ParkingLot;


public interface BookingRepository extends JpaRepository<BookingDetails, Integer> {

	@Query("SELECT bd.car_model, bd.plate_number, bd.booking_duration, p.booked_at, p.available_at " +
			"FROM BookingDetails bd " +
			"INNER JOIN ParkingLot p ON bd.parking_id = p.id "+
			"WHERE bd.username = :username " +
			"AND p.booked_at <= NOW() " +
			"AND p.available_at >= NOW() ")
	public List<Object[]> findActiveBookingsByUsername(String username);

	@Query("SELECT bd.car_model, bd.plate_number, bd.booking_duration, p.booked_at, p.available_at "
			+ "FROM BookingDetails bd "
			+ "INNER JOIN ParkingLot p ON bd.parking_id = p.id "
			+ "WHERE bd.username = :username "
			+ "AND p.available_at < NOW() ")
	List<Object[]> findPastBookingsByUsername(String username);



}
