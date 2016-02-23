package frc.team3966.toastrhino.util;
/*
 * all functions that relate to the real world, translating those into basic math functions
 *  a
 */

public class AppliedFunctions {
	/* constants */
	final static float gravity = 9.81f; // m per s^2
	final static float goalHeight = 2.159f; // m
	final static double maxShooterEncoderInput = 500;
	final static double minShooterEncoderInput = 0;
	final static double degreesAtEncoderMin = -22;
	final static double degreesAtEncoderMax = 145;
	/* Arm constants */
	final static float initialv = 5.0f; // m per s
	final static float initialDistance = 5.0f; // m
	final static short firstmeasure_px = 250; // in pixels
	final static float firstmeasure_length = 10.0f; // meters

	//returns the radians that the "gun" should rotate to. Starts at 0
	public static float getShootRadians(float distance) {
		float radical = 0.0f;
		radical += FastArithmetic.pow_f(initialv, (short) 4);
		radical -= gravity * (gravity * FastArithmetic.pow_f(distance, (short) 2)
				+ 2 * goalHeight * FastArithmetic.pow_f(initialv, (short) 2));
		float innerExpression = (FastArithmetic.pow_f(initialv, (short) 2)
				+ /* <- can be plus or minus! */ FastArithmetic.sqrt_f(radical)) / (distance * gravity);
		return (float) Math.atan(innerExpression);
	}

	//gets distance from pixels high. Requires initial measurement. Needs to be tweaked for different camera positions.
	public static float getDistance(short pixels) {
		return (firstmeasure_px * firstmeasure_length) / (pixels);
	}
	
	public static double getShootingAngle(double motorInput) { //returns shooter's pitch
		if (motorInput < minShooterEncoderInput) { //capping lower values
			return getShootingAngle(minShooterEncoderInput);
		}
		if (motorInput > maxShooterEncoderInput) { //capping larger values
			return getShootingAngle(maxShooterEncoderInput);
		}
		return motorInput * (degreesAtEncoderMax - degreesAtEncoderMin) / (maxShooterEncoderInput - minShooterEncoderInput) + degreesAtEncoderMax;
	}

}
