package frc.team3966.toastrhino.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team3966.toastrhino.RobotModule;

/**
 *
 */
public class DoNothing extends Command {
  
  public boolean doneNothing = false;

  public DoNothing() {
    requires(RobotModule.drive);
    requires(RobotModule.armAim);
    requires(RobotModule.armBallGrab);
    doneNothing = false;
  }

  // Called just before this Command runs the first time
  protected void initialize() {
    RobotModule.logger.info("Doing Nothing!");
    RobotModule.drive.doNothing();
    RobotModule.armAim.doNothing();
    RobotModule.armBallGrab.doNothing();
    doneNothing = true;
  }

  // Called repeatedly when this Command is scheduled to run
  protected void execute() {
    // Do Nothing!
  }

  // Make this return true when this Command no longer needs to run execute()
  protected boolean isFinished() {
    return doneNothing;
  }

  // Called once after isFinished returns true
  protected void end() {
    // Set things to zero.
    RobotModule.drive.doNothing();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  protected void interrupted() {
    RobotModule.drive.doNothing();
  }
}
