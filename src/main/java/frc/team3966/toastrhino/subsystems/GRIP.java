package frc.team3966.toastrhino.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team3966.toastrhino.RobotModule;

/**
 *
 */
public class GRIP extends Subsystem {

  // What is GRIP Table?
  // Init table:
  

  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    //setDefaultCommand(new MySpecialCommand());
  }
  
  public double getCenterY() {
    // Load Grip values from the network table
    /*
    if (one value in array) {
      return thatvalue;
    } else if (zero values in array) {
      // Did not find goal, use last good value
      if (lastGoodValue != null) {
        return lastGoodValue;
      } else {
        // Return a reasonable middle-ground number (needs testing)
        return 100.0;
      }
    } else if (multiple values in array) {
      return average(values);
    }
    
     */
    return 0.0; // actualvalue pls
  }

}
