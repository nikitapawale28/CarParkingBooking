package com.employee.Employee.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name ="booking_details")
public class BookingDetails {
	 @Id
	 int booking_id;
	 @Column(name = "car_model")
	 String car_model;
	 @Column(name = "plate_number")
	 String plate_number;
	 @Column(name = "booking_duration")
     int booking_duration;
	 @Column(name = "username")
     String username;
	 @Column(name = "parking_id")
	 int parking_id;
     
	public BookingDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public BookingDetails(int booking_id, String car_model, String plate_number, int booking_duration, String username,
			int parking_id) {
		super();
		this.booking_id = booking_id;
		this.car_model = car_model;
		this.plate_number = plate_number;
		this.booking_duration = booking_duration;
		this.username = username;
		this.parking_id = parking_id;
	}

	@Override
	public String toString() {
		return "BookingDetails [booking_id=" + booking_id + ", car_model=" + car_model + ", plate_number="
				+ plate_number + ", booking_duration=" + booking_duration + ", username=" + username + ", parking_id="
				+ parking_id + "]";
	}
	public int getParking_id() {
		return parking_id;
	}
	public void setParking_id(int parking_id) {
		this.parking_id = parking_id;
	}
	public int getBooking_id() {
		return booking_id;
	}
	public void setBooking_id(int booking_id) {
		this.booking_id = booking_id;
	}
	public String getCar_model() {
		return car_model;
	}
	public void setCar_model(String car_model) {
		this.car_model = car_model;
	}
	public String getPlate_number() {
		return plate_number;
	}
	public void setPlate_number(String plate_number) {
		this.plate_number = plate_number;
	}
	public int getBooking_duration() {
		return booking_duration;
	}
	public void setBooking_duration(int booking_duration) {
		this.booking_duration = booking_duration;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
     
}
