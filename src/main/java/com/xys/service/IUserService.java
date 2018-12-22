package com.xys.service;

import com.xys.dto.hibernate.UserDto;
import com.xys.entity.hibernate.User;
import com.xys.util.Pagination;

public interface IUserService {

	Pagination findUsers(Pagination pager);
	
	void insert(UserDto userDto);
	
	void deletes(Long[] ids);
	
	void update(UserDto userDto);
	
	User fetch(Long id);
	
	UserDto fetchById(Long id);
}
