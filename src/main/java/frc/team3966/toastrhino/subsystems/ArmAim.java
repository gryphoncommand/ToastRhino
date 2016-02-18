package frc.team3966.toastrhino.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team3966.toastrhino.RobotMap;

/**
 *
 */
public class ArmAim extends Subsystem {

  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  
  VictorSP Amotor = new VictorSP(RobotMap.Amotor);
  
  Encoder Aenc = new Encoder(RobotMap.AencH, RobotMap.AencL);

  public ArmAim() {
    
  }
  
  public void dash_all() {
    SmartDashboard.putData("SLmotor", Amotor);
    SmartDashboard.putData("ArmHeight", Aenc);
  }

  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    //setDefaultCommand(new MySpecialCommand());
  }
  
  public void Amotor(double speed) {
    Amotor.set(speed);
  }
}
