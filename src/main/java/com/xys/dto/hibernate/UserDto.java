package com.xys.dto.hibernate;

import java.util.Date;

import com.xys.dto.Dto;
import com.xys.entity.hibernate.User;

public class UserDto extends Dto<User> {

	private String username;
	private Integer age;
	private Date birthday;
	public UserDto() {
		super();
	}
	public UserDto(User e) {
		super(e);
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
}
