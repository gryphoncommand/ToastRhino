package frc.team3966.toastrhino.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team3966.toastrhino.RobotModule;

/**
 *
 */
public class Sensors extends Subsystem {

  public static BuiltInAccelerometer accel = new BuiltInAccelerometer();
  public static AnalogInput gripper_pos = new AnalogInput(1);
  public static PowerDistributionPanel PDP = new PowerDistributionPanel();

  // Put methods for controlling this subsystem
  // here. Call these from Commands.

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

  public void dash_navx() {
    SmartDashboard.putNumber("Degrees C", RobotModule.navigation.getTempC());
    SmartDashboard.putNumber("Angle", RobotModule.navigation.getAngle());
    SmartDashboard.putNumber("Yaw", RobotModule.navigation.getYaw());
    SmartDashboard.putNumber("Roll", RobotModule.navigation.getRoll());
    SmartDashboard.putNumber("Pitch", RobotModule.navigation.getPitch());
    SmartDashboard.putNumber("Total Displacement", RobotModule.navigation.getDisplacementTotal());
    SmartDashboard.putNumber("Displacement X", RobotModule.navigation.getDisplacementX());
    SmartDashboard.putNumber("Displacement Y", RobotModule.navigation.getDisplacementY());
    SmartDashboard.putNumber("Displacement Z", RobotModule.navigation.getDisplacementZ());
  }

  public void dash_all() {
    dash_accel();
    dash_power();
    dash_navx();
  }
}
