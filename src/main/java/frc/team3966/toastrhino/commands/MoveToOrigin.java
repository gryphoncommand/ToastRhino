package frc.team3966.toastrhino.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team3966.toastrhino.RobotModule;
import frc.team3966.toastrhino.subsystems.Navigation;

public class MoveToOrigin extends Command {
	public static final double constantMotorSpeed = .5f; //for just driving
	public static double constantAngleMotorSpeed = .4f; //for turning angles

  public MoveToOrigin() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(RobotModule.navigation);
    requires(RobotModule.drive);
  }

  // Called just before this Command runs the first time
  protected void initialize() {
	  constantAngleMotorSpeed = .5f;
	  SmartDashboard.putBoolean("isHome?", true);
  }

  // Called repeatedly when this Command is scheduled to run
  protected void execute() {
	  double slop = 0.8f;
	  double degSlop = 3f;
	  double x = RobotModule.navigation.getDisplacementX();
	  double y = RobotModule.navigation.getDisplacementY();
	  double desireDeg = radToDeg(getDesiredAngle(x, y));
	  double r = Math.sqrt(x * x + y * y);
	  double curYaw = RobotModule.navigation.getYaw();
	  double delta = desireDeg - curYaw; //difference in actual and desired
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
		  //constantAngleMotorSpeed /= 1.004;
	  } else {
		  lspeed = constantMotorSpeed;
		  rspeed = lspeed;
	  }
	  if (r <= slop) { //if we are at origin
		  lspeed = 0;
		  rspeed = 0;
		  if (!(curYaw <= degSlop && curYaw >= -degSlop)) { //if we aren't rotated to our last reset's orientation
			  lspeed = -constantAngleMotorSpeed * Math.signum(curYaw);
			  rspeed = -lspeed;
		  }
		  SmartDashboard.putBoolean("isHome?", true);
		  //constantAngleMotorSpeed = .5f;
	  } else {
		  SmartDashboard.putBoolean("isHome?", false);
	  }
	  RobotModule.drive.TankDrive(-rspeed, -lspeed);
  }
  
  
  
  private double getDesiredAngle(double x, double y) { //radians
	  double total = 0;
	  if (y == 0) {
		  total = - Math.PI * Math.signum(x) / 2;
	  } else if (y > 0) {
		  if (x < 0) {
			  total = (double)(Math.PI - Math.atan(x / y));
		  } else {
			  total = (double)(-Math.PI + Math.atan(x / y));
		  }
	  } else {
		  if (x < 0) {
			  total = (double)(Math.atan(x / y));
		  } else {
			  total = (double)(-Math.atan(x / y));
		  }
	  }
	  if (total > Math.PI) {
		  total = - Math.PI + (total % Math.PI);
	  } else if (total < -Math.PI) {
		  total = Math.PI - Math.abs((total % Math.PI))
				  ;
	  }
	  return total;
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
