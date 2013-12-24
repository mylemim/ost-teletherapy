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

<script>
	$(document).ready(function() {
		var plot1 = $.jqplot('chart1', [ new Array(1) ], {
			title : 'Heart signals',
			series : [ {
				yaxis : 'y2axis',
				label : '',
				showMarker : false,
				fill : false,
				neighborThreshold : 3,
				lineWidth : 2.2,
				color : '#0571B6',
				fillAndStroke : true
			} ],
			axes : {
				xaxis : {
					renderer : $.jqplot.DateAxisRenderer,
					tickOptions : {
						formatString : '%H:%M:%S'
					},
					numberTicks : 10
				},
				y2axis : {
					min : 40,
					max : 180,
					tickOptions : {
						formatString : '%.2f'
					},
					numberTicks : 15
				}
			},
			cursor : {
				zoom : false,
				showTooltip : false,
				show : false
			},
			highlighter : {
				useAxesFormatters : false,
				showMarker : false,
				show : false
			},
			grid : {
				gridLineColor : '#333333',
				background : 'transparent',
				borderWidth : 3
			}
		});

		var myData = [];
		var x = (new Date()).getTime() - 101000;
		var y;
		var i;
		for (i = 0; i < 100; i++) {
			x += 1000;
			y = Math.floor(Math.random() * 100);
			myData.push([ x, y ]);
		}

		plot1.series[0].data = myData;
		plot1.resetAxesScale();
		plot1.axes.xaxis.numberTicks = 10;
		plot1.axes.y2axis.numberTicks = 15;
		plot1.replot();

		function updateSeries() {
			myData.splice(0, 1);
			x = (new Date()).getTime();
			y = Math.floor(Math.random() * 100);
			myData.push([ x, y ]);

			plot1.series[0].data = myData;
			plot1.resetAxesScale();
			plot1.axes.xaxis.numberTicks = 10;
			plot1.axes.y2axis.numberTicks = 15;
			plot1.replot();
		}

		window.setInterval(updateSeries, 1000);

		var storedData = [ 0 ];

		var plot2 = $.jqplot('chart2', [ storedData ], {
			// Give the plot a title.
			title : 'Video signals chart',
			// You can specify options for all axes on the plot at once with
			// the axesDefaults object.  Here, we're using a canvas renderer
			// to draw the axis label which allows rotated text.
			axesDefaults : {
				labelRenderer : $.jqplot.CanvasAxisLabelRenderer
			},
			// An axes object holds options for all axes.
			// Allowable axes are xaxis, x2axis, yaxis, y2axis, y3axis, ...
			// Up to 9 y axes are supported.
			axes : {
				// options for each axis are specified in seperate option objects.
				xaxis : {
					label : "X Axis",
					// Turn off "padding".  This will allow data point to lie on the
					// edges of the grid.  Default padding is 1.2 and will keep all
					// points inside the bounds of the grid.
					pad : 0
				},
				yaxis : {
					label : ""
				}
			}
		});

		var plot3 = $.jqplot('chart3', [ storedData ], {

			title : 'Audio signals chart',

			axesDefaults : {
				labelRenderer : $.jqplot.CanvasAxisLabelRenderer
			},

			axes : {

				xaxis : {
					label : "X Axis",

					pad : 0
				},
				yaxis : {
					label : ""
				}
			}
		});
		
		/**
		SCRIPTS FOR RENDERING THE SIGNALS
		**/
		function renderVideoSignal() {
			if (plot2) {
				plot2.destroy();
			}
			plot2 = $.jqplot('chart2', [ storedData ]);
		}
		
		function renderAudioSignal() {
			if (plot3) {
				plot3.destroy();
			}
			plot3 = $.jqplot('chart3', [ storedData ]);
		}

		var newData = [ 1, 2, 3, 4, 4, 5, 6, 7, 3, 2, 5, 6, 6, 2, 3, 4, 5 ];

		function doUpdate() {
			//will do an endless loop if random generated
			if (newData.length) {
				//var val = newData.shift();

				//values from 0-10
				var val = Math.floor(Math.random() * 11);
				
				storedData.push(val);
				
				if (storedData.length > 30)
					storedData.shift();
				
				renderVideoSignal();
				renderAudioSignal();
				
				setTimeout(doUpdate, 150);
			}
		}

		doUpdate();
	});
</script>
<style type="text/css">
#plot_wrapper {
	position: absolute;
	top: 38%;
	left: 0%;
	padding-top: 5%;
	padding-bottom: 5%;
	border-top: 5px solid #5983FF;
	border-bottom: 5px solid #5983FF;
	width: 100%;
	padding-left: 25%;
}

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

#first_h {
	position: absolute;
	top: 26%;
	color: #5983FF;
	font-family: Arial;
	font-size: 40px;
	text-shadow: 1px 1px #8585AD;
}

#second_h {
	position: absolute;
	top: 144%;
	color: #5983FF;
	font-family: Arial;
	font-size: 40px;
	text-shadow: 1px 1px #8585AD;
}

#plot_wrapper2 {
	position: absolute;
	top: 155%;
	left: 0%;
	padding-top: 5%;
	padding-bottom: 5%;
	border-top: 5px solid #5983FF;
	border-bottom: 5px solid #5983FF;
	width: 100%;
	padding-left: 25%;
}

#third_h {
	position: absolute;
	top: 262%;
	color: #5983FF;
	font-family: Arial;
	font-size: 40px;
	text-shadow: 1px 1px #8585AD;
}

#plot_wrapper3 {
	position: absolute;
	top: 273%;
	left: 0%;
	padding-top: 5%;
	padding-bottom: 5%;
	border-top: 5px solid #5983FF;
	border-bottom: 5px solid #5983FF;
	width: 100%;
	padding-left: 25%;
}
</style>

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