<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Chapter</title>

<s:head />

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
</head>
<body>
	<s:actionerror />
	<s:actionmessage />
	<s:form action="addChapter" method="post">
		<s:select name="chapter.course.idCourse" list="courseList"
			type="select" listKey="idCourse" listValue="courseName"
			label="Select Course" id="login_username" />
		<s:textfield name="chapter.chapterName" label="Chapter Name"
			id="select" />
		<s:submit value="Add Chapter" align="right" />
	</s:form>
</body>
</html>