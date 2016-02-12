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
	  motorSpeed = .75f;
	  angleMotorSpeed = .75f;
	  SmartDashboard.putBoolean("isHome?", true);
  }

  // Called repeatedly when this Command is scheduled to run
  protected void execute() {
	  double slop = 1f;
	  double degSlop = 3f;
	  
	  //algorithm
	  double x = RobotModule.navigation.getDisplacementX();
	  double y = RobotModule.navigation.getDisplacementY();
	  double desireDeg = RobotModule.navigation.radToDeg(RobotModule.navigation.getDesiredAngle(x, y));
	  double r = Math.sqrt(x * x + y * y);
	  double curYaw = RobotModule.navigation.getYaw();
	  double lspeed = 0;
	  double rspeed = 0;
	  double angleOffset = desireDeg - curYaw;
	  
	  if (FastArithmetic.isWithinSlop(r, slop)) {
		  lspeed = 0;
		  rspeed = lspeed;
	  } else {
		  lspeed = motorSpeed;
	  }
	  
	  if (!FastArithmetic.isWithinSlop_yaw(desireDeg, curYaw, degSlop)) {
		  if (angleOffset > 0) {
			  lspeed = -angleMotorSpeed;
		  } else {
			  lspeed = angleMotorSpeed;
		  }
		  rspeed = -lspeed;
		  if (Math.signum(lastAngleOffset) != Math.signum(angleOffset)) {
			  angleMotorSpeed *= .996;
		  }
	  }
	  
	  
	  RobotModule.drive.TankDrive(rspeed, lspeed);
	  
	  //keeping track of the last offset
	  lastAngleOffset = angleOffset;
	  
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
