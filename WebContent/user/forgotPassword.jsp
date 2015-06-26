<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<!--[if lt IE 7]> <html class="lt-ie9 lt-ie8 lt-ie7" lang="en"> <![endif]-->
<!--[if IE 7]> <html class="lt-ie9 lt-ie8" lang="en"> <![endif]-->
<!--[if IE 8]> <html class="lt-ie9" lang="en"> <![endif]-->
<!--[if gt IE 8]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Login Form</title>
<link rel="stylesheet" href="css/style.css">
<!--[if lt IE 9]><script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
<head>
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
	<section class="container">
		<div class="login">
			<h1>Enter Details to SoWeb App</h1>
			<s:actionerror />
			<s:form method="post" action="forgotPassword">
				<p>
					<s:textfield name="username" placeholder="Email" />
				</p>

				<p class="submit">
					<s:submit />
				</p>
			</s:form>
		</div>


	</section>

	<section class="about">
		<p class="about-links">&nbsp;</p>
		<p class="about-author">
			&copy; 2012&ndash;2013 <a href="javascript:" target="_blank">School
				On Web</a>
		<p class="about-author">
			An <a href="http://www.understandquranacademy.com/" target="_blank">Understand
				Quran Academy</a> Product.
	</section>
</body>
</html>
