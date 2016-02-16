package frc.team3966.toastrhino.util;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team3966.toastrhino.RobotModule;

//use this class to get preferences and user set values at runtime
public class Values {

	@SuppressWarnings("deprecation")
	public static double getGenericDouble(String path) { //returns the key for "path" var in user set values. Returns -0.0 if nothing exists
		return SmartDashboard.getDouble(path);
	}

	public static double getAngleSlop(SpecificValues c) {
		String path = "";
		switch(c) {
		case SLOP_YAW:
			path = "Yaw Slop";
		case SLOP_POSITIONING:
			path = "Positioning Slop";
		case SLOP_SHOOTER:
			path = "Shooter angle slop";
		}

		return getGenericDouble(path);
	}

}
