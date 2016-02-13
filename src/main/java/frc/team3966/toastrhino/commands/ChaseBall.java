package frc.team3966.toastrhino.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team3966.toastrhino.RobotModule;
//import jaci.openrio.module.grip;
import jaci.openrio.toast.core.loader.annotation.Branch;

/**
 *
 */
@Branch(branch = "frc.team3966.toastrhino.commands.ChaseBall", dependency = "GRIP-Wrapper", method = "some_other_module")
public class ChaseBall extends Command {

  NetworkTable GRIPtable;
  double[] defaultArray = new double[]{
      -1.0 // Normally impossible.
  };
  double centerX;
  double[] GRIPcenterX;
  double leftgreed, rightgreed;

  public ChaseBall() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(RobotModule.drive);
    GRIPtable = NetworkTable.getTable("GRIP/ballContours");
  }

  // Called just before this Command runs the first time
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  protected void execute() {
    GRIPcenterX = GRIPtable.getNumberArray("centerX", defaultArray);
    if (GRIPcenterX.length > 0) {
      centerX = GRIPcenterX[0];
    } else {
      centerX = defaultArray[0];
    }
    if (centerX == -1.0) {
      SmartDashboard.putBoolean("Found Ball", false);
      RobotModule.drive.TankDrive(0.0, 0.0);
    } else if (0.0 < centerX && centerX < 320.0) {
      SmartDashboard.putBoolean("Found Ball", true);
      leftgreed = ((centerX - 160) / 160.0);
      rightgreed = -((centerX - 160.0) / 160.0);
      RobotModule.drive.TankDrive(rightgreed, -leftgreed);
      SmartDashboard.putNumber("Right Greed", rightgreed);
      SmartDashboard.putNumber("Left Greed", leftgreed);
      // TODO based on width, forward or backward
      // eventually 4-based calculation
    }
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
