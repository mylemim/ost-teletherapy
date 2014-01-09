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

	function sendAVmessage() {
		// send the audio-video message wrapped as JSON
		var json = JSON.stringify(new audioVideoWrapper(1, 1));
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
			var chatLog = document.getElementById('chatlogField');
			// chatLog.value += evt.data + "\n";

			chatLog.value += evt.data + "\n";
		}
	}

	function onError(evt) {
		writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
	}

	function writeToScreen(message) {
		var output = document.getElementById("output");
		output.innerHTML += message + "<br>";
	}

	// register click listeners
	$("#joinButton").click(function() {
		join();
	});
	$("#chatButton").click(function() {
		sendAVmessage();
	});
}

/* WRAPPERS */
// the message wrapper for the audiovideosignal
function audioVideoWrapper(video, audio) {
	this.role = "patient";
	this.sessionId = -1;
	this.video = video;
	this.audio = audio;
}
// the message wrapper for physiological signals
function physiologialSignalWrapper(heartBeat, skinConductivity) {
	this.role = "patient";
	this.sessionId = -1;
	this.heartBeat = heartBeat;
	this.skinConductivity = skinConductivity;
}