package frc.team3966.toastrhino.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team3966.toastrhino.RobotMap;

/**
 *
 */
public class ArmKicker extends Subsystem {

  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  
  VictorSP KickMotor = new VictorSP(RobotMap.KickMotor);

  public ArmKicker() {
  }
  
  public void dash_all() {
    SmartDashboard.putData("KickMotor", KickMotor);
  }

  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    //setDefaultCommand(new MySpecialCommand());
  }

  public void KickMotor(double speed) {
    KickMotor.set(speed);
  }
}
