package com.employee.Employee.Model;

import java.time.LocalDateTime;

public class BookingInfoDTO {
    private String carModel;
    private String plateNumber;
    private Integer bookingDuration;
    private LocalDateTime bookedAt;
    private LocalDateTime availableAt;
    
    
	public BookingInfoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BookingInfoDTO(String carModel, String plateNumber, Integer bookingDuration, LocalDateTime bookedAt,
			LocalDateTime availableAt) {
		super();
		this.carModel = carModel;
		this.plateNumber = plateNumber;
		this.bookingDuration = bookingDuration;
		this.bookedAt = bookedAt;
		this.availableAt = availableAt;
	}
	public String getCarModel() {
		return carModel;
	}
	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}
	public String getPlateNumber() {
		return plateNumber;
	}
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	public Integer getBookingDuration() {
		return bookingDuration;
	}
	public void setBookingDuration(Integer bookingDuration) {
		this.bookingDuration = bookingDuration;
	}
	public LocalDateTime getBookedAt() {
		return bookedAt;
	}
	public void setBookedAt(LocalDateTime bookedAt) {
		this.bookedAt = bookedAt;
	}
	public LocalDateTime getAvailableAt() {
		return availableAt;
	}
	public void setAvailableAt(LocalDateTime availableAt) {
		this.availableAt = availableAt;
	}
    
    
    
}
