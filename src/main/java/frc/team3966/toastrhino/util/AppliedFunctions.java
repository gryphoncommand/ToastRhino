package frc.team3966.toastrhino.util;
/*
 * all functions that relate to the real world, translating those into basic math functions
 *  a
 */

public class AppliedFunctions {
	/* constants */
	public final static float gravity = 9.81f; // m per s^2
	public final static float goalHeight = 1.905f; // m //Should be 2.159. Our test goal is 1.905 m, change this at competition
	public final static double maxShooterEncoderInput = 80;
	public final static double minShooterEncoderInput = 0;
	public final static double degreesAtEncoderMin = 135.5;
	public final static double degreesAtEncoderMax = -17;
	
	
	/* Arm constants */
	public final static float initialv = 5.0f; // m per s
	public final static float measure_pix_0 = 16; ///center y
	public final static float measure_m_0 = 1.9812f;//meters away
	public final static float measure_pix_1 = 93; ///center y
	public final static float measure_m_1 = 3.7846f;//meters away
	
	
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
		return (motorInput - minShooterEncoderInput) * (degreesAtEncoderMax - degreesAtEncoderMin) / (maxShooterEncoderInput - minShooterEncoderInput) + degreesAtEncoderMin;
	}
	
	public static double getEncoderFromDegrees(double degrees) { //returns encoder for a degree specifies
		if (degrees < degreesAtEncoderMin) { //capping lower values
			return getEncoderFromDegrees(degreesAtEncoderMin);
		}
		if (degrees > degreesAtEncoderMax) { //capping larger values
			return getEncoderFromDegrees(degreesAtEncoderMax);
		}
		return (degrees - degreesAtEncoderMin) * (maxShooterEncoderInput - minShooterEncoderInput) / (degreesAtEncoderMax - degreesAtEncoderMin) + minShooterEncoderInput;
	}

}
