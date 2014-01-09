package ost.teletherapy.project.controllers.messaging;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ost.teletherapy.project.user.UserType;

public class AVSignalWrapper {

	private String role;
	private int sessionId;
	private float videoSignal;
	private float audioSignal;

	public float getVideoSignal() {
		return videoSignal;
	}

	public void setVideoSignal(float videoSignal) {
		this.videoSignal = videoSignal;
	}

	public float getAudioSignal() {
		return audioSignal;
	}

	public void setAudioSignal(float audioSignal) {
		this.audioSignal = audioSignal;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getSessionId() {
		return sessionId;
	}

	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}

	@JsonIgnore
	public UserType getRoleUserType() {
		if (role.equals("therapist"))
			return UserType.Therapist;
		else if (role.equals("patient"))
			return UserType.Patient;
		else
			return UserType.NoType;
	}

}
