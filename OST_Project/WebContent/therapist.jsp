<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

<link rel="stylesheet" type="text/plain"
	href="https://bitbucket.org/cleonello/jqplot/raw/b5a7796a9ebf/src/jquery.jqplot.css" />

<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script
	src="https://bitbucket.org/cleonello/jqplot/raw/b5a7796a9ebf/src/jquery.jqplot.js"
	type="text/javascript"></script>
<script
	src="https://bitbucket.org/cleonello/jqplot/raw/b5a7796a9ebf/src/plugins/jqplot.barRenderer.js"></script>
<script
	src="https://bitbucket.org/cleonello/jqplot/raw/b5a7796a9ebf/src/plugins/jqplot.categoryAxisRenderer.js"></script>

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

</body>

</html>