$(document).ready(function() {
	initGraphs();
	webSocketInit();
});

function initGraphs() {
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
		// the axesDefaults object. Here, we're using a canvas renderer
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
				// Turn off "padding". This will allow data point to lie on the
				// edges of the grid. Default padding is 1.2 and will keep all
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
	 * SCRIPTS FOR RENDERING THE SIGNALS
	 */
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
		// will do an endless loop if random generated
		if (newData.length) {
			// var val = newData.shift();

			// values from 0-10
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
}

function webSocketInit() {
	var wsUri = "ws://" + document.location.host + "/OST_Project/websocket";
	alert(wsUri);
	var websocket = new WebSocket(wsUri);

	websocket.onopen = function(evt) {
		onOpen(evt);

		// join as soon as there is a connection
		join();
	};
	websocket.onmessage = function(evt) {
		onMessage(evt);
	};
	websocket.onerror = function(evt) {
		onError(evt);
	};

	function join() {
		websocket.send("Therapist joined");
	}

	function sendAVMessage() {
		// send the audio-video message wrapped as JSON
		var json = JSON.stringify(new audioVideoWrapper(1, 1));
		websocket.send(json);
	}

	function onOpen() {
		alert("Connected to " + wsUri);
	}

	function onMessage(evt) {

		if (evt.data.indexOf("joined") != -1) {
			var userField = document.getElementById('userField');
			userField.value += evt.data + "\n";
		} else {
			// check the typeif it's not a joined notification

			var parsedData = JSON.parse(evt.data);

			// check the type of the received data
			// AV signal has audio and visual data, while physiology has
			// heartBeat and skinConductivity
			if (parsedData.videoSignal != null
					&& parsedData.audioSignal != null) {
				var avLogField = document.getElementById('avLogField');
				avLogField.value += evt.data + "\n";
			}
			if (parsedData.heartBeat != null
					&& parsedData.skinConductivity != null) {
				var physioLogField = document.getElementById('physioLogField');
				physioLogField.value += evt.data + "\n";
			}

		}
	}

	function onError(evt) {
		writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
	}

	function writeToScreen(message) {
		var output = document.getElementById("output");
		output.innerHTML += message + "<br>";
	}

	setInterval(sendAVMessage, 1000);
}

/* WRAPPERS */
// the message wrapper for the audiovideosignal
function audioVideoWrapper(videoSignal, audioSignal) {
	this.role = "therapist";
	this.sessionId = -1;
	this.videoSignal = videoSignal;
	this.audioSignal = audioSignal;
}
// the message wrapper for physiological signals
function physiologialSignalWrapper(heartBeat, skinConductivity) {
	this.role = "patient";
	this.sessionId = -1;
	this.heartBeat = heartBeat;
	this.skinConductivity = skinConductivity;
}
