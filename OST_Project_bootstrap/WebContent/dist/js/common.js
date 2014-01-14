//postavljanje statusa ovog drugog
function setOthersStatus(status, nodeId)
{
	// provjerava nodeId
	if(nodeId === undefined)
	{
		// ako nije postavljen - postavlja defaultni nodeId
		nodeId = "therapist-state";
	}

	// dohvaća span koji pokazuje stanje
	var node = $('#' + nodeId);

	// provjerava je li fino dohatio
	if(node.length == 0)
	{
		// ako je nešto pošlo naopako - vraća se
		return;
	}

	// ako je status online
	if(status == "online")
	{
		// postavlja zelenu boju na span
		$(node).removeClass('label-default');
		$(node).addClass('label-success');
	}
	// ako je status offline
	else if(status == "offline")
	{
		// postavlja sivu boju na span
		$(node).removeClass('label-success');
		$(node).addClass('label-default');
	}

	// postavlja content na željeni status
	$(node).html(status);
}



//brzina slanja
var sendTime = 1000;

//inicijalizacija bilo kojeg grafa
function initChart(containerId, fetchData)
{
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

		plot.setupGrid();
		
		plot.draw();
		setTimeout(update, sendTime);
	}

	update();
}

//funkcija za random dohvaćanje podataka
function setData(data, value) {

	if (data.length > totalPoints)
		data = data.slice(1);

	data.push([data.length, value]);
};

//funkcija za vraćanje
function getData(data)
{
	var res = [];
	for (var i = 0; i < data.length; ++i) {
		res.push([i, data[i]]);
	}

	return res;
}



// pravi poruku
function makeMessage(role, type, data)
{
	// rješava data
	data = (data === undefined) ? {} : data;
	
	// stvara poruku sa headerom
	var message = {
		'role':role,
		'type':type,
		'sessionId':-1
	};
	
	// kombinira data, role i type
	$.extend(message, data);
	
	// vraća JSON poruku
	return JSON.stringify(message);
}

// šalje audio
function sendAudioVideo(socket, role, audioValue, videoValue)
{
	// pokušava poslati audio_video poruku
	try
	{
		// šalje poruku
		socket.send(makeMessage(role, "audio_video", {'audioValue':audioValue, 'videoValue':videoValue}));
	}
	catch(e)
	{
		// nešto nije uspio - logira šta je bilo
		console.log(e);
	}
}

// šalje reakciju
function sendReaction(socket, role, heartBeat, skinConductivity)
{
	// pokušava poslati audio_video poruku
	try
	{
		// šalje poruku
		socket.send(makeMessage(role, "reaction", {'heartBeat':heartBeat, 'skinConductivity':skinConductivity}));
	}
	catch(e)
	{
		// nešto nije uspio - logira šta je bilo
		console.log(e);
	}
}