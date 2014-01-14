$(document).ready(function()
{
	initVideo('canvas-video');
	initAudio('canvas-audio');
	initHeartBeat('canvas-heart-rate');
	initConductivity('canvas-conductivity');
});



// inicijalizcija videa: j3n
function initVideo(videoId)
{
	// Get a reference to the canvas object s3l
	var canvas = document.getElementById(videoId);

	// Create an empty project and a view for the canvas: t3r
	paper.setup(canvas);

	with(paper)
	{
		var path = new Path.Rectangle({
			point: [$(canvas).width()/2, $(canvas).height()/2],
			size: [75, 75],
			strokeColor: 'black'
		});

		view.draw();

		var adder = 0.1;
		var x = 0;
		var max = 5;

		view.onFrame = function(event) {
			// random pomak
			var rand = Math.random() * 10;

			// racunanje
			x = x+adder;

			if(Math.abs(x) > max)
			{
				adder = adder * -1;
			}

			path.translate(new Point(x, 0));

			// Each frame, rotate the path by 3 degrees:
			path.rotate(3);
		}
	}
}



// podaci
var totalPoints = 300;
var data = [];
//var data_audio = [];
//var data_heart = [];
//var data_conductivity = [];



// inicijalizacija bilo kojeg grafa
function initChart(containerId, fetchData)
{
	var updateInterval = 500;

	var plot = $.plot("#" + containerId, [ fetchData() ], {
		series: {
			shadowSize: 0	// Drawing is faster without shadows
		},
		yaxis: {
			min: 0,
			max: 100
		},
		xaxis: {
			show: false
		}
	});

	function update() {

		plot.setData([fetchData()]);

		// Since the axes don't change, we don't need to call plot.setupGrid()

		plot.draw();
		setTimeout(update, updateInterval);
	}

	update();
}

// funkcija za random dohvaćanje podataka
var getRandomData = function() {

	if (data.length > 0)
		data = data.slice(1);

	// Do a random walk

	while (data.length < totalPoints) {

		var prev = data.length > 0 ? data[data.length - 1] : 50,
			y = prev + Math.random() * 10 - 5;

		if (y < 0) {
			y = 0;
		} else if (y > 100) {
			y = 100;
		}

		data.push(y);
	}

	// Zip the generated y values with the x values

	var res = [];
	for (var i = 0; i < data.length; ++i) {
		res.push([i, data[i]])
	}

	return res;
}



// init audija (gospodara prstenova)
function initAudio(audioId)
{
	initChart(audioId, getRandomData);
}

// init otkucaja srca
function initHeartBeat(heartId)
{
	initChart(heartId, getRandomData);
}

// init vodljivosti
function initConductivity(conductivityId)
{
	initChart(conductivityId, getRandomData);
}