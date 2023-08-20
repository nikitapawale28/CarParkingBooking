
package com.employee.Employee.Model;

import jakarta.persistence.*;
/*import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
*/


@Entity
@Table(name ="credentials")
public class Credentials {
	@Id
    private String username;
	@Column(name = "password")
    private String password;
	@Column(name = "confirm_password")
    private String confirmPassword;
	public Credentials() {
		super();
		
	}
	public Credentials(String username, String password, String confirmPassword) {
		this.username = username;
		this.password = password;
		this.confirmPassword = confirmPassword;
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
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	@Override
	public String toString() {
		return "Credentials [username=" + username + ", password=" + password + ", confirmPassword=" + confirmPassword
				+ "]";
	}
	

    // Getter and setter methods for fields
}
