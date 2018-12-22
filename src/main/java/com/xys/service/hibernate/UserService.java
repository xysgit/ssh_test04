package com.xys.service.hibernate;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.xys.dao.IUserDao;
import com.xys.dto.hibernate.UserDto;
import com.xys.entity.hibernate.User;
import com.xys.service.IUserService;
import com.xys.util.Pagination;

@Service("userService")
public class UserService implements IUserService {

	@Resource
	private IUserDao userDao;

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public Pagination findUsers(Pagination pager) {
		return userDao.findUsers(pager);
	}

	public void insert(UserDto userDto) {
		User user = new User();
		BeanUtils.copyProperties(userDto, user);
		user.setCreateTime(new Date());
		user.setEditTime(new Date());
		userDao.insert(user);
	}

	public void deletes(Long[] ids) {
		userDao.deletes(ids);
	}

	public void update(UserDto userDto) {
		User user = fetch(userDto.getId());
		if(user != null) {
			user.setEditTime(new Date());
			user.setAge(userDto.getAge());
			user.setBirthday(userDto.getBirthday());
			user.setUsername(userDto.getUsername());
			userDao.update(user);
		}
	}

	public User fetch(Long id) {
		return userDao.fetch(id);
	}

	public UserDto fetchById(Long id) {
		return userDao.fetchById(id);
	}
	
	
}
