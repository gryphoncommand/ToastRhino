package frc.team3966.toastrhino.util;

import edu.wpi.first.wpilibj.*;

/**
 * A program for Xbox controller inputs
 *
 * @author Prasanth Yedlapalli, Olu Olorode
 * @version 1.0
 * @since Jan 26, 2014
 */
public class PS4Controller extends Joystick {

  public PS4Controller(int port) {
    super(port);
  }

  /**
   * Checks if button is pressed and what button is pressed
   * @param inputButton
   *   The button that is pressed
   * @return
   *   The state of the button
   */
  public boolean getButton(PS4Controller.Button inputButton) {
    return this.getRawButton(inputButton.value);
  }

  /**
   * Gets the input of D-Pad
   * @return
   *    Pressed or not
   */
  public double getDPad() {
    return this.getRawAxis(6);
  }

  /**
   * Gets the axis of each Analog stick
   * @param stickNumber
   *          The left (1) or right (2) analog stick
   * @param axisNumber
   *         The x (1) or y (2) axis
   * @return
   *     The direction of each Analog stick
   */
  public double getAxis(int stickNumber, int axisNumber) {
    int axes[] = {1, 2, 4, 5};
    int fAxis = 0;
    if (stickNumber == 1 && axisNumber == 1) {
      fAxis = axes[0];
    } else if (stickNumber == 1 && axisNumber == 2) {
      fAxis = axes[1];
    } else if (stickNumber == 2 && axisNumber == 1) {
      fAxis = axes[2];
    } else if (stickNumber == 2 && axisNumber == 2) {
      fAxis = axes[3];
    } else {
      return -69;
    }
    return this.getRawAxis(fAxis);

  }

  /**
   * The value of each button on controller
   */
  public static class Button {

    public final int value;
    public static final int kA_val = 1;
    public static final int kB_val = 2;
    public static final int kX_val = 3;
    public static final int kY_val = 4;
    public static final int kLB_val = 5;
    public static final int kRB_val = 6;
    public static final int kStart_val = 8;
    public static final int kBack_val = 7;
    public static final int kLeftClick_val = 9;
    public static final int kRightClick_val = 10;
    public static final Button A = new Button(kA_val);
    public static final Button B = new Button(kB_val);
    public static final Button X = new Button(kX_val);
    public static final Button Y = new Button(kY_val);
    public static final Button LB = new Button(kLB_val);
    public static final Button RB = new Button(kRB_val);
    public static final Button Start = new Button(kStart_val);
    public static final Button Back = new Button(kBack_val);
    public static final Button LeftClick = new Button(kLeftClick_val);
    public static final Button RightClick = new Button(kRightClick_val);

    private Button(int value) {                    // Value inputed is equal to value in Button class
      this.value = value;
    }
  }
}