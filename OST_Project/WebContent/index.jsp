<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<title>Index page</title>

<head>
<link rel="stylesheet" type="text/css" href="css/index.css">
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