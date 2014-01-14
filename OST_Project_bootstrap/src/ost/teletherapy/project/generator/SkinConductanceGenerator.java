package ost.teletherapy.project.generator;

import java.util.Random;

public class SkinConductanceGenerator {

	/**
	 * Minimum possible arousal value.
	 */
	private static final int AROUSAL_MIN = 1;

	/**
	 * Maximum possible arousal value.
	 */
	private static final int AROUSAL_MAX = 100;

	/**
	 * Minimum skin conducatance value (microsiemens).
	 */
	private static final int SKIN_C_MIN = 2;

	/**
	 * Maximum skin conducatance value (microsiemens).
	 */
	private static final int SKIN_C_MAX = 50;

	/**
	 * The maximum possible offset from standard value in microsiemens. This is
	 * sine function amplitude.
	 */
	private static final int MAX_SKIN_C_OFFSET_FOR_AROUSAL = 5;

	/**
	 * Retrieve skin conductance value for passed arousal. If arousal is out of
	 * bounds then IllegalArgumentException is thrown. Notice that skin
	 * conductance is returned as integer between SKIN_C_MIN and SKIN_C_MIN
	 * value (2 - 50 microsiemens).
	 * 
	 * @param arousal
	 *            current arrousal.
	 * @return int representing skin conductance in microsiemens.
	 */
	public static int getSkinConductanceForArousal(int arousal) {
		// Check if arousal is in correct bounds
		if (arousal < AROUSAL_MIN || arousal > AROUSAL_MAX) {
			throw new IllegalArgumentException(
					"Arousal must be >= 1 and <= 100");
		}

		// Get angle based on current system time
		long angle = System.currentTimeMillis() % 360;

		// Convert angle to radians and calculate sine (-1 to 1)
		double sineValue = Math.sin(Math.toRadians(angle));

		// Calculate random sine amplitude
		double randAmplitude = new Random().nextDouble()
				* MAX_SKIN_C_OFFSET_FOR_AROUSAL;

		// Calculate final function value that will vary around standard
		double functionValue = randAmplitude * sineValue;

		
		
		// Calcualte skin conductance range
		double skinCRange = SKIN_C_MAX - SKIN_C_MIN;

		// Calculate arousal range
		double arousalRange = AROUSAL_MAX - AROUSAL_MIN;

		// Calculate normalization factor in order to convert arousal range to
		// skin range
		double normalizationFactor = skinCRange / arousalRange;

		// Normalize the value
		double arousalNormaliedValue = arousal * normalizationFactor;

		// Calculate skin conductance standard
		double arrousalStandard = arousalNormaliedValue + SKIN_C_MIN;

		// Create final skin conducatnce value and return it as integer
		double skinCondValue = arrousalStandard + functionValue;

		return (int) skinCondValue;
	}
}
