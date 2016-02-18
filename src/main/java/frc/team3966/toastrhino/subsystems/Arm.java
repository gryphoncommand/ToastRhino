package frc.team3966.toastrhino.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team3966.toastrhino.RobotMap;

/**
 *
 */
public class Arm extends Subsystem {

  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  
  VictorSP SLmotor = new VictorSP(RobotMap.SLmotor);
  VictorSP SRmotor = new VictorSP(RobotMap.SRmotor);
  
  VictorSP KickMotor = new VictorSP(RobotMap.KickMotor);
  VictorSP Amotor = new VictorSP(RobotMap.Amotor);

  public Arm() {
    
  }
  
  public void dash_all() {
    SmartDashboard.putData("SLmotor", SLmotor);
    SmartDashboard.putData("SRmotor", SRmotor);
  }

  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    //setDefaultCommand(new MySpecialCommand());
  }
  
  public void Smotors(double speed) {
    SLmotor.set(-speed);
    SRmotor.set(speed);
  }
  
  public void Amotor(double speed) {
    Amotor.set(speed);
  }
  
  public void KickMotor(double speed) {
    KickMotor.set(speed);
  }
}
