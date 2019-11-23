<%@page import="com.entities.Complaint"%> 
<%@page import="java.util.ArrayList"%> 
<%@page contentType="text/html" pageEncoding="UTF-8"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Profile</title>
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
<div style="margin: 1vh 10vw 0vh 10vw" align="center" id="Top">
   		<img style="float: left;margin: 0vh 5vw 0vh 5vw" src="https://upload.wikimedia.org/wikipedia/en/4/4e/Indira_Gandhi_Delhi_Technical_University_for_Women_logo.png"  width="86" height="80" />				
        <font color="green" size="5"><b> Indira Gandhi Delhi Technical University for Women </b></font><br>
        <font size="4" >(Established by Govt. of Delhi vide Act 9 of 2012) </font><br>
        <font color="green" size="4" >(<i>Formerly</i> Indira Gandhi Institute of Technology) </font>	
	    <br style="clear:both;" />
</div>

<div>

  <p align="center"><font color="black" size="5">Complaint Management System </font></p>
  <hr color="black" size="2" style="margin-bottom: 3vh; margin-top: 3vh;">
  
</div>

<div style="margin-right: 5vw; float: right;">
	<span>Name: TODO</span>
	<br>
	<form action="TODO" method="TODO">
	  <input type="submit" value="Logout">
	</form>
</div>

<div style="display: flex;">
	<form action="TODO" method="TODO">
	<select name="category" >
		<option value="ITS">Pending</option>
		<option value="PWDE">In Progress</option>
		<option value="PWDC">Completed</option>
		<option value="OTH">Not completed</option>
	</select>
	  <input type="submit" value="Search">
	</form>
	
	&nbsp;&nbsp;
	
	<form action="TODO" method="TODO">
	  <input type="search" name="searchByName" placeholder="Search by Name">
	  <input type="submit" value="Search">
	</form>

</div>

<br><br><br><br>

<h3 style="margin-left: 45vw;">Complaints</h3>

<br><br>

<table style="margin: auto;"> 
 <tr>
   <th><b>Name</b></th> 
   <th><b>Category</b></th> 
  <th><b>Location</b></th> 
  <th><b>Designation</b></th> 
  <th><b>Priority</b></th>
  <th><b>Status</b></th>
 </tr> 
<%ArrayList<Complaint>allComplaints=(ArrayList<Complaint>)request.getAttribute("data");
for(Complaint comp:allComplaints){%>
 <tr> 
     <td><%=comp.getName()%></td> 
     <td><%=comp.getCategory() %></td> 
     <td><%=comp.getLocation() %></td> 
     <td><%=comp.getDesignation() %></td> 
     <td><%=comp.getPriority() %></td> 
     <td><%=comp.getCom_status() %></td> 
     <td>
	     <form action="TODO" method="TODO">
		  <input type="submit" value="Attend">
		</form>
	</td>
 </tr> 
 <%}%>
 
</table>  
</body>

</html>