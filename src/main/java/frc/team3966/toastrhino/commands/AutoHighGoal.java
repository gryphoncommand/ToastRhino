package frc.team3966.toastrhino.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team3966.toastrhino.RobotModule;
import frc.team3966.toastrhino.util.AppliedFunctions;

/**
 *
 */
public class AutoHighGoal extends Command {

  public static final double convertFactor = Math.pow(10, 9);
  private static double startTime = 0; //dont touch :P
  boolean kicked = false;

  public AutoHighGoal() {
    //requires(RobotModule.drive);
    //requires(RobotModule.armAim);
    //requires(RobotModule.armBallGrab);
    //requires(RobotModule.navigation);
  }

  // Called just before this Command runs the first time
  protected void initialize() {
    startTime = System.nanoTime();
    RobotModule.armAim.setHeightAbsolute(30.0);
    RobotModule.drive.doNothing();
    
    kicked = false;
  }

  // Called repeatedly when this Command is scheduled to run
  protected void execute() {
    // Do some stuff
    if (System.nanoTime() < (startTime + (2.3 * convertFactor))) {
      // Over defense
      RobotModule.drive.TankDrive(0.7, 0.7);
    } else if (System.nanoTime() < (startTime + ( 2.7 * convertFactor))) { // Time to stop
      RobotModule.logger.info("Forward!");
      RobotModule.drive.doNothing();
    } else if (System.nanoTime() < (startTime + ( 4.0 * convertFactor))) { // Give autoaim time
      RobotModule.logger.info("AutoAim!");
      RobotModule.autoAim();
    } else if (System.nanoTime() < (startTime + ( 5.0 * convertFactor))) { // Fire!
      RobotModule.drive.doNothing();
      RobotModule.logger.info("Spin up!");
      RobotModule.armBallGrab.grab(1.0);
    } else if (System.nanoTime() < (startTime + ( 6.0 * convertFactor))) {
      RobotModule.armBallGrab.grab(1.0);
      if (kicked == false) {
        RobotModule.logger.info("Kick!");
        RobotModule.kickOut();
        kicked = true;
      }
    } else if (System.nanoTime() > (startTime + ( 10.0 * convertFactor))) {
      RobotModule.drive.doNothing();
      RobotModule.armBallGrab.grab(0.0);
    }
    
    if (this.isFinished()) RobotModule.logger.info("AutoHighGoal Done.");
    
  }

  // Make this return true when this Command no longer needs to run execute()
  protected boolean isFinished() {
    if ((System.nanoTime() > (startTime + ( 15 * convertFactor)))) {
      RobotModule.logger.info("AutoHighGoal Done.");
      return true;
    } else {
      return false;
    }
  }

  // Called once after isFinished returns true
  protected void end() {
    // Set things to zero.
    RobotModule.logger.info("AutoHighGoal Done.");
    RobotModule.drive.doNothing();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  protected void interrupted() {
    RobotModule.logger.info("AutoAimHigh Interrupted.");
    RobotModule.drive.doNothing();
  }
}
