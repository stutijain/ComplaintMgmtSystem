<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cancel Booking</title>
<style>
body {
	text-align: center;
}

form {
	width: 500px;
	margin: 0 auto;
}

.navbar {
	overflow: hidden;
	background-color: black;
	top: 0;
}

.navbar a {
	float: left;
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
	float: left;
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
		<hr color="black" size="2"
			style="margin-bottom: -1px; margin-top: -1px;">
		<a href="HomePage.html" style="margin-left: 20px;">Home</a> <a
			href="https://igdtuw.ac.in/index.php?option=com_content&view=article&id=1&Itemid=2">About
			Us</a>
	</div>
	<br>
	 
	<form style="margin-left: -180px">
		<a href="HomePage.html"><-- Go Back</a>
	</form>

		
	<h1 align="center">
		<font color="green" size="5"> CANCEL BOOKING</font>
	</h1>
	<br>

	<form action="cancelBooking" method="post"><h4>
		Enter Booking Number To Cancel : <input type="text" name="bookNum"></h4>
		
		<br> <input type="submit" value="Submit">
	</form>
	

	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
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