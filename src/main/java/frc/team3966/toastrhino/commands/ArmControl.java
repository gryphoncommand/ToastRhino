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
    RobotModule.armAim.enablePID(true);
  }

  // Called repeatedly when this Command is scheduled to run
  protected void execute() {
    RobotModule.armBallGrab.grab(
        ((RobotModule.oi.controller.getRightTriggerAxis() + 1.0) / 2.0) -// Right trigger to fire out
        ((RobotModule.oi.controller.getLeftTriggerAxis() + 1.0) / 4.0)   // Left trigger to suck up (half speed)
        );
    
    if (RobotModule.oi.controller.getR1Button()) {
      RobotModule.armAim.setAmotor(-1.0);
    } else if (RobotModule.oi.controller.getL1Button()) {
      RobotModule.armAim.setAmotor(1.0);
    } else {
      RobotModule.armAim.setAmotor(0.0);
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
