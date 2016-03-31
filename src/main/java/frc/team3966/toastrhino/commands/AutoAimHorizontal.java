package frc.team3966.toastrhino.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team3966.toastrhino.RobotModule;
import frc.team3966.toastrhino.util.AppliedFunctions;

/**
 *
 */
public class AutoAimHorizontal extends Command {

  public static final double maxTime = .5; // Absolute max time we take control of robot drive
  public static final double convertFactor = Math.pow(10, 9);
  private long startTime = 0; //dont touch :P
  private PIDController Rotater;

  public class RobotYaw implements PIDSource {
    @Override
    public double pidGet() {
      return this.get();
    }
    public double get() {
      return RobotModule.grip.getCenterX();
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
  
  public class OutputRotation implements PIDOutput {
    @Override
    public void pidWrite(double output) {
      this.set(output / 180.0);
    }
    public void set(double output) {
      RobotModule.drive.Rotate(output);
    }
  }
  
  public AutoAimHorizontal() {
    requires(RobotModule.drive);
  }

  // Called just before this Command runs the first time
  protected void initialize() {
    startTime = System.nanoTime();
    RobotModule.drive.Rotate(0.0);;
    
    Rotater = new PIDController(10, 0.1, 1.0, new RobotYaw(), new OutputRotation());
    Rotater.setInputRange(0, 320);
    Rotater.setOutputRange(-180.0, 180.0);
    Rotater.setContinuous(false);
    Rotater.setAbsoluteTolerance(5);
    Rotater.enable();
  }

  // Called repeatedly when this Command is scheduled to run
  protected void execute() {
    // Do some stuff
    SmartDashboard.putNumber("Rotation Needed", Rotater.getError());
    if (System.nanoTime() < (startTime + (maxTime * convertFactor))) { // During time, PID the drive rotation
      Rotater.setSetpoint(AppliedFunctions.getShooterCenterXUsingDistance(RobotModule.grip.getDistanceToGoal()));
    } else if (System.nanoTime() > (startTime + (maxTime * 2 * convertFactor))) {
      Rotater.disable();
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  protected boolean isFinished() {
    return (System.nanoTime() > (startTime + (maxTime * 2 * convertFactor)));
  }

  // Called once after isFinished returns true
  protected void end() {
    // Set things to zero.
    RobotModule.armKicker.KickMotor(0.0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  protected void interrupted() {
    RobotModule.armKicker.KickMotor(0.0);
  }
}
