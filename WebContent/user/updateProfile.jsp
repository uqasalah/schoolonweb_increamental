<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-dojo-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Profile</title>
<sj:head />
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
</head>
<body>

	<s:actionerror />
	<s:form action="updateProfile">
		<s:textfield name="user.username" readonly="true" label="Username"
			placeholder="Username" value="%{#session['currentUser'].username}"
			required="true" />
		<s:password name="user.password" label="Password"
			value="%{#session['currentUser'].password}" required="true" />
		<s:password name="retype" label="retype password" required="true" />
		<s:textfield name="user.profile.nameByNameIdName.firstName"
			label="First Name"
			value="%{#session['currentUser'].profile.nameByNameIdName.firstName}" />
		<s:textfield name="user.profile.nameByNameIdName.lastName"
			label="Last Name"
			value="%{#session['currentUser'].profile.nameByNameIdName.lastName}" />
		<s:if test="%{#session['currentUser'].role.roleName == 'teacher'">
			<s:textfield name="user.profile.organisation.organisationName"
				label="Organisation" required="true"
				value="%{#session['currentUser'].profile.organisation.organisationName}"
				readonly="true" />
		</s:if>
		<s:elseif test="#session['currentUser'].firstLogin == false">
			<s:textfield name="user.profile.organisation.organisationName"
				label="Organisation" required="true"
				value="%{#session['currentUser'].profile.organisation.organisationName}"
				readonly="true" />
		</s:elseif>
		<s:else>
			<s:textfield name="user.profile.organisation.organisationName"
				label="Organisation" required="true"
				value="%{#session['currentUser'].profile.organisation.organisationName}"
				readonly="false" />
		</s:else>

		<s:label>
			<sj:datetimepicker name="user.profile.birthDate" label="Birthdate"
				displayFormat="dd-MMM-yyyy"
				value="%{#session['currentUser'].profile.birthDate}" />
		</s:label>
		<s:textfield name="user.profile.contact.mobile" label="Mobile"
			required="true"
			value="%{#session['currentUser'].profile.contact.mobile}" />
		<s:textfield name="user.profile.contact.address.hno" label="House No."
			value="%{#session['currentUser'].profile.contact.address.hno}" />
		<s:textfield name="user.profile.contact.address.street1"
			label="Street No.1"
			value="%{#session['currentUser'].profile.contact.address.street1}" />
		<s:textfield name="user.profile.contact.address.street2"
			label="Street No.2"
			value="%{#session['currentUser'].profile.contact.address.street2}" />
		<s:textfield name="user.profile.contact.address.pobox" label="PO Box"
			value="%{#session['currentUser'].profile.contact.address.pobox}" />
		<s:textfield name="user.profile.contact.address.city" label="City"
			value="%{#session['currentUser'].profile.contact.address.city}" />
		<s:textfield name="user.profile.contact.address.country"
			label="Country"
			value="%{#session['currentUser'].profile.contact.address.country}" />
		<s:submit value="Update" />
	</s:form>
</body>
</html>