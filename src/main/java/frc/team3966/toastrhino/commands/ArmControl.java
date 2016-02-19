package frc.team3966.toastrhino.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import frc.team3966.toastrhino.RobotModule;

/**
 *
 */
public class ArmControl extends Command {

  NetworkTable GRIPtable;

  public ArmControl() {
    requires(RobotModule.armAim);
  }

  // Called just before this Command runs the first time
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  protected void execute() {
    RobotModule.armBallGrab.grab(
        ((RobotModule.oi.controller.getRawAxis(4) + 1.0) / 2.0) - // Right trigger to fire out
        ((RobotModule.oi.controller.getRawAxis(3) + 1.0) / 4.0)   // Left trigger to suck up (half speed)
        );
    RobotModule.armAim.setHeight(RobotModule.oi.controller.getRawAxis(2));
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
