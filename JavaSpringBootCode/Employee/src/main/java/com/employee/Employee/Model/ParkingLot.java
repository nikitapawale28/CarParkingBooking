package com.employee.Employee.Model;


import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name ="parkinglot")
public class ParkingLot {
    @Id
    private int id;

    @Column(name = "booking_status")
    private Boolean booking_status;
    @Column(name = "booked_at")
    private LocalDateTime booked_at;
    private String booked_at_string;
    @Column(name = "available_at")
    private LocalDateTime available_at;
    private String available_at_string;
    
    
	public ParkingLot() {
		super();
		
	}
	


	public String getBooked_at_string() {
		return booked_at_string;
	}



	public void setBooked_at_string(String booked_at_string) {
		this.booked_at_string = booked_at_string;
	}



	public String getAvailable_at_string() {
		return available_at_string;
	}



	public void setAvailable_at_string(String available_at_string) {
		this.available_at_string = available_at_string;
	}



	


	public ParkingLot(int id, Boolean booking_status, LocalDateTime booked_at, String booked_at_string,
			LocalDateTime available_at, String available_at_string) {
		super();
		this.id = id;
		this.booking_status = booking_status;
		this.booked_at = booked_at;
		this.booked_at_string = booked_at_string;
		this.available_at = available_at;
		this.available_at_string = available_at_string;
	}



	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Boolean getBooking_status() {
		return booking_status;
	}


	public void setBooking_status(Boolean booking_status) {
		this.booking_status = booking_status;
	}


	public LocalDateTime getBooked_at() {
		return booked_at;
	}


	public void setBooked_at(LocalDateTime booked_at) {
		this.booked_at = booked_at;
	}


	public LocalDateTime getAvailable_at() {
		return available_at;
	}


	public void setAvailable_at(LocalDateTime available_at) {
		this.available_at = available_at;
	}



	@Override
	public String toString() {
		return "ParkingLot [id=" + id + ", booking_status=" + booking_status + ", booked_at=" + booked_at
				+ ", booked_at_string=" + booked_at_string + ", available_at=" + available_at + ", available_at_string="
				+ available_at_string + "]";
	}



	

	
	

    // Constructors, getters, setters
}