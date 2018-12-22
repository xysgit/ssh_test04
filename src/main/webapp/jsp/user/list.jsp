<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/easyui/themes/icon.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/easyui/themes/default/easyui.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/easyui/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/easyui/easyui-lang-zh_CN.js"></script>
</head>
<body>
	<table id="dg" title="My Users" class="easyui-datagrid"
		url="${pageContext.request.contextPath}/user!findUsers.action"
		toolbar="#toolbar" rownumbers="true" fitColumns="true"
		singleSelect="false" pagination="true" loadMsg="加载中...">
		<thead>
			<tr>
				<th field="id" width="50">id</th>
				<th field="createTime" width="150">createTime</th>
				<th field="editTime" width="150">editTime</th>
				<th field="username" width="50">username</th>
				<th field="age" width="50">age</th>
				<th field="birthday" width="150">birthday</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"
			onclick="newUser()">New User</a> <a href="#"
			class="easyui-linkbutton" iconCls="icon-edit" plain="true"
			onclick="editUser()">Edit User</a> <a href="#"
			class="easyui-linkbutton" iconCls="icon-remove" plain="true"
			onclick="destroyUser()">Remove User</a>
	</div>


	<div id="dlg" class="easyui-dialog"
		style="width: 400px; height: 280px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">
		<div class="ftitle">User Information</div>
		<form id="fm" method="post">
			<!-- <div class="fitem">
				<label>First Name:</label> <input name="firstname"
					class="easyui-textbox" required="true">
			</div> -->
			<div class="fitem">
				<label>id:</label> <input id="idtxt" name="userDto.id"
					class="easyui-textbox">
			</div>
			<div class="fitem">
				<label>createTime:</label> <input id="createTimetxt" name="userDto.createTime"
					class="easyui-datetimebox">
			</div>
			<div class="fitem">
				<label>editTime:</label> <input id="editTimetxt" name="userDto.editTime"
					class="easyui-datetimebox">
			</div>
			<div class="fitem">
				<label>age:</label> <input id="agetxt" name="userDto.age"
					class="easyui-textbox" required="true">
			</div>
			<div class="fitem">
				<label>username:</label> <input id="usernametxt" name="userDto.username"
					class="easyui-textbox" required="true">
			</div>
			<div class="fitem">
				<label>birthday:</label> <input id="birthdaytxt" name="userDto.birthday"
					class="easyui-datebox" required="true">
			</div>
			</div>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			onclick="saveUser()">Save</a> <a href="#" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">Cancel</a>
	</div>
	<script type="text/javascript">
	
	function newUser(){
		$('#dlg').dialog('open').dialog('setTitle','New User');
		$('#fm').form('clear');
	}
	
	function parsedate(value){
	    	var date = new Date(value);
		var year = date.getFullYear();
		var month = date.getMonth()+1; //月份+1   
		var day = date.getDate(); 
		var hour = date.getHours(); 
		var minutes = date.getMinutes(); 
		var second = date.getSeconds();
		return  day+"/"+month+"/"+year+" "+hour+":"+minutes +":"+second;
	}
	
	function editUser(){
		var rows = $('#dg').datagrid('getSelections');
		if(rows != null && rows.length == 1){
			$.ajax({
			    url:'${pageContext.request.contextPath}/user!fetchById.action',
			    type:'POST', //GET
			    async:true,    //或false,是否异步
			    data:{
			        id:rows[0].id
			    },
			    timeout:5000,    //超时时间
			    traditional:true,
			    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
			    beforeSend:function(xhr){
			        console.log(xhr)
			        console.log('发送前')
			    },
			    success:function(data,textStatus,jqXHR){
					$('#fm').form('clear');
					
					console.log(data.userDto);
					
					$('#idtxt').textbox('setValue', data.userDto.id);
					$('#agetxt').textbox('setValue', data.userDto.age);
					$('#usernametxt').textbox('setValue', data.userDto.username);
					$('#createTimetxt').datetimebox('setValue', parsedate(data.userDto.createTime));
					$('#editTimetxt').datetimebox('setValue', parsedate(data.userDto.editTime));
					$('#birthdaytxt').datebox('setValue', data.userDto.birthday);
					
					$('#dlg').dialog('open').dialog('setTitle','Edit User');
			    },
			    error:function(xhr,textStatus){
			        console.log('错误')
			        console.log(xhr)
			        console.log(textStatus)
			    },
			    complete:function(){
			        console.log('结束')
			    }
			})
		}else{
			$.messager.alert('提示',"请选中一条记录！");
		}
	}
	
	function saveUser(){
		var result = $('#fm').serialize();
		$.post('${pageContext.request.contextPath}/user!edit.action', result,function(result){
			$('#dlg').dialog('close');
			$('#dg').datagrid('reload');	// reload the user data
			$.messager.alert('提示',result.message);
		},'json');
	}
	
	
		var p = $('#dg').datagrid('getPager');
		$(p).pagination({
			beforePageText : '第',//页数文本框前显示的汉字 
			afterPageText : '页    共 {pages} 页',
			displayMsg : '第{from}到{to}条，共{total}条',
		});
		
		function deletes(ids){
			$.ajax({
			    url:'${pageContext.request.contextPath}/user!deletes.action',
			    type:'POST', //GET
			    async:true,    //或false,是否异步
			    data:{
			        ids:ids
			    },
			    timeout:5000,    //超时时间
			    traditional:true,
			    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
			    beforeSend:function(xhr){
			        console.log(xhr)
			        console.log('发送前')
			    },
			    success:function(data,textStatus,jqXHR){
			    	$('#dg').datagrid('reload');
			    	$.messager.alert('提示',data.message);
			    },
			    error:function(xhr,textStatus){
			        console.log('错误')
			        console.log(xhr)
			        console.log(textStatus)
			    },
			    complete:function(){
			        console.log('结束')
			    }
			})
		}
		
		function destroyUser(){
			var rows = $('#dg').datagrid('getSelections');
			if(rows && rows.length > 0){
				var ids = [];
				for(var i=0;i<rows.length;i++){
					ids.push(rows[i].id);
				}
				$.messager.confirm('提示','是否要删除选中记录?',function(r){
					if (r){
						deletes(ids);
					}
				});
			}else{
				$.messager.alert('提示','请选择记录！');
			}
		}
		
	</script>
</body>
</html>