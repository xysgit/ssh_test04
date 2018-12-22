package com.xys.action.struts2;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.xys.action.ZbaseAction;
import com.xys.dto.hibernate.UserDto;
import com.xys.service.IUserService;

public class UserAction extends ZbaseAction {

	@Resource
	private IUserService userService;
	private UserDto userDto;
	
	public String findUsers() {
		LinkedHashMap<String, String> conditions = new LinkedHashMap<String, String>();
		if(id != null && id > 0) {
			conditions.put("eq_id", id.toString());
		}
		pager.setPage(page);
		pager.setRows(rows);
		pager.setConditions(conditions);
		pager = userService.findUsers(pager);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", pager.getElements());
		map.put("total", pager.getTotalCount());
		
		
		String text = JSON.toJSONString(map, SerializerFeature.UseSingleQuotes);
//		String str = JSON.toJSONStringWithDateFormat(map, "yyyy-MM-dd  HH:mm:ss");
		root = JSONObject.parseObject(text, Feature.OrderedField);
		return "find-users-json";
	}
	
	public String edit() {
		setMessage("操作失败");
		if(userDto == null) {
			setMessage("参数错误");
			return "edit-json";
		}
		if(userDto.getId() != null) {
			userService.update(userDto);
			setMessage("编辑成功");
		}else {
			userService.insert(userDto);
			setMessage("新增成功");
		}
		setSuccess(true);
		return "edit-json";
	}
	
	public String deletes() {
		setMessage("操作失败");
		if(ids == null || ids.length < 1) {
			setMessage("参数错误");
			return "deletes-json";
		}
		userService.deletes(ids);
		setMessage("删除成功");
		setSuccess(true);
		return "deletes-json";
	}
	
	public String fetchById() {
		setMessage("操作失败");
		if(id == null || id < 1) {
			setMessage("参数错误");
			return "fetch-by-id-json";
		}
		userDto = userService.fetchById(id);
		setMessage("加载成功");
		setSuccess(true);
		return "fetch-by-id-json";
	}
	
	public UserDto getUserDto() {
		return userDto;
	}
	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
}
