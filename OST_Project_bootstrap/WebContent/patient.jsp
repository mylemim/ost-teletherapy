<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.File"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Patient page</title>

<!-- Bootstrap core CSS -->
<link href="dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="dist/css/therapist.css" rel="stylesheet">

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>

<body>

	<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Patient</a>
			</div>
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>

	<div class="container" id="main-container">

		<!-- prvi red -->
		<div class="row">
			<div class="col-md-4 border-bottom">
				<!-- Therapist -->
				<div class="row">
					<div class="col-md-12">
						<h3>
							<span class="glyphicon glyphicon-user"></span> Therapist: 
							<span id="therapist-state" class="label label-default">offline</span>
						</h3>
					</div>
				</div>
				<!-- Video -->
				<div class="row">
					<div class="col-md-12 border-bottom">
						<h4>
							<span class="glyphicon glyphicon-facetime-video"></span> Video
						</h4>
						<div id="canvas-video" class="demo-placeholder chart"></div>
					</div>
				</div>
				<!-- Audio -->
				<div class="row">
					<div class="col-md-12 border-bottom">
						<h4>
							<span class="glyphicon glyphicon-volume-up"></span> Audio
						</h4>
						<div id="canvas-audio" class="demo-placeholder chart"></div>
					</div>
				</div>
			</div>
			<div class="col-md-8">
				<!-- Arousal -->
				<div class="row">
					<div class="col-md-12 border-bottom">
						<h4>
							<span class="glyphicon glyphicon-eye-open"></span> Arousal
						</h4>
						<img id="arousal-image" alt="arousal image" src="<%= getServletContext().getContextPath()+"/images/" %>placeholder.jpg" style="width:95%; height:auto;"/>
					</div>
				</div>
				<!-- Semantika -->
				<div class="row">
					<div class="col-md-12 border-bottom">
						<h4>
							<span class="glyphicon glyphicon-info-sign"></span> Info
						</h4>
						<strong>Arousal level: </strong>
						<span id="arousal-info">
							none
						</span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- /.container -->


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="js/jquery-2.0.3.js"></script>
	<script src="dist/js/bootstrap.min.js"></script>
	<script src="dist/js/paper.js"></script>
	<script src="dist/js/jquery.flot.js"></script>
	<script src="dist/js/common.js"></script>
	<script src="dist/js/patient.js"></script>
</body>
</html>