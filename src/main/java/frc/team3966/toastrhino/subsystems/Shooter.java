package frc.team3966.toastrhino.subsystems;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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

  private static CANTalon Gmotor = new CANTalon(RobotMap.Gmotor);

  public static AnalogPotentiometer gripper_pos = new AnalogPotentiometer(Sensors.gripper_pos, 2.0, -1.0);

  private static PIDController gripper = new PIDController(100.0, 0.0, 0.0, gripper_pos, Gmotor);

  public Shooter() {
    gripper.enable();
    gripper.setInputRange(-0.7, 1.0); //Strange but true
    gripper.setOutputRange(-1.0, 1.0);
    //gripper_pos.setPIDSourceType();
    gripper.reset();
    gripper.enable();
    Gmotor.setInverted(true);
  }

  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    //setDefaultCommand(new MySpecialCommand());
  }

  public void setGripperPos(double setpoint) {
    gripper.setSetpoint(setpoint);
    SmartDashboard.putData("Gripper", gripper);
    SmartDashboard.putNumber("Gmotor", Gmotor.getSetpoint());
    SmartDashboard.putNumber("Gripper Pos", gripper_pos.pidGet());
    
  }
}
