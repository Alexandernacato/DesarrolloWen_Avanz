<%-- 
    Document   : formUser
    Created on : 8 may. 2025, 22:05:28
    Author     : alexa
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Form</title>
</head>
<body>
    <h2>${user != null ? "Edit User" : "New User"}</h2>
    <form action="${pageContext.request.contextPath}/User" method="post">
        <c:if test="${user != null}">
            <input type="hidden" name="id" value="${user.id}" />
        </c:if>
        <label for="name">Name:</label>
        <input type="text" name="name" value="${user != null ? user.name : ''}" required /><br/>

        <label for="lastname">Last Name:</label>
        <input type="text" name="lastname" value="${user != null ? user.lastname : ''}" required /><br/>

        <label for="age">Age:</label>
        <input type="number" name="age" value="${user != null ? user.age : ''}" required /><br/>

        <button type="submit">Save</button>
        <a href="${pageContext.request.contextPath}/User">Cancel</a>
    </form>
</body>
</html>
