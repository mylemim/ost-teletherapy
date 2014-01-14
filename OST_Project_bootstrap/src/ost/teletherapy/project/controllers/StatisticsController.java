package ost.teletherapy.project.controllers;

import java.io.IOException;

import javax.websocket.EncodeException;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import ost.teletherapy.project.controllers.messaging.AVSignalWrapper;
import ost.teletherapy.project.controllers.messaging.MessageWrapper;
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

		// deserijalizira poruku
		MessageWrapper messageObject = MessageWrapper.GetMessage(message);
		
		// provjerava je li dohvaćena poruka
		if(messageObject == null)
		{
			// ako je nešto pošlo naopako - izlazi
			return;
		}
		
		// dohvaća tip
		String type = messageObject.getType();
		
		// provjerava je li audio_video
		if(type.equalsIgnoreCase("audio_video"))
		{
			// ako jeste generira AV poruku
			message = generateAVMessage(message);
		}
		// provjerava je li reakcija na pobudu
		else if(type.equalsIgnoreCase("reaction"))
		{
			// ako je reakcija - generira reakcijsku poruku
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