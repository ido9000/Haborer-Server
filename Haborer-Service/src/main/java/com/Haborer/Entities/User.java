package com.Haborer.Entities;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonTypeInfo(use=Id.CLASS,include=JsonTypeInfo.As.PROPERTY,property="type")
public class User {
	   private static final long serialVersionUID = 1L;
	   private String _id=new ObjectId().toString();
	   
	   private String firstName;
	   private String lastName;
	   private String squadron;
	   private String userName;
	   private String password;
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getSquadron() {
		return squadron;
	}
	public void setSquadron(String squadron) {
		this.squadron = squadron;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_id == null) ? 0 : _id.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((squadron == null) ? 0 : squadron.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (_id == null) {
			if (other._id != null)
				return false;
		} else if (!_id.equals(other._id))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (squadron == null) {
			if (other.squadron != null)
				return false;
		} else if (!squadron.equals(other.squadron))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "User [_id=" + _id + ", firstName=" + firstName + ", lastName=" + lastName + ", squadron=" + squadron
				+ ", userName=" + userName + ", password=" + password + "]";
	}
	public User(String firstName, String lastName, String squadron, String userName, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.squadron = squadron;
		this.userName = userName;
		this.password = password;
	}
	   
	public User() {
		
	}
}
