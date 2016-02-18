package frc.team3966.toastrhino.subsystems;

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

  public ArmBallGrab() {
    
  }
  
  public void dash_all() {
    SmartDashboard.putData("SLmotor", SLmotor);
    SmartDashboard.putData("SRmotor", SRmotor);
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
