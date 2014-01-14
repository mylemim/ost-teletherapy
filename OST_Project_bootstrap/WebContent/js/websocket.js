$(document).ready(function() {
	webSocketInit();
});

function webSocketInit() {
	var wsUri = "ws://" + document.location.host + "/OST_Project/websocket";
	alert(wsUri);
	var websocket = new WebSocket(wsUri);
	var username;

	websocket.onopen = function(evt) {
		onOpen(evt);
	};
	websocket.onmessage = function(evt) {
		onMessage(evt);
	};
	websocket.onerror = function(evt) {
		onError(evt);
	};
	var output = document.getElementById("output");

	function join() {
		username = textField.value;
		websocket.send(username + " joined");
	}

	function send_message() {
		websocket.send(username + ": " + textField.value);
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
			chatLog.value += evt.data + "\n";
		}
	}

	function onError(evt) {
		writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
	}

	function writeToScreen(message) {
		output.innerHTML += message + "<br>";
	}
}