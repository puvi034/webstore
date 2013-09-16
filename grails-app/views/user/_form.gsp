<%@ page import="webstore.User" %>



<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'fullName', 'error')} ">
	<label for="fullName">
		<g:message code="user.fullName.label" default="Full Name" />
		
	</label>
	<g:textField name="fullName" value="${userInstance?.fullName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'password', 'error')} ">
	<label for="password">
		<g:message code="user.password.label" default="Password" />
		
	</label>
	<g:textField name="password" value="${userInstance?.password}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'userName', 'error')} ">
	<label for="userName">
		<g:message code="user.userName.label" default="User Name" />
		
	</label>
	<g:textField name="userName" value="${userInstance?.userName}"/>
</div>

