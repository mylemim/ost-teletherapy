package ost.teletherapy.project.generator;

import java.util.Random;

public class HearBeatGenerator {

	/**
	 * Minimum possible arousal value.
	 */
	private static final int AROUSAL_MIN = 1;

	/**
	 * Maximum possible arousal value.
	 */
	private static final int AROUSAL_MAX = 100;

	/**
	 * Minimum possible hearbeat range.
	 */
	private static final int MIN_HEARTBEAT_RANGE = 40;

	/**
	 * Maximum possible hearbeat range.
	 */
	private static final int MAX_HEARTBEAT_RANGE = 180;

	/**
	 * Maximum possible offset from standard value.
	 */
	private static final int MAX_HEARTBEAT_OFFSET_FOR_AROUSAL = 5;

	public static int getHeartBeatRateForArousal(int arousal) {
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
				* MAX_HEARTBEAT_OFFSET_FOR_AROUSAL;

		// Calculate final function value that will vary around standard
		double functionValue = randAmplitude * sineValue;

		// Calcualte hearbeat range
		double hearBeatRange = MAX_HEARTBEAT_RANGE - MIN_HEARTBEAT_RANGE;

		// Calculate arousal range
		double arousalRange = AROUSAL_MAX - AROUSAL_MIN;

		// Calculate normalization factor in order to convert arousal range to
		// hearbeat range
		double normalizationFactor = hearBeatRange / arousalRange;

		// Normalize the value
		double arousalNormaliedValue = arousal * normalizationFactor;

		// Calculate skin conductance standard
		double arrousalStandard = arousalNormaliedValue + MIN_HEARTBEAT_RANGE;

		// Create final heartbeat value and return it as integer
		double hearbeatValue = arrousalStandard + functionValue;

		return (int) hearbeatValue;
	}

}
