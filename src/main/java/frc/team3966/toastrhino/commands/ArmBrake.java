package frc.team3966.toastrhino.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team3966.toastrhino.RobotModule;

/**
 *
 */
public class ArmBrake extends Command {

  public static final double maxTime = .03; //max time, in seconds (2/60 of a second)
  public static final double convertFactor = Math.pow(10, 9);
  private long startTime = 0; //dont touch :P

  public ArmBrake() {
    //requires(RobotModule.armAim);
  }

  // Called just before this Command runs the first time
  protected void initialize() {
    startTime = System.nanoTime();
    RobotModule.armAim.setHeightAbsolute(0);
  }

  // Called repeatedly when this Command is scheduled to run
  protected void execute() {
    // Do some stuff
    if (System.nanoTime() < (startTime + (maxTime * convertFactor))) {
      RobotModule.armAim.setHeightAbsolute(0);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  protected boolean isFinished() {
    return (System.nanoTime() > (startTime + (maxTime * convertFactor)));
  }

  // Called once after isFinished returns true
  protected void end() {
    // Already done.
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  protected void interrupted() {
    // Nothing
  }
}
