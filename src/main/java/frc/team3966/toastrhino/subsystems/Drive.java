package frc.team3966.toastrhino.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj.VictorSP;

import frc.team3966.toastrhino.RobotMap;
import frc.team3966.toastrhino.commands.TankDrive;

public class Drive extends Subsystem {

  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  
  private static VictorSP FRmotor = new VictorSP(RobotMap.FRmotor);
  private static VictorSP BRmotor = new VictorSP(RobotMap.BRmotor);
  private static VictorSP FLmotor = new VictorSP(RobotMap.FLmotor);
  private static VictorSP BLmotor = new VictorSP(RobotMap.BLmotor);
  
  public Drive () {
    //FRmotor.setPID();
    //BRmotor.setPID(1, 0, 0.01);
    //FLmotor.setPID();
    //BLmotor.setPID(1, 0, 0.01);
    FRmotor.setInverted(false);
    BRmotor.setInverted(false);
    FLmotor.setInverted(true);
    BLmotor.setInverted(true);
  }
  
  public double getBLEncPos() {
    //return BLmotor.getEncPosition();
    return 0.0;
  }
  
  public double getBREncPos() {
    //return BRmotor.getEncPosition();
    return 0.0;
  }
  
  public void TankDrive(double rightspeed, double leftspeed) {
    FRmotor.set(rightspeed);
    BRmotor.set(rightspeed);
    FLmotor.set(leftspeed);
    BLmotor.set(leftspeed);

    SmartDashboard.putData("Back Left", BLmotor);
    SmartDashboard.putData("Back Right", BRmotor);
  }
  
  public void Rotate (double clockwisespeed) {
    FRmotor.set(clockwisespeed);
    BRmotor.set(clockwisespeed);
    FLmotor.set(-clockwisespeed);
    BLmotor.set(-clockwisespeed);
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

