package frc.team3966.toastrhino.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team3966.toastrhino.RobotMap;
import frc.team3966.toastrhino.RobotModule;

/**
 *
 */
public class ArmAim extends Subsystem {

  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  Encoder Aenc = new Encoder(RobotMap.AencH, RobotMap.AencL);
  
  PIDController armHeight;
  
  class armMotor extends VictorSP {
    public armMotor(int channel) {
      super(channel);
    }
    @Override
    public void pidWrite(double output) {
      RobotModule.armAim.setAmotor(output / (RobotMap.ArmHeightMax / 4.0));
    }
  }
  
  VictorSP Amotor = new armMotor(RobotMap.Amotor);

  public ArmAim() {
    armHeight = new PIDController(10.0, 0.1, 1.0, Aenc, Amotor);
  }
  
  public void dash_all() {
    SmartDashboard.putData("Amotor", Amotor);
    SmartDashboard.putData("ArmHeight", Aenc);
    SmartDashboard.putData("ArmPID", armHeight);
  }

  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    //setDefaultCommand(new MySpecialCommand());
  }
  
  protected void setAmotor(double speed) {
    Amotor.set(speed);
  }
  
  public void setHeight(double speed) {
    Amotor.set(speed);
  }
}
