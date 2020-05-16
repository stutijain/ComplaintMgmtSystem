<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    



<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Booking</title>
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

	
	<hr style="margin-bottom: -1px;" color="#e6e6e6" size="5">
	<div class="topnav">


  <p align="center"><font color="white" size="5">Complaint Management System </font></p>
  <hr color="black" size="2" style="margin-bottom: 3vh; margin-top: 3vh;">
  
 </div>

 <br>
	 
	<form style="margin-left: 20px">
		<a href="HomePage.html"><-- Go Back</a>
	</form>


<center><h1>Booking</h1></center>

<div style="text-align: left;">

		<form action="book" method="post">
			<p style="margin: 0px 0px 0px 100px">
				<font size="3">Booking Options : </font> <select name="category"
					required="required">
					<option value="audi">AUDITORIUM</option>
					<option value="seminar">SEMINAR HALL</option>
					<option value="conference">CONFERENCE HALL</option>
					<option value="vehicle">UNIVERSITY VEHICLE</option>
				</select>
			</p>

			<br>

			<p style="margin: 0px 0px 0px 100px">
				<font size="3">Name : </font><input type="text" name="name"
					required="required">
			</p>

			<br>

			<p style="margin: 0px 0px 0px 100px">
				<font size="3">Date : </font><input type="date" name="date"
					required="required">
			</p>

			<br>

			<p style="margin: 0px 0px 0px 100px">
				<font size="3">Time : </font>
				<small>Booking hours are 9am to 5pm</small>
				<br>
				<font size="2">From :</font>
				<input type="time" min="09:00" max="17:00" required="required"
					name="time_from">
					
					
					<font size="2"> To :</font>
					<input type="time" min="09:00" max="17:00" required="required"
					name="time_to">
			</p>

			<br>

			<p style="margin: 0px 0px 0px 100px">
				<font size="3">Details(Purpose of Booking) : </font><input type="text" name="details"
					required="required">
			</p>

			<br>

			<p style="margin: 0px 0px 0px 100px">
				<font size="3">Contact Number : </font><input type="text" name="contact"
					required="required">
			</p>

			<br>

			<p style="margin: 0px 0px 0px 100px">
				<font size="3">Email ID : </font><input type="email" name="email"
					required="required">
			</p>

			<br>

			<p style="margin: 0px 0px 0px 200px">
				<input type="submit" value="Submit">
			</p>
		</form>
		</div>
<br>

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