package frc.team3966.toastrhino.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team3966.toastrhino.RobotModule;

public class MoveToOrigin extends Command {

  public MoveToOrigin() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(RobotModule.navigation);
    requires(RobotModule.drive);
  }

  // Called just before this Command runs the first time
  protected void initialize() {
    SmartDashboard.putBoolean("I am Home?", false);
  }

  // Called repeatedly when this Command is scheduled to run
  protected void execute() {
    
  }
  
  // Make this return true when this Command no longer needs to run execute()
  protected boolean isFinished() {
    // Are we close enough to home? results are in meters
    if (RobotModule.navigation.getDisplacementTotal() < 0.2) // then...
      return true;
    else return false;
  }

  // Called once after isFinished returns true
  protected void end() {
    RobotModule.drive.doNothing();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  protected void interrupted() {
    RobotModule.drive.doNothing();
  }
}
