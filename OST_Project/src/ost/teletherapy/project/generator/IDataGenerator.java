package ost.teletherapy.project.generator;

import java.io.File;
import java.util.ArrayList;

public interface IDataGenerator<E> {
	public E generateSingleData();	
	public ArrayList<E> generateData();
	public void generateDataToJsonFile(File outputFile);
	//identicno kao ovo gore, samo treba path (ne File)
	public void generateDataToJsonFile(String outputFile);
}
