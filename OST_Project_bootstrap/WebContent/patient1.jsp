<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title>Patient page</title>
<head>
<script src="js/jquery-2.0.3.js" type="text/javascript"></script>
<script src="js/patient.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="css/patient.css">
</head>

<body>

	<div id="header">
		<h1>Patient</h1>
	</div>

	<h2 id="video_feed">Video feed</h2>
	<h2 id="image_feed">Image feed</h2>
	<div id="video_placeholder">
		<iframe width="405" height="262"
			src="http://www.youtube.com/embed/zLLOG-LXVdE">Your browser
			does not support inline frames or is currently configured not to
			display inline frames </iframe>
	</div>
	<div id="image_placeholder"></div>

	<div id="session">
		<h2 id="choose_session">Choose another session</h2>
	</div>

	<div style="text-align: center; margin-top: 872px;">
		<form action="">

			<table>
				<tr>
					<td>Users<br /> <textarea rows="25" cols="20" id="userField"
							readonly="readonly"></textarea>
					</td>
					<td>Message Log<br /> <textarea rows="25" cols="80"
							id="avLogField" readonly="readonly"></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2"> <br>  <input id="chatButton" value="Generate"
						type="button"></td>
				</tr>
			</table>

		</form>
	</div>
	<div id="output"></div>

</body>


</html>