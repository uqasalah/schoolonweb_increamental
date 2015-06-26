<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Preview / Download</title>
<style type="text/css">
<!--
.style1 {
	font-family: Arial, Helvetica, sans-serif
}
-->
</style>
</head>
<body>
	<s:url action="fileDownloadS" namespace="/" id="fds"></s:url>
	<s:url action="fileDownloadT" namespace="/" id="fdt"></s:url>
	<s:url action="filePreviewS" namespace="/" id="fps"></s:url>
	<s:url action="filePreviewT" namespace="/" id="fpt"></s:url>
	<table width="50%" border="0" cellspacing="2" cellpadding="2">
		<tr>
			<th scope="col">&nbsp;</th>
			<th scope="col">&nbsp;</th>
			<th scope="col">&nbsp;</th>
		</tr>
		<tr>
			<td class="style1">Student Copy</td>
			<td class="style1"><s:a href="%{fds}">Download</s:a></td>
			<td class="style1"><s:a href="%{fps}">Preview</s:a></td>
		</tr>
		<tr>
			<td class="style1">Teachers Copy</td>
			<td class="style1"><s:a href="%{fdt}">Download</s:a></td>
			<td class="style1"><s:a href="%{fpt}">Preview</s:a></td>
		</tr>
	</table>
</body>
</html>