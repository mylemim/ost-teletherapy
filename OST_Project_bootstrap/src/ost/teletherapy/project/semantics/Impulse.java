package ost.teletherapy.project.semantics;

public class Impulse
{
	private int value;
	private String description;
	
	public Impulse(int value, String description)
	{
		this.setValue(value);
		this.setDescription(description);
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getFormattedImpulse()
	{
		return this.value+", "+this.description;
	}
}
