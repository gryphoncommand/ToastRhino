package frc.team3966.toastrhino.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team3966.toastrhino.RobotModule;

/**
 *
 */
public class GRIP extends Subsystem {

  private double lastGoodValue = 100.0;

  // What is GRIP Table?
  // Init table:
  NetworkTable GRIPtable;
  
  private double[] blank = new double[0];

  public GRIP() {
    GRIPtable = NetworkTable.getTable("GRIP/goal");
  }

  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    //setDefaultCommand(new MySpecialCommand());
  }

  public double getCenterY() {
    // Load Grip values from the network table
    double[] GRIPcenterY = GRIPtable.getNumberArray("centerY", blank);
    
    double centerY = 0;
    if (GRIPcenterY.length == 1) {
      centerY = GRIPcenterY[0];
    } else if (GRIPcenterY.length > 1) {
      for (double i : GRIPcenterY) {
        centerY += i;
      }
      centerY /= (double)GRIPcenterY.length;
      lastGoodValue = centerY;
    } else {
      return lastGoodValue;
    }
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
    return centerY; // actualvalue pls
  }

}
