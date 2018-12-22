package com.xys.dto;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.BeanUtils;

public class Dto<E> implements Serializable {

	private Long id;
	private Date createTime;
	private Date editTime;
	public Dto() {
		super();
	}
	public Dto(E e) {
		super();
		BeanUtils.copyProperties(e, this);
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}
	public Long getId() {
		return id;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public Date getEditTime() {
		return editTime;
	}
	
}
