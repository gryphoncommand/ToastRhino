package frc.team3966.toastrhino;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.team3966.toastrhino.util.PS4Controller;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  public PS4Controller controller = new PS4Controller(RobotMap.cstick);
  public PS4Controller controller2 = new PS4Controller(RobotMap.cstick2);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());
  //public Button switchPIDbutton = new JoystickButton(controller, 10);
  
  public Button FireButton = new JoystickButton(controller, PS4Controller.X);
  public Button FireButton2 = new JoystickButton(controller2, PS4Controller.X);

  //public Button RotateToGoalButton = new JoystickButton(controller2, PS4Controller.SQUARE);
  
  public Button ArmBrakeButton = new JoystickButton(controller, PS4Controller.TRIANGLE);
  public Button ArmBrakeButton2 = new JoystickButton(controller2, PS4Controller.TRIANGLE);
  public Button ArmGroundButton = new JoystickButton(controller, PS4Controller.CIRCLE);
  public Button ArmGroundButton2 = new JoystickButton(controller2, PS4Controller.CIRCLE);
  public Button ArmAutoAim = new JoystickButton(controller, PS4Controller.SQUARE);
  public Button ArmAutoAim2 = new JoystickButton(controller2, PS4Controller.SQUARE);
  public Button ArmAutoAimHorizontal = new JoystickButton(controller, PS4Controller.PAD);
  public Button ArmAutoAimHorizontal2 = new JoystickButton(controller2, PS4Controller.PAD);
  
  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released  and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());

}

