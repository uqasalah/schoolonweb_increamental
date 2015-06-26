<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Teacher</title>
<s:head />
<style type="text/css">
<link rel="stylesheet" href="css/style.css">
.errorsBg {
	background-color: #fdd;
	color: red;
	border: 1px solid;
}

.errorMessage {
	padding: 0px 8px;
}

table {
	border-spacing: 4px;
}

td {
	padding: 4px;
}
</style>
</head>
<body>
	<s:actionerror />
	<s:actionmessage />
	<s:form action="addTeacher" method="post">
		<s:textfield name="user.username" label="Email ID" id="login_username" />
		<s:submit value="Add Teacher" align="right" />
	</s:form>
</body>
</html>