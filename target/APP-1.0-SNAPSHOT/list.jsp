<%-- 
    Document   : list
    Created on : 8 may. 2025, 22:06:44
    Author     : alexa
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Users List</title>
</head>
<body>
    <h2>List of Users</h2>
    <a href="${pageContext.request.contextPath}/User?option=new">Add New User</a>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th><th>Name</th><th>Last Name</th><th>Age</th><th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.lastname}</td>
                    <td>${user.age}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/User?option=update&id=${user.id}">Edit</a>
                        |
                        <a href="${pageContext.request.contextPath}/User?option=delete&id=${user.id}" onclick="return confirm('Are you sure?')">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
