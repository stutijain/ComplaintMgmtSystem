<%@page import="com.entities.Complaint"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

	<br>

	<hr style="margin-bottom: -1px;" color="#e6e6e6" size="5">
	<div class="topnav">


  <p align="center"><font color="white" size="5">Complaint Management System </font></p>
  <hr color="black" size="2" style="margin-bottom: 3vh; margin-top: 3vh;">
  
 </div>
<center><h1>Complaint details</h1></center>
<%Complaint comp=(Complaint)request.getAttribute("data"); %>

<form action="attend" method="post" style="margin-bottom: 3vh; margin-top: 3vh;">
	<select name="category" >
		<option value="Pending">Pending</option>
		<option value="In Progress">In Progress</option>
		<option value="Completed">Completed</option>
		<option value="Not completed">Not completed</option>
	</select>
	<input type="hidden" name="number" value= <%=comp.getComplaint_no()%> >
	<input type="submit" value="Change Complaint Status" >
</form>
  
<table width=25% border=1 style="border:1px solid black;margin-left:auto;margin-right:auto;">
<tr>
<td>Complaint number</td>
<td><%=comp.getComplaint_no()%></td>
</tr>
<tr>
<td>Category</td>
<td><%=comp.getCategory()%></td>
</tr>
<tr>
<td>Location</td>
<td><%=comp.getLocation()%></td>
</tr>
<tr>
<td>Sub Location</td>
<td><%=comp.getSub_location()%></td>
</tr>
<tr>
<td>Details</td>
<td><%=comp.getDetails()%></td>
</tr>
<tr>
<td>Priority</td>
<td><%=comp.getPriority()%></td>
</tr>
<tr>
<td>Name</td>
<td><%=comp.getName()%></td>
</tr>
<tr>
<td>Designation</td>
<td><%=comp.getDesignation()%></td>
</tr>
<tr>
<td>Email</td>
<td><%=comp.getEmail()%></td>
</tr>
<tr>
<td>Contact</td>
<td><%=comp.getContact()%></td>
</tr>
<tr>
<td>Time</td>
<td><%=comp.getTime()%></td>
</tr>
<tr>
<td>Date</td>
<td><%=comp.getDate()%></td>
</tr>
<tr>
<td>Complaint Status</td>
<td><%=comp.getCom_status()%></td>
</tr>
	</table>
	<hr style="margin-bottom: -1px;" color="#e6e6e6" size="5">
	<div class="navbar">
		<a
			href="https://igdtuw.ac.in/index.php?option=com_chronocontact&Itemid=124">Feedback</a>
		<a
			href="https://igdtuw.ac.in/index.php?option=com_contact&view=contact&id=1&Itemid=125">Contact
			Us</a> <br> <a href="#Top"
			style="float: right; color: red; font-size: 14px"> Top </a>
	</div>
	<hr style="margin-top: -1px; margin-bottom: -1px;" color="#e6e6e6"
		size="5">
</body>
</html>