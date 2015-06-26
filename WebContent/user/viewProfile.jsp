<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Profile</title>
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
	<s:form>
		<s:textfield value="%{user.username}" readonly="true" label="Email" />
		<s:textfield value="%{user.profile.nameByNameIdName}" readonly="true"
			label="Name" />
		<s:textfield value="%{user.lastLogin}" label="Last Login"
			readonly="true" />
		<s:textfield value="%{user.profile.contact.mobile}" readonly="true"
			label="Mobile" />

		<s:textfield value="%{user.profile.contact.address.hno}"
			readonly="true" label="House No." />
		<s:textfield value="%{user.profile.contact.address.street1}"
			label="Street1" readonly="true" />
		<s:textfield value="%{user.profile.contact.address.street2}"
			label="Street2" readonly="true" />

		<s:textfield value="%{user.profile.contact.address.pobox}"
			readonly="true" label="PO Box" />
		<s:textfield value="%{user.profile.contact.address.city}"
			readonly="true" label="City" />
		<s:textfield value="%{user.profile.contact.address.country}"
			label="Country" readonly="true" />

	</s:form>
</body>
</html>