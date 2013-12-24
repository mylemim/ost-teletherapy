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
<body>
	Welcome to the patient page!
	<div style="text-align: center;">
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