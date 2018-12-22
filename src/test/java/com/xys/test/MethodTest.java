package com.xys.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class MethodTest {

	private static ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring/spring.xml");
	
	public static void main(String[] args) {
		
		ComboPooledDataSource c = ac.getBean(ComboPooledDataSource.class);
		System.out.println(c);
		
		LocalSessionFactoryBean l =ac.getBean(LocalSessionFactoryBean.class
				);
		System.out.println(l);
		
		HibernateTransactionManager h = ac.getBean(HibernateTransactionManager.class);
		System.out.println(h);
		
		
		
		
		
		
		System.out.println(123);
	}
}
