package com.xys.action;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import com.xys.util.Pagination;

public class ZbaseAction extends ActionSupport {

	
	protected int page = 1;
	protected int rows = 10;
	protected Long id;
	protected Long[] ids;
	protected String message;
	protected boolean success;
	protected JSONObject root;
	protected Pagination pager = new Pagination();
	public ZbaseAction() {
		super();
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long[] getIds() {
		return ids;
	}
	public void setIds(Long[] ids) {
		this.ids = ids;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public JSONObject getRoot() {
		return root;
	}
	public void setRoot(JSONObject root) {
		this.root = root;
	}
	public Pagination getPager() {
		return pager;
	}
	public void setPager(Pagination pager) {
		this.pager = pager;
	}
	
}
