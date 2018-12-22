package com.xys.util;

import java.util.List;
import java.util.Map;

public class Pagination {

	
	private int page = 1;
	private int rows = 10;
	private Long totalCount;
	private Map<String, String> conditions;
	private List elements;
	public Pagination() {
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
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Map<String, String> getConditions() {
		return conditions;
	}
	public void setConditions(Map<String, String> conditions) {
		this.conditions = conditions;
	}
	public List getElements() {
		return elements;
	}
	public void setElements(List elements) {
		this.elements = elements;
	}
	
}
