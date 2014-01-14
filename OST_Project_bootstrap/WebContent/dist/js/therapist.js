$(document).ready(function()
{
	// paljenje web socketa
	webSocketInit();

	// paljenje grafova
	initChart('canvas-video', getVideoData);
	initChart('canvas-audio', getAudioData);
	initChart('canvas-heart-rate', getHeartData);
	initChart('canvas-conductivity', getConductivityData);
	
	// rigguje slanje audio_video poruke svake sekunde
	setInterval(function()
	{
		sendAudioVideo(websocket, "therapist", 1, 1);
	}, sendTime);
	
	// rigguje slanje reaction poruke svake sekunde
	setInterval(function()
	{
		sendReaction(websocket, "therapist", 1, 1);
	}, sendTime);
});

$(window).unload(function()
{
	// šalje da napušta session
	websocket.send(makeMessage("therapist", "quit"));
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
		websocket.send(makeMessage("therapist", "join"));
	};

	// callback kada se dobije poruka
	websocket.onmessage = function(evt)
	{
		// dohvaća podatke
		var data = JSON.parse(evt.data);

		// provjerava je li poruka za therapista
		if(data.role == "patient")
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
					websocket.send(makeMessage("therapist", "join_ok"));
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
			// provjerava je li poruka s reakcijom
			else if(data.type == "reaction")
			{
				// logira šta je dobio
				console.log(evt.data);
				
				// postavlja podatke
				setData(data_heart, data.heartBeat);
				setData(data_conductivity, data.skinConductivity);
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


// podaci za grafove
var data_video = [];
var data_audio = [];
var data_heart = [];
var data_conductivity = [];

// funkcije za dohvaćanje raznih podataka
var getVideoData = function() { return getData(data_video); };
var getAudioData = function() { return getData(data_audio); };
var getHeartData = function() { return getData(data_heart); };
var getConductivityData = function() { return getData(data_conductivity); };



/* HELPERS */

// postavljanje statusa pacijenta
function setOthersStatus(status, nodeId)
{
	// provjerava nodeId
	if(nodeId === undefined)
	{
		// ako nije postavljen - postavlja defaultni nodeId
		nodeId = "patient-state";
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

// šalje pobudu
function sendArousal(radioName)
{
	// rješava radio name
	radioName = (radioName === undefined) ? "image_radios" : radioName;
	
	// dohvaća odabrani radio
	var radio = $('input[type="radio"][name="' + radioName + '"]:checked');
	
	// provjerava valja li šta ovo što je dohvatio
	if(radio.length == 0)
	{
		// ako nije ništa dohvatio - vraća se
		return;
	}
	
	// dohvaća link slike i arousal
	var link = $(radio).data('image');
	var arousal = $(radio).attr('value');
	
	// šalje poruku
	websocket.send(makeMessage("therapist", "arousal", {'link':link, 'arousal':arousal}));
}





