package frc.team3966.toastrhino.subsystems;

import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team3966.toastrhino.RobotMap;

/**
 *
 */
public class Shooter extends Subsystem {

  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  @SuppressWarnings("unused")
  private static TalonSRX SLmotor = new TalonSRX(RobotMap.SLmotor);
  @SuppressWarnings("unused")
  private static TalonSRX SRmotor = new TalonSRX(RobotMap.SRmotor);

  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    //setDefaultCommand(new MySpecialCommand());
  }
}
