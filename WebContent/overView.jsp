<%@page import="alt.sow.util.DataSource"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Overview</title>
<style type="text/css">
<!--
.style20 {font-size: small}
.style21 {font-size: medium}
.style22 {font-family: Arial, Helvetica, sans-serif}
.style23 {font-size: medium; font-family: Arial, Helvetica, sans-serif; }
-->
</style>
</head>
<body>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
		<tr>
			<td width="20%"></td>
			<td width="60%">Here comes details about the System</td>
			<td width="20%"></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td><table width="100%" border="0" cellspacing="2"
					cellpadding="2">
					<tr>
						<th width="50%" scope="col"><div align="left"><span class="style20"><span class="style21"><span class="style22"></span></span></span></div>						</th>
						<th width="50%" scope="col"><div align="left"><span class="style20"><span class="style21"><span class="style22"></span></span></span></div>						</th>
					</tr>
					<tr>
						<td><div align="left" class="style23">Courses</div>						</td>
						<td><div align="left" class="style23">

								<%=DataSource.getListSize("from Course c")%>
							</div>						</td>
					</tr>
					<tr>
						<td><div align="left" class="style23">Questions</div>						</td>
						<td><div align="left" class="style23">
								<%=DataSource.getListSize("from Question q")%>
							</div>						</td>
					</tr>
					<tr>
						<td><div align="left" class="style23">Schools</div>						</td>
						<td><div align="left" class="style23">
								<%=DataSource
					.getListSize("from User u where u.role.roleName='"
							+ DataSource.getInstance().SCHOOL.getRoleName()
							+ "'")%>
							</div>						</td>
					</tr>
					<tr>
						<td><div align="left" class="style23">Teachers</div>						</td>
						<td><div align="left" class="style23">
								<%=DataSource
					.getListSize("from User u where u.role.roleName='"
							+ DataSource.getInstance().TEACHER.getRoleName()
							+ "'")%>
							</div>						</td>
					</tr>
					<tr>
						<td><div align="left" class="style23">Exams</div>						</td>
						<td><div align="left" class="style23">
								<%=DataSource.getListSize("from Exam e ")%>
							</div>						</td>
					</tr>
					<tr>
						<td><div align="left"><span class="style20"><span class="style21"><span class="style22"></span></span></span></div>						</td>
						<td><div align="left"><span class="style20"><span class="style21"><span class="style22"></span></span></span></div>						</td>
					</tr>
				</table>
			</td>
			<td>&nbsp;</td>
		</tr>
	</table>

</body>
</html>