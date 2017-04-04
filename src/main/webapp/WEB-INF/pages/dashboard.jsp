<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dashboard</title>
</head>
<body >
<form name='dashboardform' method='GET'>
<div class="container">
	<h1 class="well">This is dashboard!</h1>
  <input type="submit" onclick="location.href='${pageContext.request.contextPath}/drama'" name="drama" value="drama" /></div>	
  <input type="submit" onclick="location.href='${pageContext.request.contextPath}/user_listitems'" name="user_listitems" value="user_listitems" /></div>	

</form></body>
</html>