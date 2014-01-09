package ost.teletherapy.project.controllers;

import java.io.IOException;

import javax.websocket.EncodeException;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import ost.teletherapy.project.controllers.messaging.AVSignalWrapper;
import ost.teletherapy.project.controllers.messaging.SensorSignalWrapper;
import ost.teletherapy.project.generator.AudioGenerator;
import ost.teletherapy.project.generator.HearBeatGenerator;
import ost.teletherapy.project.generator.SkinConductanceGenerator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ServerEndpoint("/websocket")
public class StatisticsController {

	@OnMessage
	public void message(String message, Session client) throws IOException,
			EncodeException {

		// if it's not a join message, it's meant for the peers
		if (!message.contains("joined")) {
			// if it has a video and audio signal, then it's an AV message
			if (message.contains("videoSignal")
					&& message.contains("audioSignal"))
				message = generateAVMessage(message);
			// else it's a physio message, with a heart beat and skin
			// conductivity measurement
			else if (message.contains("heartBeat")
					&& message.contains("skinConductivity"))
				message = generatePhysioMessage(message);
		}

		System.out.println(message);

		// Sent to other peers
		for (Session peer : client.getOpenSessions()) {
			peer.getBasicRemote().sendObject(message);
		}
	}

	private String generateAVMessage(String message) {
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
			return mapper.writeValueAsString(wrapper);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return message;
	}

	private String generatePhysioMessage(String message) {
		ObjectMapper mapper = new ObjectMapper();

		SensorSignalWrapper wrapper = new SensorSignalWrapper();

		// deserialize the object
		try {
			wrapper = mapper.readValue(message, SensorSignalWrapper.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		int arousal = 30;

		// generate the values and store them
		wrapper.setHeartBeat(HearBeatGenerator
				.getHeartBeatRateForArousal(arousal));
		wrapper.setSkinConductivity(SkinConductanceGenerator
				.getSkinConductanceForArousal(arousal));

		// serialize back into JSON
		try {
			return mapper.writeValueAsString(wrapper);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return message;
	}

}