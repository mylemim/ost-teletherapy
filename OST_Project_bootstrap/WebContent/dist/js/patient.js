$(document).ready(function()
{
	// paljenje web socketa
	webSocketInit();

	// paljenje grafova
	initChart('canvas-video', getVideoData);
	initChart('canvas-audio', getAudioData);
	
	// rigguje slanje audio_video poruke svake sekunde
	setInterval(function()
	{
		sendAudioVideo(websocket, "patient", 1, 1);
	}, sendTime);
	
	// rigguje slanje reaction poruke svake sekunde
	setInterval(function()
	{
		sendReaction(websocket, "patient", 1, 1);
	}, sendTime);
});

$(window).unload(function()
{
	// šalje da napušta session
	websocket.send(makeMessage("patient", "quit"));
});

/* WEB SOCKET */
// websocket
var websocket;

// funkcija za init web socketa
function webSocketInit()
{
	// uri za web socket
	var webSocketURI = "ws://" + document.location.host + "/OST_Project/websocket";

	// logiranje urija
	console.log(webSocketURI);

	// postavljanje web socketa
	websocket = new WebSocket(webSocketURI);

	// callback kada se otvori web socket
	websocket.onopen = function(evt)
	{
		// šalje obavijest o joinu
		websocket.send(makeMessage("patient", "join"));
	};

	// callback kada se dobije poruka
	websocket.onmessage = function(evt)
	{
		// dohvaća podatke
		var data = JSON.parse(evt.data);

		// provjerava je li poruka za therapista
		if(data.role == "therapist")
		{
			// provjerava je li join poruka
			if(data.type == "join" || data.type == "join_ok")
			{
				// logira tko se prijavio
				console.log(data.role + " joined");
	
				// postavlja status pacijenta na online
				setOthersStatus("online");
				
				// ako je samo join poruka
				if(data.type == "join")
				{
					// vraća nazad join_ok
					websocket.send(makeMessage("patient", "join_ok"));
				}
			}
			// provjerava je li quit poruka
			else if(data.type == "quit")
			{
				// logira tko se prijavio
				console.log(data.role + " exited");
	
				// postavlja status pacijenta na online
				setOthersStatus("offline");
			}
			// provjerava je li av poruka
			else if(data.type == "audio_video")
			{
				// logira šta je dobio
				console.log(evt.data);
	
				// postavlja podatke
				setData(data_video, data.videoSignal);
				setData(data_audio, data.audioSignal);
			}
			// provjerava je li poruka s pobudom
			else if(data.type == "arousal")
			{
				// logira šta je dobio
				console.log(evt.data);
				
				// mijenja sliku i arousal
				changeArousal(data.link, data.arousal);
			}
		}
	};

	// callback kada se dogodi error
	websocket.onerror = function(evt)
	{
		// postavlja status pacijenta na offline
		setOthersStatus("offline");

		// dojavljuje da se desio error
		alert("Error: " + evt.data);
	};
}



//podaci
var totalPoints = 50;

var data_video = [];
var data_audio = [];

// funkcije za dohvaćanje raznih podataka
var getVideoData = function() { return data_video; };
var getAudioData = function() { return data_audio; };



/* HELPERS */
// mijenja sliku i info od arousala
function changeArousal(imageLink, arousal, imageId, arousalId)
{
	// rješava imageId i arousalId
	imageId = (imageId === undefined) ? 'arousal-image' : imageId;
	arousalId = (arousalId === undefined) ? 'arousal-info' : arousalId;
	
	// dohvaća sliku i arousal info
	var image = $('#'+imageId);
	var info = $('#'+arousalId);
	
	// provjerava je li šta dohvaio
	if(image.length == 0 || info.length == 0)
	{
		// ako nešto nije dohvatio - vraća se
		return;
	}
	
	// postavlja sliku
	$(image).attr('src', imageLink);
	
	// postavlja arousal
	$(info).html(arousal);
}




