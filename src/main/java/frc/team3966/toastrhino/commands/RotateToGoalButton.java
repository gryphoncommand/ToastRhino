package frc.team3966.toastrhino.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team3966.toastrhino.RobotModule;

public class RotateToGoalButton extends Command {
  
	NetworkTable GRIPtable;
	
  static double yawToOrigin, leftp, rightp;
  static double angle;
  static double x, y, z;
  static PIDController YawToOrigin;
  
  // This is a dummy class to get our wanted angle for the PIDController.
  public class RobotYaw implements PIDSource {
    @Override
    public double pidGet() {
      return this.get();
    }
    public double get() {
      return RobotModule.navigation.getYaw();
    }
    @Override
    public void setPIDSourceType(PIDSourceType pidSource) {
      RobotModule.logger.info("Set PID source type." + pidSource.toString());
    }
    @Override
    public PIDSourceType getPIDSourceType() {
      return PIDSourceType.kDisplacement;
    }
  }
  
  // The same kind of dummy class, but to output to rotation
  public class OutputRotation implements PIDOutput {
    @Override
    public void pidWrite(double output) {
      this.set(output / 180.0);
    }
    public void set(double output) {
      RobotModule.drive.Rotate(output);
    }
  }
  
  PIDController Rotater;
  
  public RotateToGoalButton() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(RobotModule.navigation);
    requires(RobotModule.drive);
  }

  // Called just before this Command runs the first time
  protected void initialize() {
	  
	  requires(RobotModule.drive);
	  GRIPtable = NetworkTable.getTable("GRIP/goal");
    SmartDashboard.putBoolean("Looking at goal?", false);
    SmartDashboard.putBoolean("Pointed at goal??", false);
    
    Rotater = new PIDController(10, 0.1, 1.0, new RobotYaw(), new OutputRotation());
    Rotater.setInputRange(-180.0, 180.0);
    Rotater.setOutputRange(-180.0, 180.0);
    Rotater.setContinuous(true);
    Rotater.setAbsoluteTolerance(5);
    SmartDashboard.putNumber("Rotation Needed", Rotater.getError());
  }

  // Called repeatedly when this Command is scheduled to run
  protected void execute() {

	  SmartDashboard.putBoolean("Looking at goal?", isFinished());
	SmartDashboard.putNumber("Degrees left to move: ", Rotater.getError());
    SmartDashboard.putData("Rotater PID", Rotater);
    
    if (Math.abs(Rotater.getError()) > 2) {
      Rotater.enable();
      Rotater.setSetpoint(this.getAngleOffsetToGoal() + RobotModule.navigation.getYaw());
    }
    
    SmartDashboard.putBoolean("On Target?", (Math.abs(Rotater.getError()) < 2));
    SmartDashboard.putBoolean("I am Home?", isFinished());
    
  }
  
  // Make this return true when this Command no longer needs to run execute()
  protected boolean isFinished() {
    // Are we close enough to home? results are in meters
    if (this.Rotater.onTarget()) // then...
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
  
  //Picture is 320x240
  public double getAngleOffsetToGoal() {
     double centerOfPic = 320 / 2; //What the centerX should be, if aligned
     double[] GRIPcenterX = GRIPtable.getNumberArray("centerX");
     double centerX = centerOfPic;
     if (GRIPcenterX.length > 0) {
    	 centerX = GRIPcenterX[0];
     }
     double deg = (centerX - centerOfPic) / 8.0; //scaled arbitrarily
     return deg;
  }
  
  double ensureRange(double value, double min, double max) {
    return Math.min(Math.max(value, min), max);
  }

}