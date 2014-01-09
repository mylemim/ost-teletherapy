<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title>Therapist page</title>

<head>
<link rel="stylesheet" type="text/plain" href="css/jquery.jqplot.css" />
<script src="js/jquery-2.0.3.js" type="text/javascript"></script>
<script src="js/jquery.jqplot.js" type="text/javascript"></script>
<script src="js/jqplot.barRenderer.js"></script>
<script src="js/jqplot.categoryAxisRenderer.js"></script>

<script src="js/therapist.js"></script>
<link rel="stylesheet" type="text/css" href="css/therapist.css">
</head>

<body>

	<div id="header">
		<h1>Therapist</h1>
	</div>

	<h2 id="first_h">Physiology</h2>
	<div id="plot_wrapper">
		<div id="chart1" class="plot" style="width: 700px; height: 500px;"></div>
	</div>

	<h2 id="second_h">Video</h2>
	<div id="plot_wrapper2">
		<div id="chart2" class="plot" style="width: 700px; height: 500px;"></div>
	</div>

	<h2 id="third_h">Audio</h2>
	<div id="plot_wrapper3">
		<div id="chart3" class="plot" style="width: 700px; height: 500px;"></div>
	</div>

	<div style="text-align: center; margin-top: 3285px;">
		<form action="">

			<table>
				<tr>
					<td>Users<br /> <textarea rows="6" cols="20" id="userField"
							readonly="readonly"></textarea>
					</td>
					<td>Message Log<br /> <textarea rows="6" cols="50"
							id="chatlogField" readonly="readonly"></textarea>
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