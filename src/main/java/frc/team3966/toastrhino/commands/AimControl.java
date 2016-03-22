package frc.team3966.toastrhino.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team3966.toastrhino.RobotModule;
import frc.team3966.toastrhino.util.AppliedFunctions;

/**
 *
 */
public class AimControl extends Command {

  NetworkTable GRIPtable;

  public AimControl() {
    requires(RobotModule.drive);
	GRIPtable = NetworkTable.getTable("GRIP/goal");
    requires(RobotModule.armAim);
  }

  // Called just before this Command runs the first time
  protected void initialize() {
    //RobotModule.armAim.enablePID(true);
  }

  // Called repeatedly when this Command is scheduled to run
  protected void execute() {
	  double[] GRIPcenterX = GRIPtable.getNumberArray("centerX");
	  double[] GRIPcenterY = GRIPtable.getNumberArray("centerY");
	  double[] GRIPheight = GRIPtable.getNumberArray("height");
	    double centerX;
	    double centerY;
	    double height;
	    if (GRIPcenterX.length > 0) {
	      centerX = GRIPcenterX[0];
	    } else {
	      centerX = 0;
	    }
	    if (GRIPcenterY.length > 0) {
		  centerY = GRIPcenterY[0];
		} else {
		  centerY = 0;
		}
	    if (GRIPheight.length > 0) {
			  height = GRIPheight[0];
			} else {
			  height = 0;
			}
	    double distanceFromGoal = AppliedFunctions.getDistance(centerY); //m
	    SmartDashboard.putNumber("Distance from goal", distanceFromGoal);
	   
	    
	    
	    
  }

  // Make this return true when this Command no longer needs to run execute()
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  protected void interrupted() {
  }
}