<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form:form id="loginForm" servletRelativeAction="/login"
			commandName="loginForm" method="POST" autocomplete="off">
			<h3>Login</h3>
			<c:if test="${not empty errorMessage}">
			<label><strong><font color="red">${errorMessage}</font></strong></label><br/>
			</c:if>
			<label for="username">Username:</label> <form:input path="username"/><br/>
			<label for="password">Password:</label> <form:input path="password"/><br/>
			<button type="submit" name="login" value="stampaElenco">Login</button>
</form:form>