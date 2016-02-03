package frc.team3966.toastrhino;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team3966.toastrhino.commands.ChaseBall;
import frc.team3966.toastrhino.commands.Shoot;
import frc.team3966.toastrhino.commands.TankDrive;
import frc.team3966.toastrhino.subsystems.Drive;
import frc.team3966.toastrhino.subsystems.Sensors;
import frc.team3966.toastrhino.subsystems.Shooter;
import jaci.openrio.toast.lib.log.Logger;
import jaci.openrio.toast.lib.module.IterativeModule;

public class RobotModule extends IterativeModule {

  public static Logger logger;

  public static final Drive drive = new Drive();
  public static final Shooter shooter = new Shooter();
  public static final Sensors sensors = new Sensors();
  public static OI oi;

  NetworkTable table;

  Command autonomousCommand;
  Command TankDrive;
  Command ShootCommand;
  SendableChooser chooser;

  @Override
  public String getModuleName() {
    return "ToastRhino";
  }

  @Override
  public String getModuleVersion() {
    return "0.0.1";
  }

  @Override
  public void robotInit() {
    logger = new Logger("ToastRhino", Logger.ATTR_DEFAULT);
    logger.info("robotInit() started");
    oi = new OI();
    TankDrive = new TankDrive();
    ShootCommand = new Shoot();

    chooser = new SendableChooser();
    chooser.addObject("Chase Ball", new ChaseBall());
    SmartDashboard.putData("Auto mode", chooser);

    SmartDashboard.putBoolean("Initialized", true);
    SmartDashboard.putBoolean("DB/LED 0", true);

    logger.info("robotInit(); finished");
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit(){
    //if (TankDrive != null) TankDrive.cancel();
    drive.doNothing();
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
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
  }

  /**
   * This function is called periodically during autonomous
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to 
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (autonomousCommand != null) autonomousCommand.cancel();
    if (TankDrive != null) TankDrive.start();
    if (ShootCommand != null ) ShootCommand.start();
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
  }
}
