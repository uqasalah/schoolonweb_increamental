<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/style.css">
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
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
<!-- END: load jquery -->
<script type="text/javascript" src="js/table/table.js"></script>
<script src="js/setup.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {
		setupLeftMenu();

		$('.datatable').dataTable();
		setSidebarHeight();

	});
</script>
<style type="text/css">
html,body {
	height: 100%;
	min-height: 100%;
	margin: 0;
	padding: 0;
}

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

.data display datatable {
	height: 100%;
	min-height: 100%;
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
	<s:form id="selectId" action="submitSyllabus" theme="simple"
		method="POST">
		<table id="example" border="1" class="data display datatable">

			<thead>
				<tr align="justify" valign="middle" bgcolor="#9966CC" height="50px">
					<th align="left" class="style3"><span class="style3">Select</span>
					<th align="left" class="style3"><span class="style3">No.</span>
					<th align="left" class="st
					yle3"><span class="style3">Chapter
							Name</span>
					</th>
					<th align="left" class="style3">Course Name</th>
					<th align="left" class="style3">Questions</th>
				</tr>
			</thead>
			<tbody class="block">
				<s:iterator value="chapters" var="user" status="i">
					<tr align="left"
						class="<s:if test="#i.odd == true">odd gradeX</s:if>1 
				  <s:else>even gradeC</s:else>">
						<td><s:checkbox name="std[%{#i.index}]" value="false" />
						</td>
						<td><s:property value="#i.count" />
						</td>
						<td><s:property value="chapterName" />
						</td>
						<td><s:property value="course.courseName" />
						</td>
						<td><s:property value="questions.size" />
						</td>
					</tr>
				</s:iterator>
			</tbody>

		</table>
		<s:hidden name="source" value="select"></s:hidden>
		<s:submit value="Next" align="center" />
	</s:form>
</body>
</html>