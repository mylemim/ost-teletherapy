package ost.teletherapy.project.generator;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class AudioGenerator implements IDataGenerator<Float> {


	@Override
	public Float generateSingleData() {
		// TODO Auto-generated method stub
		
		Random generator = new Random();
		
		float number = (float) (Math.round((generator.nextFloat() * 100) * 100.0) / 100.0);

		return number;
	}

	@Override
	public ArrayList<Float> generateData() {
		// TODO Auto-generated method stub
		
		ArrayList<Float> listNumbers = new ArrayList<Float>();
		float number;
		
		for (int i = 0; i < 101; i++) {

			number =  generateSingleData();
			listNumbers.add(number);
		}

		return listNumbers;
	}

	@Override
	public void generateDataToJsonFile(File outputFile) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void generateDataToJsonFile(String outputFile) {
		// TODO Auto-generated method stub
		
	}
}
