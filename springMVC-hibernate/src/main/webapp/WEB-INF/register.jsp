<%@ page session="false" language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>Register</title>
</head>
<body>
<h1>Registration Details</h1>
<form:form method="Post" action="register" modelAttribute="user">
<table>
<tr>
<td>First Name:</td>
<td><form:input path="userName" /></td>
<td><font color="red"><form:errors path="userName" /></font></td></tr>
<tr>
<td>Last Name:</td>
<td><form:input path="userId" /></td>
<td><font color="red"><form:errors path="userId" /></font></td></tr>
</table>
<input type="submit" value="Submit" />
</form:form>
</body>
</html>