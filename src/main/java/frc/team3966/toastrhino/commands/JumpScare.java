package frc.team3966.toastrhino.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team3966.toastrhino.RobotModule;

public class JumpScare extends Command {

  public JumpScare() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(RobotModule.navigation);
    requires(RobotModule.drive);
  }

  // Called just before this Command runs the first time
  protected void initialize() {
    SmartDashboard.putBoolean("Scared?", RobotModule.navigation.isMoving());
  }

  // Called repeatedly when this Command is scheduled to run
  protected void execute() {
    SmartDashboard.putBoolean("Scared?", RobotModule.navigation.isMoving());
    
    if (RobotModule.navigation.isMoving() == true) {
      RobotModule.drive.TankDrive(0.1, -0.1);
    } else {
      RobotModule.drive.TankDrive(0.0, 0.0);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  protected boolean isFinished() {
    return false;
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
