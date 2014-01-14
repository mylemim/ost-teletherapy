package ost.teletherapy.project.controllers.messaging;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ost.teletherapy.project.user.UserType;

public class SensorSignalWrapper extends MessageWrapper
{
	private int sessionId;
	private float skinConductivity;
	private float heartBeat;

	public int getSessionId() {
		return sessionId;
	}

	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}

	public float getSkinConductivity() {
		return skinConductivity;
	}

	public void setSkinConductivity(float skinConductivity) {
		this.skinConductivity = skinConductivity;
	}

	public float getHeartBeat() {
		return heartBeat;
	}

	public void setHeartBeat(float heartBeat) {
		this.heartBeat = heartBeat;
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
