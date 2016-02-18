package frc.team3966.toastrhino.subsystems;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team3966.toastrhino.RobotModule;

/**
 *
 */
public class Sensors extends Subsystem {

  public static BuiltInAccelerometer accel;
  public static PowerDistributionPanel PDP;

  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  
  public Sensors() {
    accel = new BuiltInAccelerometer();
    PDP = new PowerDistributionPanel();
  }

  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    //setDefaultCommand(new MySpecialCommand());
  }

  public void dash_accel() {
    SmartDashboard.putData("InternalAccelerometer", accel);
  }

  public void dash_power() {
    SmartDashboard.putNumber("PDP Voltage", PDP.getVoltage());
    SmartDashboard.putNumber("PDP Total Amps", PDP.getTotalCurrent());
  }

  public void dash_submodule_navigation() {
    SmartDashboard.putNumber("Degrees C", RobotModule.navigation.getTempC());
    SmartDashboard.putNumber("Angle", RobotModule.navigation.getAngle());
    SmartDashboard.putNumber("Compass Heading", RobotModule.navigation.getCompassHeading());
    SmartDashboard.putNumber("Yaw", RobotModule.navigation.getYaw());
    SmartDashboard.putNumber("Roll", RobotModule.navigation.getRoll());
    SmartDashboard.putNumber("Pitch", RobotModule.navigation.getPitch());
    SmartDashboard.putNumber("Total Displacement", RobotModule.navigation.getDisplacementTotal());
    SmartDashboard.putNumber("Displacement X", RobotModule.navigation.getDisplacementX());
    SmartDashboard.putNumber("Displacement Y", RobotModule.navigation.getDisplacementY());
    SmartDashboard.putNumber("Displacement Z", RobotModule.navigation.getDisplacementZ());
    SmartDashboard.putBoolean("Calibrating", RobotModule.navigation.isCalibrating());
    SmartDashboard.putBoolean("Magnetic Disturbance", RobotModule.navigation.isMagneticDisturbance());
  }
  
  public void dash_submodule_drive() {
    RobotModule.drive.dash_all();
  }
  
  public void dash_submodule_arm() {
    RobotModule.armAim.dash_all();
  }
  
  public void dash_submodule_armKicker() {
    RobotModule.armKicker.dash_all();
  }

  public void dash_all() {
    dash_accel();
    dash_power();
    dash_submodule_navigation();
    dash_submodule_drive();
    dash_submodule_arm();
    dash_submodule_armKicker();
  }
}
