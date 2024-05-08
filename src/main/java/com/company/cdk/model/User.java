package com.company.cdk.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_details")
public class User{              //implements UserDetails

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID")
    private int id;

    @Column(name = "Fullname")
    private String fullname;

    @Column(name = "Address")
    private String address;

    @Column(name = "Email")
    private String email;

    @Column(name = "Password")
    private String password;
    
    @Column(name = "usertype")
    private String userType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public User() {
		super();
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", fullname=" + fullname + ", address=" + address + ", email=" + email + ", password="
				+ password + ", userType=" + userType + "]";
	}
	
	public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        // Add authorities based on the user's role or userType
        authorities.add(new SimpleGrantedAuthority("ROLE_" + userType.toUpperCase()));
        // Add additional authorities as needed
        return authorities;
    }
    
    
}
