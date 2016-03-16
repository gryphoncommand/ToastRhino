package frc.team3966.toastrhino.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogTrigger;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team3966.toastrhino.RobotMap;

/**
 *
 */
public class ArmBallGrab extends Subsystem {

  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  
  VictorSP SLmotor = new VictorSP(RobotMap.SLmotor);
  VictorSP SRmotor = new VictorSP(RobotMap.SRmotor);
  
  AnalogInput SLinput = new AnalogInput(0);
  AnalogInput SRinput = new AnalogInput(1);
  
  AnalogTrigger SLtrigger = new AnalogTrigger(SLinput);
  AnalogTrigger SRtrigger = new AnalogTrigger(SRinput);
  
  Counter SLcounter = new Counter(SLtrigger);
  Counter SRcounter = new Counter(SRtrigger);

  public ArmBallGrab() {
    
  }
  
  public void dash_all() {
    SmartDashboard.putData("SLmotor", SLmotor);
    SmartDashboard.putData("SRmotor", SRmotor);
    SmartDashboard.putData("SLinput", SLinput);
    SmartDashboard.putData("SRinput", SRinput);
    SmartDashboard.putData("SLcounter", SLcounter);
    SmartDashboard.putData("SRcounter", SRcounter);
  }

  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    //setDefaultCommand(new MySpecialCommand());
  }
  
  public void grab(double speed) {
    SLmotor.set(-speed);
    SRmotor.set(speed);
  }
}
