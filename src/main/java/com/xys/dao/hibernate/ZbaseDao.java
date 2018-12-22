package com.xys.dao.hibernate;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public abstract class ZbaseDao {

	@Resource
	private SessionFactory sessionFactory;

	public ZbaseDao() {
		super();
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
}
