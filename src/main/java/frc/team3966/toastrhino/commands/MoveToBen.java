package frc.team3966.toastrhino.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team3966.toastrhino.RobotModule;

public class MoveToBen extends Command {
  
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
  
  public MoveToBen() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(RobotModule.navigation);
    requires(RobotModule.drive);
  }

  // Called just before this Command runs the first time
  protected void initialize() {
    SmartDashboard.putBoolean("I am Home?", false);
    SmartDashboard.putBoolean("On Target?", false);
    
    Rotater = new PIDController(10, 0.1, 1.0, new RobotYaw(), new OutputRotation());
    Rotater.setInputRange(-180.0, 180.0);
    Rotater.setOutputRange(-180.0, 180.0);
    Rotater.setContinuous(true);
    Rotater.setAbsoluteTolerance(5);
    SmartDashboard.putNumber("Rotation Needed", Rotater.getError());
  }

  // Called repeatedly when this Command is scheduled to run
  protected void execute() {
    SmartDashboard.putBoolean("I am Home?", isFinished());
    SmartDashboard.putNumber("Rotation Needed", Rotater.getError());
    SmartDashboard.putData("Rotater PID", Rotater);
    
    if (Math.abs(Rotater.getError()) > 2) {
      Rotater.enable();
      Rotater.setSetpoint(this.getAngleToOrigin());
    } else {
      Rotater.disable();
      leftp = rightp = RobotModule.navigation.getDisplacementTotal() / 3.0;
      // Our full power is too fast for precision movement.
      leftp = this.ensureRange(leftp, -0.6, 0.6);
      rightp = this.ensureRange(rightp, -0.6, 0.6);
      RobotModule.drive.TankDrive(rightp, leftp);
    }
    
    SmartDashboard.putBoolean("On Target?", (Math.abs(Rotater.getError()) < 2));
    SmartDashboard.putBoolean("I am Home?", isFinished());
    
  }
  
  // Make this return true when this Command no longer needs to run execute()
  protected boolean isFinished() {
    // Are we close enough to home? results are in meters
    if (RobotModule.navigation.getDisplacementTotal() < 0.4) // then...
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
    //return Math.atan(RobotModule.navigation.getDisplacementX() / RobotModule.navigation.getDisplacementY());
    angle = 0;
    if (y == 0) {
      if (x > 0) {
        angle = - Math.PI / 2;
      } else {
        angle = Math.PI / 2;
      }
    }
    double atan = Math.atan(Math.abs(x / y));
    if (x > 0 && y > 0) {
      angle = - Math.PI + atan;
    } 
    if (x < 0 && y > 0) {
      angle = Math.PI - atan;
    }
    if (x > 0 && y < 0) {
      angle = -atan;
    }
    if (x < 0 && y < 0) {
      angle = atan;
    }
    return (angle * 180) / Math.PI;
  }
  
  double ensureRange(double value, double min, double max) {
    return Math.min(Math.max(value, min), max);
  }

}