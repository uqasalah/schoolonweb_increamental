<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-dojo-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Setup Exam Details</title>
<sj:head />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>All Chapters</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/reset.css"
	media="screen" />
<link rel="stylesheet" type="text/css" href="css/text.css"
	media="screen" />
<link rel="stylesheet" type="text/css" href="css/grid.css"
	media="screen" />
<link rel="stylesheet" type="text/css" href="css/layout.css"
	media="screen" />
<link rel="stylesheet" type="text/css" href="css/nav.css" media="screen" />
<!--[if IE 6]><link rel="stylesheet" type="text/css" href="css/ie6.css" media="screen" /><![endif]-->
<!--[if IE 7]><link rel="stylesheet" type="text/css" href="css/ie.css" media="screen" /><![endif]-->
<link href="css/table/demo_page.css" rel="stylesheet" type="text/css" />
<!-- BEGIN: load jquery -->
<script src="js/jquery-1.6.4.min.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery-ui/jquery.ui.core.min.js"></script>
<script src="js/jquery-ui/jquery.ui.widget.min.js"
	type="text/javascript"></script>
<script src="js/jquery-ui/jquery.ui.accordion.min.js"
	type="text/javascript"></script>
<script src="js/jquery-ui/jquery.effects.core.min.js"
	type="text/javascript"></script>
<script src="js/jquery-ui/jquery.effects.slide.min.js"
	type="text/javascript"></script>
<script src="js/jquery-ui/jquery.ui.mouse.min.js" type="text/javascript"></script>
<script src="js/jquery-ui/jquery.ui.sortable.min.js"
	type="text/javascript"></script>
<script src="js/table/jquery.dataTables.min.js" type="text/javascript"></script>
<title>All School Accounts</title>
<link rel="stylesheet" href="css/style.css">
<style type="text/css">
<!--
.style3 {
	font-family: Arial, Helvetica, sans-serif;
	font-size: medium;
}

.deleteStyle {
	color: #FF0000
}

.unblockStyle {
	color: #00A452
}

.blockStyle {
	color: #FF0080
}

.emailStyle {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-weight: bold;
	color: #8080FF;
}

.style7 {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-weight: bold;
	color: #8080FF;
	font-style: italic;
}
-->
</style>
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
	<s:form action="configExamAction" method="post">
		<s:radio name="exam.cat" label="tick category"
			list="#{'1':'Teachers','2':'Student'}" value="2" />
		<s:textfield name="user.profile.organisation.organisationName"
			readonly="true"
			value="%{#session['currentUser'].profile.organisation.organisationName}" />
		<sj:datetimepicker name="exam.conductedOn" displayFormat="dd-MMM-yyyy"
			value="%{'today'}" label="Exam Date" type="date" />
		<s:textfield name="exam.time" label="time in mins" />
		<s:textfield name="mcq_noq" label="no.s of mcq questions" />
		<s:textfield name="mcq_mks" label="marks per mcq questions" />
		<s:textfield name="sa_noq" label="no.s of short answer questions" />
		<s:textfield name="sa_mks" label="marks per short answer questions" />
		<s:textfield name="la_noq" label="no.s of long answer questions" />
		<s:textfield name="la_mks" label="marks per long answer questions" />
		<s:hidden name="source" value="config"></s:hidden>
		<s:submit value="Submit" />
	</s:form>
</body>
</html>