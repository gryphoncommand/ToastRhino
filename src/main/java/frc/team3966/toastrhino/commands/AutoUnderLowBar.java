package frc.team3966.toastrhino.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team3966.toastrhino.RobotModule;
import frc.team3966.toastrhino.util.AppliedFunctions;

/**
 *
 */
public class AutoUnderLowBar extends Command {

  public static final double convertFactor = Math.pow(10, 9);
  private static double startTime = 0; //dont touch :P

  public AutoUnderLowBar() {
    requires(RobotModule.drive);
    requires(RobotModule.armAim);
    requires(RobotModule.navigation);
  }

  // Called just before this Command runs the first time
  protected void initialize() {
    startTime = System.nanoTime();
    RobotModule.armAim.setHeightAbsolute(68.0);
    RobotModule.drive.doNothing();
  }

  // Called repeatedly when this Command is scheduled to run
  protected void execute() {
    // Do some stuff
    if (System.nanoTime() < (startTime + (1.0 * convertFactor))) {
      // Time before maxTime is over
      RobotModule.drive.TankDrive(0.0, 0.0);
    } if (System.nanoTime() < (startTime + ( 3.0 * convertFactor))) {
      // Time before maxTime is over
      RobotModule.drive.TankDrive(0.9, 0.9);
    } else if (System.nanoTime() > (startTime + ( 5.0 * convertFactor))) {
      // After maxTime is over
      RobotModule.drive.doNothing();
    }
    
  }

  // Make this return true when this Command no longer needs to run execute()
  protected boolean isFinished() {
    return (System.nanoTime() > (startTime + ( 12.0 * convertFactor)));
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
