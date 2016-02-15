package frc.team3966.toastrhino.subsystems;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team3966.toastrhino.RobotMap;
import frc.team3966.toastrhino.RobotModule;
import frc.team3966.toastrhino.commands.TankDrive;

public class Drive extends Subsystem {

  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public class MotorController extends VictorSP {
    public MotorController(int motorpin) {
      super(motorpin);
    }

    @Override
    public void pidWrite(double speed) {
      set(speed / RobotMap.topspeed);
    }
  }

  // Motor Controllers
  private static VictorSP FRmotor = new VictorSP(RobotMap.FRmotor);
  private static VictorSP BRmotor = new VictorSP(RobotMap.BRmotor);
  private static VictorSP FLmotor = new VictorSP(RobotMap.FLmotor);
  private static VictorSP BLmotor = new VictorSP(RobotMap.BLmotor);

  // Motor encoders
  private static Encoder FRenc;
  private static Encoder BRenc;
  private static Encoder FLenc;
  private static Encoder BLenc;
  protected boolean encoders = true;

  // PID Controllers
  private static PIDController FRctrl;
  private static PIDController BRctrl;
  private static PIDController FLctrl;
  private static PIDController BLctrl;
  protected boolean pidcontrollers = true;

  public Drive () {    
    // Motor encoders
    try {
      FRenc = new Encoder(RobotMap.FRencH, RobotMap.FRencL, false, EncodingType.k4X);
      BRenc = new Encoder(RobotMap.BRencH, RobotMap.BRencL, false, EncodingType.k4X);
      FLenc = new Encoder(RobotMap.FLencH, RobotMap.FLencL, false, EncodingType.k4X);
      BLenc = new Encoder(RobotMap.BLencH, RobotMap.BLencL, false, EncodingType.k4X);
    } catch (UnsatisfiedLinkError ex ) {
      RobotMap.usePID = false;
      this.encoders = false;
      this.pidcontrollers = false;
      RobotModule.logger.error("Encoders OFFLINE");
      DriverStation.reportError("Encoders OFFLINE", false);
    } catch (Exception ex) {
      this.encoders = false;
      this.pidcontrollers = false;
      RobotModule.logger.error("Error initiating encoders.");
      DriverStation.reportError("Encoders OFFLINE", false);
    }

    if (this.pidcontrollers) {
      // PID Controllers
      FRctrl = new PIDController(RobotMap.driveP, RobotMap.driveI, RobotMap.driveD, FRenc, FRmotor);
      BRctrl = new PIDController(RobotMap.driveP, RobotMap.driveI, RobotMap.driveD, BRenc, BRmotor);
      FLctrl = new PIDController(RobotMap.driveP, RobotMap.driveI, RobotMap.driveD, FLenc, FLmotor);
      BLctrl = new PIDController(RobotMap.driveP, RobotMap.driveI, RobotMap.driveD, BLenc, BLmotor);
    }

    FRmotor.setInverted(false);
    BRmotor.setInverted(false);
    FLmotor.setInverted(true);
    BLmotor.setInverted(true);

    if (this.encoders) {
      // Right vs left
      FRenc.setReverseDirection(true);
      BRenc.setReverseDirection(true);
      FLenc.setReverseDirection(false);
      BLenc.setReverseDirection(false);
    }

    if (this.pidcontrollers) {
      // Not continuous.
      FRctrl.setContinuous(false);
      BRctrl.setContinuous(false);
      FLctrl.setContinuous(false);
      BLctrl.setContinuous(false);

      // Using the max speed as ratio
      FRctrl.setOutputRange(-RobotMap.topspeed, RobotMap.topspeed);
      BRctrl.setOutputRange(-RobotMap.topspeed, RobotMap.topspeed);
      FLctrl.setOutputRange(-RobotMap.topspeed, RobotMap.topspeed);
      BLctrl.setOutputRange(-RobotMap.topspeed, RobotMap.topspeed);

      // Safer to reset before use.
      FRctrl.reset();
      BRctrl.reset();
      FLctrl.reset();
      BLctrl.reset();
    }

    // Only enable the PID controllers if we are using them.
    if (this.pidcontrollers) {
      if (RobotMap.usePID) {
        FRctrl.enable();
        BRctrl.enable();
        FLctrl.enable();
        BLctrl.enable();
      } else {
        FRctrl.disable();
        BRctrl.disable();
        FLctrl.disable();
        BLctrl.disable();
      }
    }
  }

  public void dash_all() {
    SmartDashboard.putData("Front Left", FLmotor);
    SmartDashboard.putData("Front Right", FRmotor);
    SmartDashboard.putData("Back Left", BLmotor);
    SmartDashboard.putData("Back Right", BRmotor);
    if (this.pidcontrollers) {
      SmartDashboard.putData("Front Left Ctrl", FLctrl);
      SmartDashboard.putData("Front Right Ctrl", FRctrl);
      SmartDashboard.putData("Back Left Ctrl", BLctrl);
      SmartDashboard.putData("Back Right Ctrl", BRctrl);
    }
    if (this.encoders) {
      SmartDashboard.putData("FR Encoder", FRenc);
      SmartDashboard.putData("BR Encoder", BRenc);
      SmartDashboard.putData("FL Encoder", FLenc);
      SmartDashboard.putData("BL Encoder", BLenc);
    }
  }

  public void TankDrive(double rightspeed, double leftspeed) {
    if (RobotMap.usePID) {
      FRctrl.setSetpoint(rightspeed);
      BRctrl.setSetpoint(rightspeed);
      FLctrl.setSetpoint(leftspeed);
      BLctrl.setSetpoint(leftspeed);
    } else {
      FRmotor.set(rightspeed);
      BRmotor.set(rightspeed);
      FLmotor.set(leftspeed);
      BLmotor.set(leftspeed);
    }
  }

  public void Rotate (double clockwisespeed) {
    if (!RobotMap.usePID) {
      FRmotor.set(clockwisespeed);
      BRmotor.set(clockwisespeed);
      FLmotor.set(-clockwisespeed);
      BLmotor.set(-clockwisespeed);
    } else {
      FRctrl.setSetpoint(clockwisespeed);
      BRctrl.setSetpoint(clockwisespeed);
      FLctrl.setSetpoint(-clockwisespeed);
      BLctrl.setSetpoint(-clockwisespeed);
    }
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
    if (RobotMap.usePID) {
      FRctrl.setSetpoint(0.0);
      BRctrl.setSetpoint(0.0);
      FLctrl.setSetpoint(0.0);
      BLctrl.setSetpoint(0.0);
    }
  }
}

