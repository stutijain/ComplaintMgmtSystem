<%@page import="com.entities.Complaint"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Complaint Details</title>
<style>
body {
	font-family: "Lato", sans-serif;
}

.navbar {
	overflow: hidden;
	background-color: black;
	top: 0;
}

.navbar a {
	top: 0;
	color: white;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
	font-size: 17px;
}

.topnav {
	overflow: hidden;
	background: linear-gradient(to bottom, #009933 0%, #003300 100%);
	top: 0;
}

.topnav a {
	color: #f2f2f2;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
	font-size: 17px;
}

.topnav a:hover {
	color: orange;
}

.topnav a.active {
	background-color: #4CAF50;
	color: white;
}

form {
	vertical-align: top;
}
</style>
</head>

<body>
	<div style="margin: 10px 200px 0px 200px" align="center" id="Top">

		<img style="float: left; margin: 0px -120px 0px 120px"
			src="https://upload.wikimedia.org/wikipedia/en/4/4e/Indira_Gandhi_Delhi_Technical_University_for_Women_logo.png"
			width="86" height="80" /> <font color="green" size="5"><b>
				Indira Gandhi Delhi Technical University for Women </b></font><br> <font
			size="4">(Established by Govt. of Delhi vide Act 9 of 2012) </font><br>
		<font color="green" size="4">(<i>Formerly</i> Indira Gandhi
			Institute of Technology)
		</font> <br style="clear: both;" />
	</div>


	<hr style="margin-bottom: -1px;" color="#e6e6e6" size="5">
	<div class="topnav">


		<p align="center">
			<font color="white" size="5">Complaint Management System </font>
		</p>
		<hr color="black" size="2"
			style="margin-bottom: 3vh; margin-top: 3vh;">

	</div>
	
	<br>
	 

	<form style="margin-left: -1px">

		<a href="javascript:history.back()"><-- Go Back</a>
	</form>

		
	<center>
		<h1>Search details</h1>
	</center>
	<table width=25% border=1
		style="border: 1px solid black; margin-left: auto; margin-right: auto;">
		<tr>
			<th><b>Name</b></th>
			<th><b>Category</b></th>
			<th><b>Location</b></th>
			<th><b>Complaint No</b></th>
			<th><b>Priority</b></th>
			<th><b>Status</b></th>
		</tr>
		<tr>
			<%
				ArrayList<Complaint> allComplaints = (ArrayList<Complaint>) request.getAttribute("data");
				for (Complaint comp : allComplaints) {
			%>

			<td><%=comp.getName()%></td>
			<td><%=comp.getCategory()%></td>
			<td><%=comp.getLocation()%></td>
			<td><input type="hidden" name="number"
				value=<%=comp.getComplaint_no()%>><%=comp.getComplaint_no()%></td>
			<td><%=comp.getPriority()%></td>
			<td><%=comp.getCom_status()%></td>

		</tr>
		<%
			}
		%>
	</table>

	
</body>
</html>