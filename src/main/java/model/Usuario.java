package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class Usuario {
	
	@Id	
	private String username;
	private String password;
	private String authority;
	
		
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
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	public boolean isActive(){
		return true;
	}
	
}
