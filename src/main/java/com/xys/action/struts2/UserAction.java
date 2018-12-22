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
		setMessage("����ʧ��");
		if(userDto == null) {
			setMessage("��������");
			return "edit-json";
		}
		if(userDto.getId() != null) {
			userService.update(userDto);
			setMessage("�༭�ɹ�");
		}else {
			userService.insert(userDto);
			setMessage("�����ɹ�");
		}
		setSuccess(true);
		return "edit-json";
	}
	
	public String deletes() {
		setMessage("����ʧ��");
		if(ids == null || ids.length < 1) {
			setMessage("��������");
			return "deletes-json";
		}
		userService.deletes(ids);
		setMessage("ɾ���ɹ�");
		setSuccess(true);
		return "deletes-json";
	}
	
	public String fetchById() {
		setMessage("����ʧ��");
		if(id == null || id < 1) {
			setMessage("��������");
			return "fetch-by-id-json";
		}
		userDto = userService.fetchById(id);
		setMessage("���سɹ�");
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
