
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Manager </title>
    </head>
<body>
    <div align="center">
            <h1>User List</h1>
            <h3><a href="kalravweb/user_grid">List of User</a></h3>
            <table border="1">
                <th>No</th>
                <th>Name</th>
                <th>Email</th>
                <th>Address</th>
                <th>Phone</th>
                 
                <c:forEach var="user_grid" items="${listUsers}" varStatus="status">
                <tr>
                    <td>${status.index + 1}</td>
                    <td>${users.username}</td>
                    <td>${users.email}</td>
                    <td>${users.address}</td>
                    <td>${users.phone}</td>
                   
                             
                </tr>
                </c:forEach>             
            </table>
        </div>
</body>
</html>