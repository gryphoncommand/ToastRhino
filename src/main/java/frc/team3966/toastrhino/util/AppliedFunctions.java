package frc.team3966.toastrhino.util;
/*
 * all functions that relate to the real world, translating those into basic math functions
 *  a
 */

public class AppliedFunctions {
	/* constants */
	final static float gravity = 9.81f; // m per s^2
	final static float goalHeight = 1.905f; // m //Should be 2.159. Our test goal is 1.905 m, change this at competition
	final static double maxShooterEncoderInput = 62.5;
	final static double minShooterEncoderInput = 89.5;
	final static double degreesAtEncoderMin = 135.5;
	final static double degreesAtEncoderMax = -17;
	
	
	/* Arm constants */
	final static float initialv = 5.0f; // m per s
	final static float measure_pix_0 = 16; ///center y
	final static float measure_m_0 = 1.9812f;//meters away
	final static float measure_pix_1 = 93; ///center y
	final static float measure_m_1 = 3.7846f;//meters away

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
	public static double getDistance(double centerY) {
		return (centerY - measure_pix_0) * ((measure_m_1 - measure_m_0) / (measure_pix_1 - measure_pix_0)) + measure_m_0;
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
