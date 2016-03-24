package frc.team3966.toastrhino.commands;

import edu.wpi.first.wpilibj.command.Command;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team3966.toastrhino.RobotModule;

/**
 *
 */
public class TankDrive extends Command {

  public TankDrive() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(RobotModule.drive);
  }

  // Called just before this Command runs the first time
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  protected void execute() {
    //RobotModule.drive.TankDrive(RobotModule.oi.controller.getRawAxis(5), RobotModule.oi.controller.getRawAxis(1));
    // Note that "up" is negative on the axis
    RobotModule.drive.TankDrive(-RobotModule.oi.controller.getRightStickY(), -RobotModule.oi.controller.getLeftStickY());
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
