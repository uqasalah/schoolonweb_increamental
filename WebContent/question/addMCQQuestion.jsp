<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="sx" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add MCQ</title>
<sx:head />
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
</head>
<body>
	<sx:actionerror />
	<sx:actionmessage />
	<sx:form id="formid" name="mcqform" action="addMCQQuestion"
		method="post">
		<sx:radio name="question.dif_int" label="tick difficulty level"
			list="#{'1':'Easy','2':'Medium','3':'Hard'}" value="2" />
		<sx:radio name="question.cat_int" label="tick category"
			list="#{'1':'Teachers','2':'Student','3':'Both'}" value="2" />
		<sx:doubleselect formName="mcqform" name="courseid"
			doubleName="chapterid" list="courses" doubleList="chapters"
			listKey="idCourse" doubleListKey="idChapter" listValue="courseName"
			doubleListValue="chapterName" />

		<%-- 		<s:doubleselect label="Select Course & Chapter" name="courseid" --%>
		<!-- 			doubleName="chapterid" list="courses" doubleList="chapters" -->
		<!-- 			listKey="idCourse" doubleListKey="idChapter" listValue="courseName" -->
		<!-- 			doubleListValue="chapterName" /> -->

		<sx:textfield name="question.question" label="Enter Question" />
		<sx:radio name="correct" label="tick correct answer" list="{1,2,3,4}"
			value="2" />

		<sx:textfield name="answers[0]" label="Enter Answer No.1" />
		<sx:textfield name="answers[1]" label="Enter Answer No.2" />
		<sx:textfield name="answers[2]" label="Enter Answer No.3" />
		<sx:textfield name="answers[3]" label="Enter Answer No.4" />

		<sx:submit value="Add Question" align="right" />
	</sx:form>
</body>
</html>