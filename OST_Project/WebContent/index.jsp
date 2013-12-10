<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

<style type="text/css">
#header {
	position: absolute;
	top: 0%;
	left: 0%;
	width: 100%;
	height: 20%;
	background: blue;
	opacity: 0.7;
	filter: alpha(opacity = 70);
	box-shadow: 2px 10px 5px #888888;
	text-align: center;
}

h2 {
	color: white;
	font-family: Arial;
	font-size: 40px;
	text-shadow: 1px 1px #8585AD;
	margin-top: 3%;
}

#for_login {
	position: absolute;
	top: 30%;
	left: 35%;
	border: 2px solid blue;
	border-radius: 5px;
	padding-top: 2%;
	padding-left: 2%;
	padding-right: 2%;
	z-index: -1;
}

input {
	padding: 9px;
	border: solid 1px #E5E5E5;
	outline: 0;
	font: normal 13px/100% Verdana, Tahoma, sans-serif;
	width: 200px;
	background: #FFFFFF url('bg_form.png') left top repeat-x;
	background: -webkit-gradient(linear, left top, left 25, from(#FFFFFF),
		color-stop(4%, #EEEEEE), to(#FFFFFF));
	background: -moz-linear-gradient(top, #FFFFFF, #EEEEEE 1px, #FFFFFF 25px);
	box-shadow: rgba(0, 0, 0, 0.1) 0px 0px 8px;
	-moz-box-shadow: rgba(0, 0, 0, 0.1) 0px 0px 8px;
	-webkit-box-shadow: rgba(0, 0, 0, 0.1) 0px 0px 8px;
}

input:hover,input:focus {
	border-color: #C9C9C9;
	-webkit-box-shadow: rgba(0, 0, 0, 0.15) 0px 0px 8px;
}

.form label {
	margin-left: 10px;
	color: #999999;
}

.submit input {
	width: auto;
	padding: 9px 15px;
	background: #617798;
	border: 0;
	font-size: 14px;
	color: #FFFFFF;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	margin-left: 35%;
}

#fer_logo {
	position: absolute;
	top: 30%;
	left: 15%;
}

#ferLogo {
	height: 40%;
	width: 40%;
	margin-left: -12%;
}

.sponsored_by {
	font-family: Arial;
	text-shadow: 1px 1px #8585AD;
}

#MO_logo {
	position: absolute;
	top: 30%;
	left: 73%;
}

#MOLogo {
	margin-left: -10%;
}

#footer {
	position: absolute;
	bottom: 0%;
	left: 0%;
	width: 100%;
	background: black;
	opacity: 0.7;
	filter: alpha(opacity = 70);
	text-align: center;
}

#footer_text {
	color: white;
}
</style>

</head>

<body>

	<div id="header">
		<h2>Computer aided cognitive behavioural therapy</h2>
	</div>

	<div id="for_login">
		<form class="form" method="POST">

			<p class="name">
				<input type="text" name="name" id="name" /> <label for="name">Name</label>
			</p>

			<p class="password">
				<input type="text" name="password" id="password" /> <label
					for="password">Password</label>
			</p>

			<p class="submit">
				<input type="submit" value="Send" />
			</p>

		</form>
	</div>

	<div id="fer_logo">
		<p class="sponsored_by">Sponsored by:</p>
		<img id="ferLogo" src="fer_logo.jpg" alt="FER logo">
	</div>

	<div id="MO_logo">
		<p class="sponsored_by">Sponsored by:</p>
		<img id="MOLogo" src="MO.jpg" alt="MO logo" height="175" width="150">
	</div>

	<div id="footer">
		<p id="footer_text">Developed by ...</p>
	</div>

</body>
</html>