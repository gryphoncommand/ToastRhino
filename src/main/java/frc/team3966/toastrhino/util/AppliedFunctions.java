package frc.team3966.toastrhino.util;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/*
 * all functions that relate to the real world, translating those into basic math functions
 *  a
 */

public class AppliedFunctions {
	/* constants */
	public final static float gravity = 9.81f; // m per s^2
	public final static float goalHeight = 1.905f; // m //Should be 2.159. Our test goal is 1.905 m, change this at competition

	// Get desired arm setpoint for vertical aim
	public static double getPotValueFromCenterY(double centerY) {
	    return .1021 * centerY + 37.8;
        //This was before the shooter broke TWICE
    	//return .1021 * centerY + 31.409;
	}
	
	// gets camera center
	public static double getActualCenter() {
		return 320 / 2;
	}
	
	// gets what centerX should be in line with robot, not camera
	public static double getShooterCenterX() { // Input is in Inches
                return 147;
		//return .169 * distance  + 119.119; //Actually 2 "119" s in  the offset
	}
}
