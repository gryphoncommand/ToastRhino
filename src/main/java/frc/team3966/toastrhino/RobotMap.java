package frc.team3966.toastrhino;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // Front Right FR
  // Back Left BL
  public static final int FRmotor = 0;
  public static final int BRmotor = 1;
  public static final int FLmotor = 2;
  public static final int BLmotor = 3;

  public static final int SLmotor = 4;
  public static final int SRmotor = 5;
  public static final int Gmotor = 6;
  public static final int Kmotor = 7;

  // Joysticks
  public static int cstick = 0;

  // Encoder full speed
  public static double topspeed = 1000;

  // Gyro DIO port
  public static int gyroport = 0;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;
}
