<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
</head>
<body>
	<s:actionerror />
	<s:actionmessage />
	<table id="example" class="data display datatable">
		<thead>
			<tr align="justify" valign="middle" bgcolor="#9966CC" height="50px">
				<th align="left" class="style3"><span class="style3">No.</span>
				</th>
				<th align="left" class="style3"><span class="style3">Email</span>
				</th>
				<th align="left" class="style3">Name</th>
				<th align="left" class="style3"><span class="style3">Action</span>
				</th>
				<th align="left" class="style3"><span class="style3">Status</span>
				</th>
				<th align="left" class="style3">Delete</th>
			</tr>
		</thead>
		<tbody class="block">
			<s:iterator value="teacherList" var="user" status="i">
				<tr align="left"
					class="<s:if test="#i.odd == true">odd gradeX</s:if>1 
				  <s:else>even gradeC</s:else>">
					<td><s:property value="#i.count" /></td>
					<td><a
						href="viewProfile?user.idUser=<s:property value="idUser"/>"> <s:property
								value="username" /> </a>
					</td>
					<td><s:property value="profile.nameByNameIdName" /></td>
					<td><s:if test="blocked == false">
							<a onclick="return confirm('Are you sure you want to block user')" href="blockTeacher?user.idUser=<s:property value="idUser"/>"><span
								class="blockStyle">Block</span> </a>
						</s:if> <s:else>
							<a onclick="return confirm('Are you sure you want to unblock user')" href="unBlockTeacher?user.idUser=<s:property value="idUser"/>"><span
								class="unblockStyle">Unblock</span> </a>
						</s:else>
					</td>
					<td><s:if test="blocked == false">
							<span class="unblockStyle">Allowed</span>
							</a>
						</s:if> <s:else>
							<span class="blockStyle">Restricted</span>
							</a>
						</s:else>
					</td>
					<td><a  onclick="return confirm('Are you sure you want to delete')"
						href="deleteTeacher?user.idUser=<s:property value="idUser"/>"><span
							class="deleteStyle"><strong>delete 
					</a> </strong> </span>
					</td>
				</tr>
			</s:iterator>
		</tbody>

	</table>
</html>