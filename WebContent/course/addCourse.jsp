<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<s:head />
<link rel="stylesheet" href="css/style.css">
<style type="text/css">

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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Course</title>
</head>
<body>
	<s:actionerror />
	<s:actionmessage />
	<s:form action="addCourse" method="post">
		<s:textfield name="course.courseName" label="Course Name" />
		<s:submit value="Add Course" align="right" />
	</s:form>
</body>
</html>