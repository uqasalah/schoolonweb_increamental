<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<style>
body,input {
	font-family: Calibri, Arial;
}

table#contact {
	border-collapse: collapse;
	width: 550px;
}

th {
	height: 40px;
	background-color: #ffee55;
}
</style>
<title>Administrator Management</title>
</head>
<body>
	<s:include value="addAdmin.jsp"></s:include>
	<s:include value="adminTable.jsp"></s:include>
</body>
</html>