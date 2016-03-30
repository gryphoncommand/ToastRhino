package frc.team3966.toastrhino.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.AnalogTrigger;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team3966.toastrhino.RobotMap;
import frc.team3966.toastrhino.RobotModule;

/**
 *
 */
public class ArmAim extends Subsystem {

  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  //arm "encoder"
  Potentiometer pot = new AnalogPotentiometer(2, 100, -20);
  
  private boolean enablePID = false;
  
  boolean AnalogInputs = false;
  AnalogInput HallBackInput;

  PIDController armHeight;

  class armMotor extends VictorSP {
    public armMotor(int channel) {
      super(channel);
    }
    @Override
    public void pidWrite(double output) {
      super.set(output);
    }
  }

  VictorSP Amotor = new armMotor(RobotMap.Amotor);

  public ArmAim() {
    try {
      HallBackInput = new AnalogInput(3);
      AnalogInputs = true;
    } catch (UnsatisfiedLinkError e) {
      RobotModule.logger.warn("Hall Effect no Link");;
      AnalogInputs = false;
    }
    Amotor.setInverted(false);
    try {
      armHeight = new PIDController(0.15, 0.0, 0.01, pot, Amotor);
      pot.setPIDSourceType(PIDSourceType.kDisplacement); // Can only be displacement!
      armHeight.setAbsoluteTolerance(1.0);
      enablePID = true;
    } catch (Error e) {
      RobotModule.logger.error("PIDController Could not be enabled");
      enablePID = false;
    }
  }

  public void dash_all() {
    SmartDashboard.putData("Amotor", Amotor);
    if (AnalogInputs) {
      SmartDashboard.putData("Back Hall", HallBackInput);
    }
    SmartDashboard.putNumber("Arm Potentiometer", pot.pidGet());
    if (armHeight != null) SmartDashboard.putData("ArmPID", armHeight);
  }

  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    //setDefaultCommand(new MySpecialCommand());
  }
  
  public double getPot() {
    return pot.get();
  }

  public void setAmotor(double speed) {
    Amotor.set(speed);
  }
  
  public void setHeightRelative(double speed) {
    if (armHeight != null && enablePID) {
      if (armHeight.getSetpoint() < 0.0) { // Change modes to rate
        armHeight.setSetpoint(0.0);
      } else if (armHeight.getError() > 30.0) {
        // Let the PID controller catch back up.
      } else {
        armHeight.setSetpoint(armHeight.getSetpoint() + (speed / 2));
      }
    }
    else if (enablePID == false) {
      setAmotor(speed);
    }
  }
  
  public void setHeightAbsolute(double setpoint) {
    if (armHeight != null && enablePID) {
      if (!armHeight.isEnabled()) { // Change modes to displacement
        armHeight.enable();
      }
      armHeight.setSetpoint(setpoint);
    }
    else if (enablePID == false) {
      RobotModule.logger.warn("Tried to set arm absolute height without PID");
      setAmotor(0.0);
    }
  }
  
  public void enablePID(boolean enabled) {
    enablePID = enabled;
    if (enablePID) {
      if (armHeight != null) armHeight.enable();
    } else {
      if (armHeight != null) armHeight.disable();
    }
  }

  public void doNothing() {
    if (armHeight != null && enablePID) {
      // Stops arm by setting PID to current position
      armHeight.setSetpoint(pot.pidGet());
    } else {
      setAmotor(0.0);
    }
  }
}
