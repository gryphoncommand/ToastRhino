package frc.team3966.toastrhino.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team3966.toastrhino.RobotModule;
import frc.team3966.toastrhino.util.AppliedFunctions;

/**
 *
 */
public class AutoAimVertical extends Command {

  public AutoAimVertical() {
    // Should not lock any other subsystems
    // requires(RobotModule.grip);
  }

  // Called just before this Command runs the first time
  protected void initialize() {
    
  }

  // Called repeatedly when this Command is scheduled to run
  protected void execute() {
    // Do some stuff
    RobotModule.armAim.setHeightAbsolute(AppliedFunctions.getPotValueFromCenterY(RobotModule.grip.getCenterY()) - 4.0);
  }

  // Make this return true when this Command no longer needs to run execute()
  protected boolean isFinished() {
    RobotModule.logger.info("AutoAimed Vertical");
    return true;
  }

  // Called once after isFinished returns true
  protected void end() {
    // Nothing.
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  protected void interrupted() {
    RobotModule.armKicker.KickMotor(0.0);
  }
}
