package frc.team3966.toastrhino.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team3966.toastrhino.RobotModule;

/**
 *
 */
public class KickOut extends Command {

  public static final double maxTime = .25;//max time, in seconds
  public static final double convertFactor = Math.pow(10, 9);
  private long startTime = 0; //dont touch :P

  public KickOut() {
    requires(RobotModule.arm);
  }

  // Called just before this Command runs the first time
  protected void initialize() {
    startTime = System.nanoTime();
  }

  // Called repeatedly when this Command is scheduled to run
  protected void execute() {
    // Do some stuff
    //arm.kickout or something
  }

  // Make this return true when this Command no longer needs to run execute()
  protected boolean isFinished() {
    return (System.nanoTime() > (startTime + (maxTime * convertFactor)));
  }

  // Called once after isFinished returns true
  protected void end() {
    // Set things to zero.
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  protected void interrupted() {
  }
}
