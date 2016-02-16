package frc.team3966.toastrhino.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import frc.team3966.toastrhino.RobotModule;

/**
 *
 */
public class KickOut extends Command {

  public static final double maxTime = .25;//max time, in seconds
  public static final double converFactor = Math.pow(10, -9);
  public boolean doThis = true;
  public double time = 0;
  private long startTime = 0; //dont touch :P

  public KickOut() {
    requires(RobotModule.arm);
  }

  // Called just before this Command runs the first time
  protected void initialize() {
	  doThis = true;
	  startTime = System.nanoTime();
  }

  // Called repeatedly when this Command is scheduled to run
  protected void execute() {
    long ctime = System.nanoTime();
    time = (ctime - startTime) * converFactor;
    if (time >= maxTime) {
    	return;
    }
    if (doThis) {
    	//your code does this
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  protected boolean isFinished() {
    return !doThis;
  }

  // Called once after isFinished returns true
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  protected void interrupted() {
  }
}
