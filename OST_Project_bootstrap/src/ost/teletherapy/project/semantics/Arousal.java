package ost.teletherapy.project.semantics;


public class Arousal
{
	public static final String extension = ".jpg";
	
	private String path;
	private String name;
	private Impulse impulse;
	
	public Arousal(String path, String name, int impulseValue, String impulseDescription)
	{
		this.path = path;
		this.name = name;
		this.impulse = new Impulse(impulseValue, impulseDescription);
	}
	
	public String getFullPath()
	{
		return this.path+"/"+name+extension;
	}
	
	public String getFormattedImpulse()
	{
		return this.impulse.getFormattedImpulse();
	}
	
	
	
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Impulse getImpulse() {
		return impulse;
	}
	
	public void setImpulse(Impulse impulse) {
		this.impulse = impulse;
	}
}
