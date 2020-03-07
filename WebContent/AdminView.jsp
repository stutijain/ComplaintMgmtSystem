<%@page import="com.entities.Complaint"%> 
<%@page import="java.util.ArrayList"%> 
<%@page import="java.sql.Connection"%> 
<%@page import="java.sql.DriverManager"%> 
<%@page import="java.sql.ResultSet"%> 
<%@page import="java.sql.SQLException"%> 
<%@page import="java.sql.Statement"%> 
<%@page import="java.io.IOException"%> 

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Profile</title>
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

<style>
th {
	background-color: #00b300;
	color: white;
}

table, th, td {
	border: 1px solid black;
}

th, td {
	padding-left: 2vw;
	padding-right: 2vw;
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
<center><h1>Welcome Admin!</h1></center>
	
	

	<form action="Logout" method="post">
	  <input type="submit" value="Logout" style="float: right;">
	</form>
</div>

<div style="display: flex;">
    Search for Complaints:
	<form action="status" method="post">
	<select name="category" >
		<option value="Pending">Pending</option>
		<option value="In Progress">In Progress</option>
		<option value="Completed">Completed</option>
		<option value="Not completed">Not completed</option>
	</select>
	  <input type="submit" value="Search">
	</form>
	
	&nbsp;&nbsp;
	
	<form action="searchByName" method="post">
	  <input type="search" name="searchByName" placeholder="Search by Name">
	  <input type="submit" value="Search">
	</form>
</div>
<br>
<div>
    <form action="engineerDetails" method="post">
    Click for Details of Engineers
    <input type="submit" value="Details">
	</form>

</div>

<br><br><br><br>


<table style="margin: auto;"> 
 <tr>
   <th><b>Name</b></th> 
   <th><b>Category</b></th> 
  <th><b>Location</b></th> 
  <th><b>Complaint No</b></th> 
  <th><b>Priority</b></th>
  <th><b>Status</b></th>
 </tr> 
 <%ArrayList<Complaint>allComplaints=(ArrayList<Complaint>)request.getAttribute("data");
for(Complaint comp:allComplaints){%>
 <tr> 
	     <form action="expand" method="post">
		     <td><%=comp.getName()%></td> 
		     <td><%=comp.getCategory() %></td> 
		     <td><%=comp.getLocation() %></td> 
		     <td><input type="hidden" name="number" value= <%=comp.getComplaint_no()%> ><%=comp.getComplaint_no() %></td> 
		     <td><%=comp.getPriority() %></td> 
		     <td><%=comp.getCom_status() %></td> 
		     <td> <input type="submit" value="Show Details"> </td>
	     </form>
		
		<form action="deleteComplaint" method="post">
			<%--get complaint number to be used to delete the complaint --%>
			<input type="hidden" name="com_number" value= <%=comp.getComplaint_no()%>>
			<td><input type="submit" value="Delete Complaint"></td>
		</form>
		
	
	
 </tr> 
 <%}%>
 

 
</table>  
<hr style="margin-bottom: -1px;" color="#e6e6e6" size="5">
<div class="navbar">  	   
  <a href="https://igdtuw.ac.in/index.php?option=com_chronocontact&Itemid=124">Feedback</a>
  <a href="https://igdtuw.ac.in/index.php?option=com_contact&view=contact&id=1&Itemid=125">Contact Us</a>
  <br>
  <a href="#Top" style="float: right; color: red; font-size: 14px" > Top </a>
</div>
<hr style="margin-top: -1px; margin-bottom: -1px;" color="#e6e6e6" size="5">
	
</body>

</html>
