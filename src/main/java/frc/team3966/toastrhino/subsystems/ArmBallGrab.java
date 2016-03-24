package frc.team3966.toastrhino.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogTrigger;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team3966.toastrhino.RobotMap;
import frc.team3966.toastrhino.RobotModule;

/**
 *
 */
public class ArmBallGrab extends Subsystem {

  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  VictorSP SLmotor = new VictorSP(RobotMap.SLmotor);
  VictorSP SRmotor = new VictorSP(RobotMap.SRmotor);

  boolean Scounters = false;

  AnalogInput SLinput;
  AnalogInput SRinput;
  AnalogTrigger SLtrigger;
  AnalogTrigger SRtrigger;
  Counter SLcounter;
  Counter SRcounter;
  
  
  public ArmBallGrab() {
    try {
      SLinput = new AnalogInput(0);
      SRinput = new AnalogInput(1);
      SLtrigger = new AnalogTrigger(SLinput);
      SRtrigger = new AnalogTrigger(SRinput);
      SLcounter = new Counter(SLtrigger);
      SRcounter = new Counter(SRtrigger);
      Scounters = true;
    } catch (UnsatisfiedLinkError e) {
      RobotModule.logger.warn("Shooter Counters no Link");;
    }
  }

  public void dash_all() {
    SmartDashboard.putData("SLmotor", SLmotor);
    SmartDashboard.putData("SRmotor", SRmotor);
    if (Scounters) {
      SmartDashboard.putData("SLinput", SLinput);
      SmartDashboard.putData("SRinput", SRinput);
      SmartDashboard.putData("SLcounter", SLcounter);
      SmartDashboard.putData("SRcounter", SRcounter);
    }
  }

  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    //setDefaultCommand(new MySpecialCommand());
  }

  public void grab(double speed) {
    SLmotor.set(-speed);
    SRmotor.set(speed);
  }

  public void doNothing() {
    grab(0.0);
  }
}
