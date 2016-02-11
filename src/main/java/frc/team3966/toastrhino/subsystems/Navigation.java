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

  public void initNavX() {
    try {
      /* Communicate w/navX-MXP via the MXP SPI Bus.                                     */
      /* Alternatively:  I2C.Port.kMXP, SerialPort.Port.kMXP or SerialPort.Port.kUSB     */
      /* See http://navx-mxp.kauailabs.com/guidance/selecting-an-interface/ for details. */
      navx = new AHRS(SPI.Port.kMXP);
      navx.resetDisplacement();
      SmartDashboard.putBoolean("NavX Online", true);
    } catch (RuntimeException ex ) {
      SmartDashboard.putBoolean("NavX Online", false);
      DriverStation.reportError("Error instantiating navX-MXP:  " + ex.getMessage(), true);
    }
  }
  
  public boolean isMoving() {
    if (navx != null) return navx.isMoving();
    else {
      DriverStation.reportError("Tried .isMoving() without navX sensor!", false);
      return false;
    }
  }
  
  public double getYaw() {
    if (navx != null) return navx.getYaw();
    else {
      DriverStation.reportError("Tried .getYaw() without navX sensor!", false);
      return 0;
    }
  }
  
  public double getAngle() {
    if (navx != null) return navx.getAngle();
    else {
      DriverStation.reportError("Tried .getCompassHeading() without navX sensor!", false);
      return 180;
    }
  }
  
  public double getDisplacementX() {
    if (navx != null) return navx.getDisplacementX();
    else {
      DriverStation.reportError("Tried .getDisplacementX() without navX sensor!", false);
      return 0.0;
    }
  }
  
  public double getDisplacementY() {
    if (navx != null) return navx.getDisplacementY();
    else {
      DriverStation.reportError("Tried .getDisplacementY() without navX sensor!", false);
      return 0.0;
    }
  }
  
  public double getDisplacementZ() {
    if (navx != null) return navx.getDisplacementZ();
    else {
      DriverStation.reportError("Tried .getDisplacementZ() without navX sensor!", false);
      return 0.0;
    }
  }
  
  public float getTempC() {
    if (navx != null) return navx.getTempC();
    else {
      DriverStation.reportError("Tried .getTempC() without navX sensor!", false);
      return 25.0f;
    }
  }
  
  public double getDisplacementTotal() {
    double X = 0.0, Y = 0.0, Z = 0.0;
    X = this.getDisplacementX();
    Y = this.getDisplacementY();
    Z = this.getDisplacementZ();
    
    return Math.sqrt(X * X + Y * Y + Z * Z);
  }
}
