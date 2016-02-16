package frc.team3966.toastrhino.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team3966.toastrhino.RobotMap;
import frc.team3966.toastrhino.RobotModule;

/**
 *
 */
public class SwitchPID extends Command {

  NetworkTable GRIPtable;

  public SwitchPID() {
    requires(RobotModule.drive);
  }

  // Called just before this Command runs the first time
  protected void initialize() {
    if (RobotMap.usePID) {
      RobotMap.usePID = false;
      SmartDashboard.putBoolean("PID Control?", RobotMap.usePID);
      RobotModule.logger.info("PID is OFF");
      DriverStation.reportError("PID is now OFF", false);
    } else {
      RobotMap.usePID = true;
      SmartDashboard.putBoolean("PID Control?", RobotMap.usePID);
      RobotModule.logger.info("PID is ON");
      DriverStation.reportError("PID is now ON", false);
    }
  }

  // Called repeatedly when this Command is scheduled to run
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  protected boolean isFinished() {
    return true;
  }

  // Called once after isFinished returns true
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  protected void interrupted() {
  }
}
