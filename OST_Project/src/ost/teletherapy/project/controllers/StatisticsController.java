package ost.teletherapy.project.controllers;

import java.io.IOException;

import javax.websocket.EncodeException;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import ost.teletherapy.project.controllers.messaging.AVSignalWrapper;
import ost.teletherapy.project.generator.AudioGenerator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ServerEndpoint("/websocket")
public class StatisticsController {

	@OnMessage
	public void message(String message, Session client) throws IOException,
			EncodeException {

		// if it's an audiovideo message
		if (!message.contains("joined")) {
			ObjectMapper mapper = new ObjectMapper();
			AudioGenerator ag = new AudioGenerator();
			AVSignalWrapper wrapper = new AVSignalWrapper();

			// deserialize the object
			try {
				wrapper = mapper.readValue(message, AVSignalWrapper.class);
			} catch (Exception e) {
				e.printStackTrace();
			}

			// generate the values and store them
			wrapper.setAudioSignal(ag.generateSingleData());
			wrapper.setVideoSignal(ag.generateSingleData());

			// serialize back into JSON
			try {
				message = mapper.writeValueAsString(wrapper);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}

		System.out.println(message);

		// Sent to other peers
		for (Session peer : client.getOpenSessions()) {
			peer.getBasicRemote().sendObject(message);
		}
	}

}