package frc.team3966.toastrhino.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team3966.toastrhino.RobotModule;

/**
 *
 */
public class GRIP extends Subsystem {

  private double lastGoodValueY = 100.0;

  private double lastGoodValueX = 100.0;

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
      lastGoodValueY = centerY;
    } else if (GRIPcenterY.length > 1) {
      for (double i : GRIPcenterY) {
        centerY += i;
      }
      centerY /= (double)GRIPcenterY.length;
      lastGoodValueY = centerY;
    } else {
      return lastGoodValueY;
    }
    return centerY; // actualvalue pls
  }
  
  // Uses centerY to calulate value
  public double getDistanceToGoal() { // Returns in inches
    return 0.5808 * getCenterY() + 32.799;
  }
  
  public double getCenterX() {
	    // Load Grip values from the network table
	    double[] GRIPcenterX = GRIPtable.getNumberArray("centerX", blank);
	    
	    double centerX = 0;
	    if (GRIPcenterX.length == 1) {
	      centerX = GRIPcenterX[0];
        lastGoodValueX = centerX;
	    } else if (GRIPcenterX.length > 1) {
	    	double max = 0;
	      for (double i : GRIPcenterX) {
	    	  if (i > max) max = i; //gets rightmost contour... Todo... Use largest
	      }
	      lastGoodValueX = centerX;
	    } else {
	      return lastGoodValueX;
	    }
	    return centerX; // actualvalue pls
	  }

}
