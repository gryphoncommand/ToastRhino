package frc.team3966.toastrhino.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team3966.toastrhino.RobotModule;

public class GyroMatch extends Command {

  public GyroMatch() {
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
    SmartDashboard.putNumber("Compass Heading", RobotModule.navigation.getAngle());

    //    if ( RobotModule.navigation.getNorthHeading() > 195 ) {
    //      RobotModule.drive.TankDrive(0.3, -0.3);
    //    } else if ( RobotModule.navigation.getNorthHeading() < 165 ) {
    //      RobotModule.drive.TankDrive(-0.3, 0.3);
    //    } else {
    double leftp = (( RobotModule.navigation.getAngle() - 180 ) / 40.0);
    double rightp = - (( RobotModule.navigation.getAngle() - 180 ) / 40.0);
    
    leftp = this.ensureRange(leftp, -0.5, 0.5);
    rightp = this.ensureRange(rightp, -0.5, 0.5);

    RobotModule.drive.TankDrive(rightp, leftp);
    //    } 
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

  double ensureRange(double value, double min, double max) {
    return Math.min(Math.max(value, min), max);
  }
}
