package frc.team3966.toastrhino.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team3966.toastrhino.RobotModule;
import frc.team3966.toastrhino.util.AppliedFunctions;

/**
 *
 */
public class MoveLowGoalStraight extends Command {

  public static final double maxTime = 3; // Base interval time (3 seconds is best.)
  public static final double convertFactor = Math.pow(10, 9);
  private static double startTime = 0; //dont touch :P

  public MoveLowGoalStraight() {
    requires(RobotModule.drive);
    requires(RobotModule.armAim);
    requires(RobotModule.armBallGrab);
    requires(RobotModule.navigation);
  }

  // Called just before this Command runs the first time
  protected void initialize() {
    startTime = System.nanoTime();
    RobotModule.armAim.setHeightAbsolute(20.0);
    RobotModule.drive.doNothing();
  }

  // Called repeatedly when this Command is scheduled to run
  protected void execute() {
    // Do some stuff
    if (System.nanoTime() < (startTime + (maxTime * convertFactor))) {
      // Time before maxTime is over
      RobotModule.drive.TankDrive(0.7, 0.7);
    } else if (System.nanoTime() < (startTime + (maxTime * 2 * convertFactor))) { // After 6 Seconds
      RobotModule.drive.doNothing();
      RobotModule.armAim.setHeightAbsolute(60);
    } else if (System.nanoTime() < (startTime + (maxTime * 2.5 * convertFactor))) { // After 6 Seconds
      RobotModule.drive.doNothing();
      RobotModule.armAim.setHeightAbsolute(60);
      RobotModule.armBallGrab.grab(1.0);
    } else if (System.nanoTime() > (startTime + (maxTime * 2.5 * convertFactor))) { // End game greater than
      RobotModule.drive.doNothing();
      RobotModule.armBallGrab.grab(0.0);
    }
    
  }

  // Make this return true when this Command no longer needs to run execute()
  protected boolean isFinished() {
    return (System.nanoTime() > (startTime + (maxTime * 4 * convertFactor)));
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
