<%@page import="java.util.List"%>
<%@page import="ost.teletherapy.project.semantics.Arousal"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ost.teletherapy.project.dal.DataAccessManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.File"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Therapist page</title>

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
				<a class="navbar-brand" href="#">Therapist</a>
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
				<div class="row">
					<!-- Patient -->
					<div class="col-md-12">
						<h3>
							<span class="glyphicon glyphicon-user"></span> Patient: 
							<span id="patient-state" class="label label-default">offline</span>
						</h3>
					</div>
				</div>
				<div class="row">
					<!-- Arrousal -->
					<div class="col-md-12">
						<h3>
							<span class="glyphicon glyphicon-bell"></span> Arousal
						</h3>
							<%
							// dohvaća arousale
							List<Arousal> arousals = DataAccessManager.getAllArousals();
							
							// provjerava je li dohvatio šta
							if(arousals == null)
							{
								out.println("Niti jedna slika nije dohvaćena");
							}
							else
							{
								// gleda je li prvi
								Boolean first = true;
								
								// iterira
								for(Arousal a : arousals)
								{
									// stvara link na sliku
									String link = getServletContext().getContextPath()+"/"+a.getFullPath();
								%>
									<div class="radio">
										<label>
											<input 
												type="radio" 
												name="image_radios" 
												value="<%= a.getFormattedImpulse() %>" 
												data-image="<%= link %>"
												<%  
													if(first == true)
													{
														first = false;
														out.print("checked");
													}
												%>
											> 
											<img style="max-width:64px; max-height:64px;" src="<%= link %>" /> 
											<%= a.getName() %>
									  	</label>
									</div>
								<%
								}
							}
						%>
						<button id="send_erausal_btn" type="button" class="btn btn-primary btn-block" onclick="sendArousal();">Pošalji pobudu</button>
					</div>
				</div>
			</div>
			<div class="col-md-8">
				<div class="row">
					<div class="col-md-12 border-bottom">
						<h3>
							<span class="glyphicon glyphicon-facetime-video"></span> Video
						</h3>
						<div id="canvas-video" class="demo-placeholder chart"></div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12 border-bottom">
						<h3>
							<span class="glyphicon glyphicon-volume-up"></span> Audio
						</h3>
						<div id="canvas-audio" class="demo-placeholder chart"></div>
					</div>
				</div>
			</div>
		</div>

		<div class="row background-darker">
			<div class="col-md-6 border-bottom">
				<h3>
					<span class="glyphicon glyphicon-heart-empty"></span> Heart-rate
				</h3>
				<div id="canvas-heart-rate" class="demo-placeholder chart"></div>
			</div>
			<div class="col-md-6 border-bottom">
				<h3>
					<span class="glyphicon glyphicon-random"></span> Conductivity
				</h3>
				<div id="canvas-conductivity" class="demo-placeholder chart"></div>
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
	<script src="dist/js/therapist.js"></script>
</body>
</html>
