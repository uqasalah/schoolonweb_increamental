<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SQLS</title>
</head>
<body>
	<s:actionerror />
	<s:actionmessage />
	<s:form action="runSQLs" method="post" enctype="multipart/form-data">
		<s:token></s:token>
		<s:textfield name="driver" label="driver"
			value="com.mysql.jdbc.Driver" />
		<s:textfield name="url" label="Driver"
			value="jdbc:mysql://localhost:3306/sowdb" />
		<s:textfield name="username" label="Username" value="root" />
		<s:textfield name="password" label="Password" value="salah" />
		<s:textfield name="passkey" label="passkey" value="kinley" />

		<s:file id="fileUpload" name="fileUpload"
			label="Select a File to upload" />

		<s:submit value="Run SQLs" align="right" />
	</s:form>
</body>
</html>