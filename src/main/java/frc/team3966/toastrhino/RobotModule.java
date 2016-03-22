package frc.team3966.toastrhino;

//import frc.team3966.toastrhino.commands.ArmControl;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team3966.toastrhino.commands.ChaseBall;
import frc.team3966.toastrhino.commands.JumpScare;
import frc.team3966.toastrhino.commands.KickOut;
import frc.team3966.toastrhino.commands.MoveForward;
import frc.team3966.toastrhino.commands.MoveToBen;
import frc.team3966.toastrhino.commands.MoveToCade;
import frc.team3966.toastrhino.commands.ResetNavigation;
import frc.team3966.toastrhino.commands.AimControl;
import frc.team3966.toastrhino.commands.ArmControl;
import frc.team3966.toastrhino.commands.Square;
import frc.team3966.toastrhino.commands.SwitchPID;
import frc.team3966.toastrhino.commands.TankDrive;
import frc.team3966.toastrhino.subsystems.Drive;
import frc.team3966.toastrhino.subsystems.Navigation;
import frc.team3966.toastrhino.subsystems.Sensors;
import frc.team3966.toastrhino.subsystems.ArmAim;
import frc.team3966.toastrhino.subsystems.ArmBallGrab;
import frc.team3966.toastrhino.subsystems.ArmKicker;
import jaci.openrio.toast.lib.log.Logger;
import jaci.openrio.toast.lib.module.IterativeModule;

public class RobotModule extends IterativeModule {

  public static Logger logger;

  public static Drive drive;
  public static ArmAim armAim;
  public static ArmKicker armKicker;
  public static ArmBallGrab armBallGrab;
  public static Sensors sensors;
  public static Navigation navigation;
  public static OI oi;

  NetworkTable table;

  Command autonomousCommand;
  Command TankDrive;
  Command ManualArmControl;
  Command KickOut;
  Command resetNav;
  Command switchPID;
  Command aimControl;
  SendableChooser chooser;

  @Override
  public String getModuleName() {
    return "ToastRhino";
  }

  @Override
  public String getModuleVersion() {
    return "0.0.2"; // A version higher than 0.0.1????
  }

  @Override
  public void robotInit() {
    logger = new Logger("ToastRhino", Logger.ATTR_DEFAULT);
    logger.info("robotInit() started");
    oi = new OI();

    // Subsystems
    drive = new Drive();
    armAim = new ArmAim();
    armKicker = new ArmKicker();
    armBallGrab = new ArmBallGrab();
    navigation = new Navigation();
    sensors = new Sensors();

    // Commands
    TankDrive = new TankDrive();
    ManualArmControl = new ArmControl();
    KickOut = new KickOut();
    resetNav = new ResetNavigation();
    switchPID = new SwitchPID();
    resetNav.setRunWhenDisabled(true);
    aimControl = new AimControl();
    RobotModule.oi.FireButton.whenPressed(KickOut);

    // Autonomous options
    chooser = new SendableChooser();
    chooser.addObject("Move Forward", new MoveForward());
    //chooser.addObject("Chase Ball", new ChaseBall());
    //chooser.addObject("Jump Scared", new JumpScare());
    //chooser.addObject("Move To Ben", new MoveToBen());
    //chooser.addObject("Move To Cade", new MoveToCade());
    //chooser.addObject("Move in Square", new Square());
    chooser.addObject("Aim Control", aimControl);
    SmartDashboard.putData("Auto mode", chooser);

    SmartDashboard.putData("Reset Navigation", resetNav);
    //SmartDashboard.putData("Switch PID", switchPID);
    SmartDashboard.putBoolean("Initialized", true);
    SmartDashboard.putBoolean("DB/LED 0", true);

    navigation.initNavX();

    logger.info("robotInit() finished");
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit(){
    if (TankDrive != null) TankDrive.cancel();
    drive.doNothing();
    logger.info("Disabled.");
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
    SmartDashboard.putData("Reset Navigation", resetNav);
    sensors.dash_all();
  }


  /**
   * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
   * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
   * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
   * below the Gyro
   *
   * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
   * or additional comparisons to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    autonomousCommand = (Command) chooser.getSelected();

    /* String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
      switch(autoSelected) {
      case "My Auto":
        autonomousCommand = new MyAutoCommand();
        break;
      case "Default Auto":
      default:
        autonomousCommand = new ExampleCommand();
        break;
      } */

    // schedule the autonomous command (example)
    if (autonomousCommand != null) autonomousCommand.start();
    if (TankDrive != null) TankDrive.cancel();
    logger.info("Autonomous.");
  }

  /**
   * This function is called periodically during autonomous
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
    sensors.dash_all();
  }

  @Override
  public void teleopInit() {
    logger.info("Teleoperated.");
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (autonomousCommand != null) autonomousCommand.cancel();
    if (TankDrive != null) TankDrive.start();
    if (ManualArmControl != null ) ManualArmControl.start();
  }

  /**
   * This function is called periodically during operator control
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    sensors.dash_all();
  }

  /**
   * This function is called periodically during test mode
   */
  @Override
  public void testPeriodic() {
    LiveWindow.run();
    sensors.dash_all();
  }
}
