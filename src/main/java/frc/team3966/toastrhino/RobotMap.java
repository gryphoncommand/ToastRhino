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
  
  // All 4 motors encoders
  public static final int FRencH = 0;
  public static final int BRencH = 4;
  public static final int FRencL = 1;
  public static final int BRencL = 5;
  public static final int FLencH = 2;
  public static final int BLencH = 6;
  public static final int FLencL = 3;
  public static final int BLencL = 7;
  
  // Drive PID settings
  public static boolean usePID = false;
  public static final double driveP = 1.0;
  public static final double driveI = 0.0;
  public static final double driveD = 0.01;

  // Arm and arm
  public static final int SLmotor = 4; // Arm left.
  public static final int SRmotor = 5; // Arm right.
  public static final int Amotor = 6; // Arm up/down.
  public static final int KickMotor = 7; // Push ball out.
  
  // Arm height encoder
  public static final int AencH = 8;
  public static final int AencL = 9;

  // Joysticks
  public static final int cstick = 0;

  // Encoder full speed
  public static double topspeed = 2500;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;
}
