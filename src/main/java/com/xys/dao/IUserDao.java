package com.xys.dao;

import com.xys.dto.hibernate.UserDto;
import com.xys.entity.hibernate.User;
import com.xys.util.Pagination;

public interface IUserDao {

	Pagination findUsers(Pagination pager);
	
	void insert(User user);
	
	void deletes(Long[] ids);
	
	void update(User user);
	
	User fetch(Long id);
	
	UserDto fetchById(Long id);
}
