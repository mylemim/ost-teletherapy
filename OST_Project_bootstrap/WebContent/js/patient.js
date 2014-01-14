$(document).ready(function() {
	webSocketInit();
});

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
		websocket.send("Patient joined");
	}

	function sendAVMessage() {
		// send the audio-video message wrapped as JSON
		var json = JSON.stringify(new audioVideoWrapper(1, 1));
		websocket.send(json);
	}
	
	function sendPhysioMessage()
	{
		// send the phsysio message wrapped as JSON
		var json = JSON.stringify(new physiologialSignalWrapper(1, 1));
		websocket.send(json);
	}

	function onOpen() {
		alert("Connected to " + wsUri);
	}

	function onMessage(evt) {

		if (evt.data.indexOf("joined") != -1) {
			var chatLog = document.getElementById('userField');
			chatLog.value += evt.data + "\n";
		} else {
			// check the typeif it's not a joined notification

			var parsedData = JSON.parse(evt.data);

			// check the type of the received data
			if (parsedData.videoSignal!=null && parsedData.audioSignal!=null) {
				var avLogField = document.getElementById('avLogField');
				avLogField.value += evt.data + "\n";
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
	setInterval(sendPhysioMessage, 1000);
}

/* WRAPPERS */
// the message wrapper for the audiovideo signal
function audioVideoWrapper(videoSignal, audioSignal) {
	this.role = "patient";
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