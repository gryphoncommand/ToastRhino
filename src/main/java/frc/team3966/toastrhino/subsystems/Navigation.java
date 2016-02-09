package frc.team3966.toastrhino.subsystems;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Navigation extends Subsystem {
  
  private static AHRS navx;
  
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    //setDefaultCommand(new MySpecialCommand());
    SmartDashboard.putBoolean("NavX Online", false);
  }

  public static void initNavX() {
    try {
      /* Communicate w/navX-MXP via the MXP SPI Bus.                                     */
      /* Alternatively:  I2C.Port.kMXP, SerialPort.Port.kMXP or SerialPort.Port.kUSB     */
      /* See http://navx-mxp.kauailabs.com/guidance/selecting-an-interface/ for details. */
      navx = new AHRS(SPI.Port.kMXP);
      SmartDashboard.putBoolean("NavX Online", true);
    } catch (RuntimeException ex ) {
      SmartDashboard.putBoolean("NavX Online", false);
      DriverStation.reportError("Error instantiating navX-MXP:  " + ex.getMessage(), true);
    }
  }
  
  public static boolean isMoving() {
    if (navx != null) return navx.isMoving();
    else {
      DriverStation.reportError("Tried .isMoving() without navX sensor!", false);
      return false;
    }
  }
}
