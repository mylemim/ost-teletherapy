<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="js/patientWebsocket.js" type="text/javascript">
	
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Patient page</title>
</head>
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

h1 {
	color: white;
	font-family: Arial;
	font-size: 40px;
	text-shadow: 1px 1px #8585AD;
	margin-top: 3%;
}

#video_placeholder {
	position: absolute;
	top: 30%;
	left: 10%;
	border: solid 2px blue;
	overflow: hidden;
}

#image_placeholder {
	position: absolute;
	top: 30%;
	right: 10%;
	width: 30%;
	height: 40%;
	border: solid 2px blue;
}

#video_feed {
	position: absolute;
	top: 21%;
	left: 18%;
	color: blue;
	font-family: Arial;
	font-size: 30px;
	text-shadow: 1px 1px #8585AD;
}

#image_feed {
	position: absolute;
	top: 21%;
	right: 19%;
	color: blue;
	font-family: Arial;
	font-size: 30px;
	text-shadow: 1px 1px #8585AD;
}

#session {
	position: absolute;
	top: 80%;
	left: 35%;
	height: 15%;
	width: 30%;
	background: black;
	opacity: 0.7;
	filter: alpha(opacity = 70);
	text-align: center;
}

#choose_session {
	color: white;
	font-family: Arial;
	font-size: 20px;
	text-shadow: 1px 1px #8585AD;
	margin-top: 10%;
}
</style>

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
					<td>Users<br /> <textarea rows="6" cols="20" id="userField"
							readonly="readonly"></textarea>
					</td>
					<td>Chat Log<br /> <textarea rows="6" cols="50"
							id="chatlogField" readonly="readonly"></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2"><input id="textField" name="name" value="Duke"
						type="text"><br> <input onclick="join();"
						value="Join" type="button"> <input
						onclick="send_message();" value="Chat" type="button"></td>
				</tr>
			</table>

		</form>
	</div>
	<div id="output"></div>

</body>


</html>