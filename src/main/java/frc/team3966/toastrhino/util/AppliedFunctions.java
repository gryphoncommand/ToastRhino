package frc.team3966.toastrhino.util;
/*
 * all functions that relate to the real world, translating those into basic math functions
 *  a
 */

public class AppliedFunctions {
	/* constants */
	public final static float gravity = 9.81f; // m per s^2
	public final static float goalHeight = 1.905f; // m //Should be 2.159. Our test goal is 1.905 m, change this at competition

	
	//gets distance from pixels high. Requires initial measurement. Needs to be tweaked for different camera positions.
	public static double getDistance(double centerY) {
		return .831 * centerY - 7.931;
	}
	
	// Get desired arm setpoint for vertical aim
	public static double getPotValueFromCenterY(double centerY) {
		return .106 * centerY + 23.73;
	}
	
	// gets camera center
	public static double getActualCenter(double distance) {
		return 320 / 2;
	}
	
	// gets what centerX should be in line with robot, not camera
	public static double getShooterCenterXUsingDistance(double distance) { // Input is in Inches
		return .169 * distance  + 119.119; //Actually 2 "119" s in  the offset
	}
}
