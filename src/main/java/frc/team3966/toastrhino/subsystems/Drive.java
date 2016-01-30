package frc.team3966.toastrhino.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj.CANTalon;

import frc.team3966.toastrhino.RobotMap;
import frc.team3966.toastrhino.commands.TankDrive;

//import jaci.openrio.toast.lib.registry.*;

/**
 *
 */
public class Drive extends Subsystem {

  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  
  private static CANTalon FRmotor = new CANTalon(RobotMap.FRmotor);
  private static CANTalon BRmotor = new CANTalon(RobotMap.BRmotor);
  private static CANTalon FLmotor = new CANTalon(RobotMap.FLmotor);
  private static CANTalon BLmotor = new CANTalon(RobotMap.BLmotor);
  
  public Drive () {
    FRmotor.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
    BRmotor.changeControlMode(CANTalon.TalonControlMode.Speed);
    FLmotor.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
    BLmotor.changeControlMode(CANTalon.TalonControlMode.Speed);
    //FRmotor.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
    BRmotor.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
    //FLmotor.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
    BLmotor.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
    //FRmotor.setPID();
    BRmotor.setPID(1, 0, 0.01);
    //FLmotor.setPID();
    BLmotor.setPID(1, 0, 0.01);
    
    // Front encoders are not connected, but talons still obey switches?
    FRmotor.ConfigFwdLimitSwitchNormallyOpen(true);
    BRmotor.ConfigFwdLimitSwitchNormallyOpen(false);
    FLmotor.ConfigFwdLimitSwitchNormallyOpen(true);
    BLmotor.ConfigFwdLimitSwitchNormallyOpen(false);
    
    //FRmotor.ConfigRevLimitSwitchNormallyOpen(false);
    BRmotor.ConfigRevLimitSwitchNormallyOpen(false);
    //FLmotor.ConfigRevLimitSwitchNormallyOpen(false);
    BLmotor.ConfigRevLimitSwitchNormallyOpen(false);
  }
  
  public double getBLEncPos() {
    return BLmotor.getEncPosition();
  }
  
  public double getBREncPos() {
    return BRmotor.getEncPosition();
  }
  
  public void TankDrive(double rightspeed, double leftspeed) {
    FRmotor.set(rightspeed);
    BRmotor.set(rightspeed * RobotMap.topspeed);
    FLmotor.set(leftspeed);
    BLmotor.set(leftspeed * RobotMap.topspeed);

    SmartDashboard.putData("Back Left", BLmotor);
    SmartDashboard.putData("Back Right", BRmotor);
    SmartDashboard.putNumber("BL Enc Speed", BLmotor.getEncVelocity());
    SmartDashboard.putNumber("BR Enc Speed", BRmotor.getEncVelocity());
    SmartDashboard.putNumber("BL Setpoint", BLmotor.getSetpoint());
    SmartDashboard.putNumber("BR Setpoint", BRmotor.getSetpoint());
    SmartDashboard.putBoolean("BL Control", BLmotor.isControlEnabled());
    SmartDashboard.putBoolean("BR Control", BRmotor.isControlEnabled());
    SmartDashboard.putBoolean("BL Alive", BLmotor.isAlive());
    SmartDashboard.putBoolean("BR Alive", BRmotor.isAlive());
    SmartDashboard.putNumber("BL Error", BLmotor.getError());
    SmartDashboard.putNumber("BR Error", BRmotor.getError());
    SmartDashboard.putNumber("BL Voltage", BLmotor.getOutputVoltage());
    SmartDashboard.putNumber("BR Voltage", BRmotor.getOutputVoltage());
  }
  
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new TankDrive());
  }

  public void doNothing() {
    FRmotor.set(0.0);
    BRmotor.set(0.0);
    FLmotor.set(0.0);
    BLmotor.set(0.0);
  }
}

