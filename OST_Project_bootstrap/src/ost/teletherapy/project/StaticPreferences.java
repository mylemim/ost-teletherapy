package ost.teletherapy.project;

public class StaticPreferences {

	private static String defaultMultiMediaPath="C:\\OST_teletherapy";
	private static String defaultVideoPath="\\Video";
	private static String defaultImagePath="\\Image";
	private static String defaultSoundPath="\\Sound";
	
	public static String getDefaultMultiMediaPath() {
		return defaultMultiMediaPath;
	}
	
	public static String getDefaultVideoPath() {
		return  defaultMultiMediaPath+defaultVideoPath;
	}

	public static String getDefaultImagePath() {
		return defaultMultiMediaPath+defaultImagePath;
	}

	public static String getDefaultSoundPath() {
		return defaultMultiMediaPath+defaultSoundPath;
	}
}

