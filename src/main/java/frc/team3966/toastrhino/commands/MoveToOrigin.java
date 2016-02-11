package frc.team3966.toastrhino.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team3966.toastrhino.RobotModule;
import frc.team3966.toastrhino.subsystems.Navigation;

public class MoveToOrigin extends Command {
	public static final double constantMotorSpeed = .5f; //for just driving
	public static final double constantAngleMotorSpeed = .5f; //for turning angles

  public MoveToOrigin() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(RobotModule.navigation);
    requires(RobotModule.drive);
  }

  // Called just before this Command runs the first time
  protected void initialize() {
	  SmartDashboard.putBoolean("isHome?", true);
  }

  // Called repeatedly when this Command is scheduled to run
  protected void execute() {
	  double slop = .1f;
	  double degSlop = 1f;
	  double x = RobotModule.navigation.getDisplacementX();
	  double y = RobotModule.navigation.getDisplacementY();
	  double desireDeg = radToDeg(getDesiredAngle(x, y));
	  double curYaw = RobotModule.navigation.getYaw();
	  double delta = curYaw - desireDeg; //difference in actual and desired
	  double lspeed = 0;
	  double rspeed = 0;
	  SmartDashboard.putNumber("Displacement X", x);
	  SmartDashboard.putNumber("Displacement Y", y);
	  SmartDashboard.putNumber("Yaw", curYaw);
	  SmartDashboard.putNumber("Desired Yaw", desireDeg);
	  
	  if (!(delta <= degSlop && delta >= -degSlop)) {
		  if (curYaw > desireDeg) { //turn left
			  lspeed = -constantAngleMotorSpeed;
			  
		  } else {
			  lspeed = constantAngleMotorSpeed; //turn right
		  }
		  rspeed = -lspeed;
	  } else {
		  lspeed = constantMotorSpeed;
		  rspeed = lspeed;
	  }
	  if ((x <= slop && x >= -slop) && (y <= slop && y >= -slop)) { //if we are at origin
		  lspeed = 0;
		  rspeed = 0;
		  if (!(curYaw <= degSlop && curYaw >= -degSlop)) { //if we aren't rotated to our last reset's orientation
			  lspeed = -constantAngleMotorSpeed * Math.signum(curYaw);
			  rspeed = -lspeed;
		  }
		  SmartDashboard.putBoolean("isHome?", true);
	  } else {
		  SmartDashboard.putBoolean("isHome?", false);
	  }
	  RobotModule.drive.TankDrive(rspeed, lspeed);
  }
  
  
  
  private double getDesiredAngle(double x, double y) { //radians
	  if (y == 0) {
		  return -90 * Math.signum(x);
	  } else if (y > 0) {
		  return (double)(Math.PI - Math.atan(x / y));
	  } else {
		  return (double)(Math.atan(x / y));
	  }
  }
  
  private double degToRad(double deg) {
	  return (double)(Math.PI * deg / 180.0);
  }
  
  private double radToDeg(double rad) {
	  return (double)(180.0 * rad / Math.PI);
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
