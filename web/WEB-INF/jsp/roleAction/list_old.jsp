<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工岗位</title>
</head>
<body>
	<s:iterator value="#roleList">
		<s:property value="id"/>,
		<s:property value="name"/>,
		<s:property value="description"/>,
		<s:a action="role_delete?id=%{id}" onclick="return confirm('确定要删除吗？')">删除</s:a>
		<s:a action="role_editUI?id=%{id}">修改</s:a>
		<br>
	</s:iterator>
	<s:a action="role_addUI">添加</s:a>
</body>
</html>