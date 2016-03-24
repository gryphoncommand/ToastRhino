package frc.team3966.toastrhino.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import frc.team3966.toastrhino.RobotModule;
import frc.team3966.toastrhino.util.AppliedFunctions;

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
    if (RobotModule.oi.controller.getL2Button() || RobotModule.oi.controller.getR2Button() ) {
      RobotModule.armBallGrab.grab(
          ((RobotModule.oi.controller.getRightTriggerAxis() + 1.0) / 2.0) -// Right trigger to fire out
          ((RobotModule.oi.controller.getLeftTriggerAxis() + 1.0) / 4.0)   // Left trigger to suck up (half speed)
          );
    } else {
      RobotModule.armBallGrab.grab(
          ((RobotModule.oi.controller2.getRightTriggerAxis() + 1.0) / 2.0) -// Right trigger to fire out
          ((RobotModule.oi.controller2.getLeftTriggerAxis() + 1.0) / 4.0)   // Left trigger to suck up (half speed)
          );
    }

    if (RobotModule.oi.controller.getR1Button()) { // Master Controller Right
      RobotModule.armAim.setAmotor(-1.0);
    } else if (RobotModule.oi.controller.getL1Button()) { // Master Controller Left
      RobotModule.armAim.setAmotor(1.0);
    } else if (RobotModule.oi.controller2.getR1Button()) { // Controller 2 Right
      RobotModule.armAim.setAmotor(-1.0);
    } else if (RobotModule.oi.controller2.getL1Button()) { // Controller 2 Left
      RobotModule.armAim.setAmotor(1.0);
    } else { // When none of the buttons are pressed.
      RobotModule.armAim.setAmotor(0.0);
    }

    if (RobotModule.oi.controller.getTriangleButton()) {
    	RobotModule.armAim.setHeightRelative(AppliedFunctions.degreesAtEncoderMax); //Max or min... Cant remember
    } else if (RobotModule.oi.controller.getCircleButton()) {
    	RobotModule.armAim.setHeightRelative(AppliedFunctions.degreesAtEncoderMin); //Max or min... Cant remember
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
