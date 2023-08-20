package com.employee.Employee.Model;

import java.util.List;

public class AccountInfoDTO {
	private String username;
    private String password;
    private List<BookingInfoDTO> activeBookings;
    private List<BookingInfoDTO> pastBookings;
    
    
	public AccountInfoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AccountInfoDTO(String username, String password, List<BookingInfoDTO> activeBookings,
			List<BookingInfoDTO> pastBookings) {
		super();
		this.username = username;
		this.password = password;
		this.activeBookings = activeBookings;
		this.pastBookings = pastBookings;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<BookingInfoDTO> getActiveBookings() {
		return activeBookings;
	}
	public void setActiveBookings(List<BookingInfoDTO> activeBookings) {
		this.activeBookings = activeBookings;
	}
	public List<BookingInfoDTO> getPastBookings() {
		return pastBookings;
	}
	public void setPastBookings(List<BookingInfoDTO> pastBookings) {
		this.pastBookings = pastBookings;
	}
	@Override
	public String toString() {
		return "AccountInfoDTO [username=" + username + ", password=" + password + ", activeBookings=" + activeBookings
				+ ", pastBookings=" + pastBookings + "]";
	}

    // Constructors, getters, setters
	
    
}
