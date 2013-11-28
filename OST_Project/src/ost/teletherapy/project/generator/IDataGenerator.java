package ost.teletherapy.project.generator;

import java.io.File;
import java.util.ArrayList;

public interface IDataGenerator<E> {
	public ArrayList<E> generateData();
	public void generateDataToJsonFile(ArrayList<E> data, File outputFile);
}
