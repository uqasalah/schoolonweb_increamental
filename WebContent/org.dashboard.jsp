<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="alt.sow.domain.User"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Welcome | School on Web</title>
<link rel="stylesheet" href="css/style.css">
	<link rel="stylesheet" type="text/css" href="css/reset.css"
		media="screen" />
	<link rel="stylesheet" type="text/css" href="css/text.css"
		media="screen" />
	<link rel="stylesheet" type="text/css" href="css/grid.css"
		media="screen" />
	<link rel="stylesheet" type="text/css" href="css/layout.css"
		media="screen" />
	<link rel="stylesheet" type="text/css" href="css/nav.css"
		media="screen" />
	<!--[if IE 6]><link rel="stylesheet" type="text/css" href="css/ie6.css" media="screen" /><![endif]-->
	<!--[if IE 7]><link rel="stylesheet" type="text/css" href="css/ie.css" media="screen" /><![endif]-->
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
	<!-- END: load jquery -->
	<!-- BEGIN: load jqplot -->
	<link rel="stylesheet" type="text/css" href="css/jquery.jqplot.min.css" />
	<!--[if lt IE 9]><script language="javascript" type="text/javascript" src="js/jqPlot/excanvas.min.js"></script><![endif]-->
	<script language="javascript" type="text/javascript"
		src="js/jqPlot/jquery.jqplot.min.js"></script>
	<script language="javascript" type="text/javascript"
		src="js/jqPlot/plugins/jqplot.barRenderer.min.js"></script>
	<script language="javascript" type="text/javascript"
		src="js/jqPlot/plugins/jqplot.pieRenderer.min.js"></script>
	<script language="javascript" type="text/javascript"
		src="js/jqPlot/plugins/jqplot.categoryAxisRenderer.min.js"></script>
	<script language="javascript" type="text/javascript"
		src="js/jqPlot/plugins/jqplot.highlighter.min.js"></script>
	<script language="javascript" type="text/javascript"
		src="js/jqPlot/plugins/jqplot.pointLabels.min.js"></script>
	<!-- END: load jqplot -->
	<script src="js/setup.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			setupDashboardChart('chart1');
			setupLeftMenu();
			setSidebarHeight();

		});
	</script>
	<style type="text/css">
html, body {
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

.container_12 {
	height: 100%;
	min-height: 100%;
}
-->
</style>
</head>
<body>
	<div class="container_12" id="container">
		<div class="grid_12 header-repeat">
			<div id="branding">
				<div class="floatleft">
					<img src="img/logo.png" alt="Logo" />
				</div>
				<div class="floatright">
					<div class="floatleft">
						<img src="img/img-profile.jpg" alt="Profile Pic" />
					</div>
					<div class="floatleft marginleft10">
						<ul class="inline-ul floatleft">
							<li>Hello <s:property
									value="%{#session['currentUser'].username}" />
							</li>
							<li><a href="<s:url action="updateProfile"/>">Change
									Profile</a></li>
							<li><a href="<s:url action="access"/>"></a></li>
							<li><a href="<s:url action="logout"/>">Logout</a></li>
						</ul>
						<%
							Date d = ((User) session.getAttribute("currentUser"))
									.getLastLogin();
						%>
						<br /> <span class="small grey">Last Login : <%=((d == null) ? "First Login" : SimpleDateFormat
					.getInstance().format(d))%>
						</span>
					</div>
				</div>
				<div class="clear"></div>
			</div>
		</div>
		<div class="clear"></div>
		<div class="grid_12">
			<ul class="nav main">
				<s:if
					test="%{#session['currentUser'].role.roleName == 'debug' || #session['currentUser'].role.roleName == 'administrator'}">
					<li class="ic-dashboard"><a href="javascript:"><span>Manage
								Administrators</span> </a>
						<ul>
							<li><a href="<s:url action="addAdmin"/>">Add New
									Administrator</a></li>
							<li><a href="<s:url action="listAdmin"/>">All
									Administrators</a></li>
						</ul></li>
				</s:if>
				<s:if
					test="%{#session['currentUser'].role.roleName == 'debug' || #session['currentUser'].role.roleName == 'administrator'  }">
					<li class="ic-form-style"><a href="javascript:"><span>Manage
								Schools</span> </a>
						<ul>
							<li><a href="<s:url action="addSchool"/>">Add New School</a>
							</li>
							<li><a href="<s:url action="listSchool"/>">All Schools</a></li>
						</ul></li>
				</s:if>
				<s:if
					test="%{#session['currentUser'].role.roleName == 'debug' || #session['currentUser'].role.roleName == 'school'  }">
					<li class="ic-charts"><a href="javascript:"><span>Manage
								Teachers</span> </a>
						<ul>
							<li><a href="<s:url action="addTeacher"/>">Add New
									Teacher</a></li>
							<li><a href="<s:url action="listTeacher"/>">All Teachers</a>
							</li>
						</ul></li>
				</s:if>
				<s:if
					test="%{#session['currentUser'].role.roleName == 'debug' || #session['currentUser'].role.roleName == 'administrator'}">
					<li class="ic-grid-tables"><a href="javascript:"><span>Manage
								Syllabus</span> </a>
						<ul>
							<li><a href="<s:url action="listCourse"/>">All Courses</a></li>
							<li><a href="<s:url action="listChapter"/>">All Chapters</a>
							</li>
							<li><a href="<s:url action="listQuestion"/>">All
									Questions</a></li>
							<li><a href="<s:url action="addCourse"/>">Add New Course</a>
							</li>
							<li><a href="<s:url action="addChapter"/>">Add New
									Chapter</a></li>
							<li><a href="<s:url action="addMCQQuestion"/>">Add New
									MCQ</a></li>
							<li><a href="<s:url action="addShortQuestion"/>">Add
									Short Answer Question</a></li>
							<li><a href="<s:url action="addLongQuestion"/>">Add Long
									Answer Question</a></li>
						</ul></li>
				</s:if>
				<s:if
					test="%{#session['currentUser'].role.roleName == 'debug' || #session['currentUser'].role.roleName == 'teacher'}">
					<li class="ic-gallery dd"><a href="javascript:"><span>Manage
								Exam</span> </a>
						<ul>
							<li><a href="<s:url action = "selectSyllabus"/>">Generate
									Exam </a></li>
						</ul></li>
				</s:if>

			</ul>
		</div>

		<div class="clear"></div>
		<div class="clear"></div>
		<table width="100%" align="center">
			<tr>
				<td height="600">
					<div class="grid_12" id="bodyCont">
						<div class="box round first fullpage">
							<h2>
								<tiles:insertAttribute name="title" />
							</h2>
							<div class="block">
								<tiles:insertAttribute name="content" />
							</div>
						</div>
					</div>
					<div class="clear"></div>
				</td>
			</tr>
		</table>
	</div>

	<!-- <div id="site_info"  >
		<p>
			Copyright <a href="http://www.understandquran.com">Understand
				Al'Quran</a>. All Rights Reserved.
		</p>
	</div> -->

</body>
</html>
