package frc.team3966.toastrhino.util;
/*
 * all functions that relate to the real world, translating those into basic math functions
 * 
 */

public class AppliedFunctions {
	/* constants */
	final float gravity = 9.81f; // m per s^2
	final float goalHeight = 2.159f; // m

	/* Arm constants */
	final float initialv = 5.0f; // m per s
	final float initialDistance = 5.0f; // m
	final short firstmeasure_px = 250; // in pixels
	final float firstmeasure_length = 10.0f; // meters

	//returns the radians that the "gun" should rotate to. Starts at 0
	public float getShootRadians(float distance) {
		float radical = 0.0f;
		radical += FastArithmetic.pow_f(initialv, (short) 4);
		radical -= gravity * (gravity * FastArithmetic.pow_f(distance, (short) 2)
				+ 2 * goalHeight * FastArithmetic.pow_f(initialv, (short) 2));
		float innerExpression = (FastArithmetic.pow_f(initialv, (short) 2)
				+ /* <- can be plus or minus! */ FastArithmetic.sqrt_f(radical)) / (distance * gravity);
		return (float) Math.atan(innerExpression);
	}

	//gets distance from pixels high. Requires initial measurement. Needs to be tweaked for different camera positions.
	public float getDistance(short pixels) {
		return (firstmeasure_px * firstmeasure_length) / (pixels);
	}

}
