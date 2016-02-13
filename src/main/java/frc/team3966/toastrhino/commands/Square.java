package frc.team3966.toastrhino.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team3966.toastrhino.RobotModule;
import frc.team3966.toastrhino.subsystems.Navigation;

public class Square extends Command {
	public static final double constantMotorSpeed = .5f; // for just driving
	public static final double constantAngleMotorSpeed = .5f; // for turning angles
	public static final double sideLength = 2; // 2 meter square
	private int currentSide = 0;

	public Square() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(RobotModule.navigation);
		requires(RobotModule.drive);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		SmartDashboard.putBoolean("square finished", false);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double slop = .1f;
		double degSlop = 1f;
		double x = RobotModule.navigation.getDisplacementX();
		double y = RobotModule.navigation.getDisplacementY();
		double lspeed = 0;
		double rspeed = 0;

		double curYaw = RobotModule.navigation.getYaw();

		double desireDeg = currentSide * 90;
		if (desireDeg > 180) {
			// e.g. convert 270 -> -90
			desireDeg -= 360;
		}
		SmartDashboard.putNumber("Desired Yaw", desireDeg);
		SmartDashboard.putNumber("Current Side", currentSide);

		// yes this is ugly repeated code.  could be refactored but wanted to keep it as stupid as possible
		
		if (currentSide == 0) {
			if (y <= sideLength) {
				// we need to keep moving forward
				rspeed = constantMotorSpeed;
				lspeed = constantMotorSpeed;
			} else {
				// we've come to the turn
				desireDeg = 90;
				if((desireDeg - curYaw) > degSlop){
					// need to turn right
					rspeed = -constantAngleMotorSpeed;
					lspeed = constantAngleMotorSpeed;
				}
				else
				{
					// we may have overshot it a bit, but don't worry about that for now, just say we've made the turn and go straight
					currentSide++;
					rspeed = constantMotorSpeed;
					lspeed = constantMotorSpeed;					
				}				
			}
		}
		else if(currentSide == 1){
			if (x <= sideLength) {
				// we need to keep moving forward
				rspeed = constantMotorSpeed;
				lspeed = constantMotorSpeed;
			} else {
				// we've come to the turn
				desireDeg = 180;
				if((desireDeg - curYaw) > degSlop){
					// need to turn right
					rspeed = -constantAngleMotorSpeed;
					lspeed = constantAngleMotorSpeed;
				}
				else
				{
					// we may have overshot it a bit, but don't worry about that for now, just say we've made the turn and go straight
					currentSide++;
					rspeed = constantMotorSpeed;
					lspeed = constantMotorSpeed;					
				}				
			}
		}
		else if(currentSide == 2){
			if (y >= 0) {
				// we need to keep moving forward
				rspeed = constantMotorSpeed;
				lspeed = constantMotorSpeed;
			} else {
				// we've come to the turn
				desireDeg = -90;
				if((desireDeg - curYaw) > degSlop){
					// need to turn right
					rspeed = -constantAngleMotorSpeed;
					lspeed = constantAngleMotorSpeed;
				}
				else
				{
					// we may have overshot it a bit, but don't worry about that for now, just say we've made the turn and go straight
					currentSide++;
					rspeed = constantMotorSpeed;
					lspeed = constantMotorSpeed;					
				}				
			}
		}
		else if(currentSide == 3){
			if (x >= 0) {
				// we need to keep moving forward
				rspeed = constantMotorSpeed;
				lspeed = constantMotorSpeed;
			} else {
				// we've come to the turn (to get re-positioned)
				desireDeg = 0;
				if((desireDeg - curYaw) > degSlop){
					// need to turn right
					rspeed = -constantAngleMotorSpeed;
					lspeed = constantAngleMotorSpeed;
				}
				else
				{
					// we may have overshot it a bit, but don't worry about that for now, just say we've made the turn and be done
					currentSide++;
					rspeed = 0;
					lspeed = 0;		

					SmartDashboard.putBoolean("square finished", true);
				}				
			}
		}

		RobotModule.drive.TankDrive(rspeed, lspeed);

	}


	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return currentSide > 3;
	}

	// Called once after isFinished returns true
	protected void end() {
		RobotModule.drive.doNothing();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		RobotModule.drive.doNothing();
	}
}
