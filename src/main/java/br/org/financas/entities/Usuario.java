package br.org.financas.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name="users")
public class Usuario {
	
	@Id
	@TableGenerator(name = "TABLE_GENERATOR", table = "SEQUENCE_TABLE", pkColumnName = "TABLE_NAME", pkColumnValue = "USUARIO_ID", valueColumnName = "TABLE_VALUE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GENERATOR")
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
