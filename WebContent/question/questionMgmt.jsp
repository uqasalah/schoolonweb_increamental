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
<title>Question Management</title>
</head>
<body>
	<s:include value="addMCQQuestion.jsp"></s:include>
	<s:include value="addShortQuestion.jsp"></s:include>
	<s:include value="addLongQuestion.jsp"></s:include>
	<s:include value="questionTable.jsp"></s:include>
</body>
</html>