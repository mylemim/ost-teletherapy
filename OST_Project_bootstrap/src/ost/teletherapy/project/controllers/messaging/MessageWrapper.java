package ost.teletherapy.project.controllers.messaging;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown=true)
public class MessageWrapper
{
	protected String role;
	protected String type;
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public static MessageWrapper GetMessage(String data)
	{
		// object mapper
		ObjectMapper mapper = new ObjectMapper();
		
		// poruka
		MessageWrapper message = new MessageWrapper();
		
		// pokušava dekodirati objekt
		try
		{
			// postavlja poruku
			message = mapper.readValue(data, MessageWrapper.class);
		}
		catch(Exception e)
		{
			// ako je šta pošlo naopako - vraća null
			return null;
		}

		// vraća poruku
		return message;
	}
}
