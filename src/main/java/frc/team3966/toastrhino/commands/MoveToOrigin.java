package frc.team3966.toastrhino.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team3966.toastrhino.RobotModule;

public class MoveToOrigin extends Command {
  
  double yawToOrigin, leftp, rightp;
  
  public MoveToOrigin() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(RobotModule.navigation);
    requires(RobotModule.drive);
  }

  // Called just before this Command runs the first time
  protected void initialize() {
    SmartDashboard.putBoolean("I am Home?", false);
    SmartDashboard.putBoolean("On Target?", false);
  }

  // Called repeatedly when this Command is scheduled to run
  protected void execute() {
    SmartDashboard.putBoolean("I am Home?", isFinished());
    yawToOrigin = this.getYawToOrigin();
    
    if (RobotModule.navigation.getYaw() > (yawToOrigin + 10.0) 
     || RobotModule.navigation.getYaw() < (yawToOrigin - 10.0)) {
      SmartDashboard.putBoolean("On Target?", false);
      leftp = - (( RobotModule.navigation.getYaw() - yawToOrigin ) / 20.0);
      rightp = (( RobotModule.navigation.getYaw() - yawToOrigin ) / 20.0);
    } else {
      SmartDashboard.putBoolean("On Target?", true);
      leftp = rightp = RobotModule.navigation.getDisplacementTotal() / 3.0;
    }
    
    // Our full power is too fast for precision movement.
    leftp = this.ensureRange(leftp, -0.6, 0.6);
    rightp = this.ensureRange(rightp, -0.6, 0.6);
    RobotModule.drive.TankDrive(rightp, leftp);
    
    SmartDashboard.putBoolean("I am Home?", isFinished());
    
  }
  
  // Make this return true when this Command no longer needs to run execute()
  protected boolean isFinished() {
    // Are we close enough to home? results are in meters
    if (RobotModule.navigation.getDisplacementTotal() < 0.5) // then...
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
  
  public double getAngleToOrigin() {
    return Math.atan(RobotModule.navigation.getDisplacementX() / RobotModule.navigation.getDisplacementY());
  }

  private double getYawToOrigin() { //radians
    double y, x;
    x = RobotModule.navigation.getDisplacementX();
    y = RobotModule.navigation.getDisplacementY();
    if (y == 0) {
      return -90 * Math.signum(x);
    } else if (y > 0) {
      return (double)(Math.PI - Math.atan(x / y));
    } else {
      return (double)(Math.atan(x / y));
    }
  }
  
  double ensureRange(double value, double min, double max) {
    return Math.min(Math.max(value, min), max);
  }

}
