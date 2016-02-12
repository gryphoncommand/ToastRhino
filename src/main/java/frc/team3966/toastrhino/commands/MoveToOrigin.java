package frc.team3966.toastrhino.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team3966.toastrhino.RobotModule;
import frc.team3966.toastrhino.subsystems.Navigation;
import frc.team3966.toastrhino.util.FastArithmetic;

public class MoveToOrigin extends Command {
	public static double motorSpeed = .5f; //for just driving
	public static double angleMotorSpeed = .5f; //for turning angles
	public double lastAngleOffset = 0; //how close were we last time?
	
  public MoveToOrigin() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(RobotModule.navigation);
    requires(RobotModule.drive);
  }

  // Called just before this Command runs the first time
  protected void initialize() {
	  motorSpeed = .5f;
	  angleMotorSpeed = .5f;
	  SmartDashboard.putBoolean("isHome?", true);
  }

  // Called repeatedly when this Command is scheduled to run
  protected void execute() {
	  double slop = 0.8f;
	  double degSlop = 3f;
	  
	  //algorithm
	  double x = RobotModule.navigation.getDisplacementX();
	  double y = RobotModule.navigation.getDisplacementY();
	  double desireDeg = RobotModule.navigation.radToDeg(RobotModule.navigation.getDesiredAngle(x, y));
	  double r = Math.sqrt(x * x + y * y);
	  double curYaw = RobotModule.navigation.getYaw();
	  double delta = desireDeg - curYaw; //difference in actual and desired
	  double lspeed = 0;
	  double rspeed = 0;
	  
	  if (FastArithmetic.isWithinSlop(r, slop)) { //if we are at the origin
		  lspeed = 0;
		  rspeed = 0;
		  motorSpeed = .5f;
	  } else { //if we are not
		  lspeed = motorSpeed;
		  rspeed = lspeed;
	  }
	  //this method overwrites the above speeds, meaning that we take priority in aiming directly at origin.
	  if (!FastArithmetic.isWithinSlop(delta, degSlop)) { //turns right or left, depending if it over-shot or under-shot
		  if (Math.signum(delta) != Math.signum(lastAngleOffset)) {
			  angleMotorSpeed /= 1.008; //decreases if we overshot it, and then it will reverse direction.
		  }
		  if (lastAngleOffset > 0) { //turns left
			  lspeed = -angleMotorSpeed;
			  rspeed = -lspeed;
		  } else {//turns right
			  lspeed = angleMotorSpeed;
			  rspeed = -lspeed;
		  }

	  }

	  RobotModule.drive.TankDrive(rspeed, lspeed);
	  
	  //keeping track of the last offset
	  lastAngleOffset = delta;
	  
	  //Logging and such
	  SmartDashboard.putNumber("Displacement X", x);
	  SmartDashboard.putNumber("Displacement Y", y);
	  SmartDashboard.putNumber("Yaw", curYaw);
	  SmartDashboard.putNumber("Desired Yaw", desireDeg);
	  SmartDashboard.putNumber("Angle turning speed", angleMotorSpeed); //how fast we turn.
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
