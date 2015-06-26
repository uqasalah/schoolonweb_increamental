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
<title>Add Short Question</title>
</head>
<body>
	<s:actionerror />
	<s:actionmessage />
	<s:form id="formid" name="mcqform"  action="addShortQuestion" method="post">
			<s:radio name="question.dif_int" label="tick difficulty level"
			list="#{'1':'Easy','2':'Medium','3':'Hard'}" value="2" />
		<s:radio name="question.cat_int" label="tick category"
			list="#{'1':'Teachers','2':'Student','3':'Both'}" value="2" />
		<s:doubleselect id="login_username" formName="mcqform"
			name="courseid" doubleName="chapterid" list="courses"
			doubleList="chapters" listKey="idCourse" doubleListKey="idChapter"
			listValue="courseName" doubleListValue="chapterName"
			/>
		<s:textfield name="question.question" label="Enter Question"
			id="login_username" />
		<s:textarea name="answers[0]" label="Enter Answer" wrap="true"
			rows="3" cols="30" />
		<s:hidden name="correct" value="1" />
		<s:submit value="Add Question" align="center" />
	</s:form>
</body>
</html>