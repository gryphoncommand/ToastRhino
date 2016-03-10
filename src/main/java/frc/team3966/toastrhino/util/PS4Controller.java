package frc.team3966.toastrhino.util;

import edu.wpi.first.wpilibj.Joystick;

/**
 * PS4 controller wrapper for a joystick.
 * @author Matthew.Lythgoe
 */
public class PS4Controller extends Joystick {
    
    //TODO
    public static final int SQUARE = 1;
    public static final int X = 2;
    public static final int CIRCLE = 3;
    public static final int TRIANGLE = 4;
    public static final int L1 = 5;
    public static final int R1 = 6;
    public static final int L2 = 7;
    public static final int R2 = 8;
    public static final int SHARE = 9;
    public static final int OPTIONS = 10;
    public static final int L3 = 11;
    public static final int R3 = -12;
    public static final int PS = 13;
    public static final int PAD = 14;
    
    //TODO
    public static final int LEFT_X = 0;
    public static final int LEFT_Y = 1;
    public static final int RIGHT_X = 2;
    public static final int RIGHT_Y = 5;
    public static final int L2_AXIS = 3;
    public static final int R2_AXIS = 4;
    // These two are not buttons but POV directions.
    public static final int D_PAD_X = -1;
    public static final int D_PAD_Y = -1;
    
    
    public PS4Controller(int port) {
        super(port);
    }

    /**
     * Read the value of the right joystick's X axis.
     * @return the value of the right joystick's X axis.
     */
    public double getRightStickX() {
        return getRawAxis(RIGHT_X);
    }

    /**
     * Read the value of the right joystick's Y axis.
     * @return the value of the right joystick's Y axis.
     */
    public double getRightStickY() {
        return getRawAxis(RIGHT_Y);
    }

    /**
     * Read the value of the left joystick's X axis.
     * @return the value of the left joystick's X axis.
     */
    public double getLeftStickX() {
        return getRawAxis(LEFT_X);
    }

    /**
     * Read the value of the left joystick's Y axis.
     * @return the value of the left joystick's Y axis.
     */
    public double getLeftStickY() {
        return getRawAxis(LEFT_Y);
    }
    
    /**
     * Read the value of the d-pad's X axis.
     * @return the value of the d-pad's X axis.
     */
    public double getDPadX() {
        return getRawAxis(D_PAD_X);
    }
    
    /**
     * Read the value of the d-pad's Y axis.
     * @return the value of the d-pad's Y axis.
     */
    public double getDPadY() {
        return getRawAxis(D_PAD_Y);
    }

    /**
     * Read the state of the Square button.
     * @return the state of the Square button.
     */
    public boolean getSquareButton() {
        return getRawButton(SQUARE);
    }

    /**
     * Read the state of the X button.
     * @return the state of the X button.
     */
    public boolean getXButton() {
        return getRawButton(X);
    }

    /**
     * Read the state of the Circle button.
     * @return the state of the Circle button.
     */
    public boolean getCircleButton() {
        return getRawButton(CIRCLE);
    }

    /**
     * Read the state of the Triangle button.
     * @return the state of the Triangle button.
     */
    public boolean getTriangleButton() {
        return getRawButton(TRIANGLE);
    }

    /**
     * Read the state of the R1 button.
     * @return the state of the R1 button.
     */
    public boolean getR1Button() {
        return getRawButton(R1);
    }

    /**
     * Read the state of the L1 button.
     * @return the state of the L1 button.
     */
    public boolean getL1Button() {
        return getRawButton(L1);
    }
    
    /**
     * Read the state of the R2 button.
     * @return the state of the R2 button.
     */
    public boolean getR2Button() {
        return getRawButton(R2);
    }

    /**
     * Read the state of the L2 button.
     * @return the state of the L2 button.
     */
    public boolean getL2Button() {
        return getRawButton(L2);
    }
    
    /**
     * Read the state of the R3 button.
     * @return the state of the R3 button.
     */
    public boolean getR3Button() {
        return getRawButton(R3);
    }
    
    /**
     * Read the state of the L3 button.
     * @return the state of the L3 button.
     */
    public boolean getL3Button() {
        return getRawButton(L3);
    }

    /**
     * Read the state of the share button.
     * @return the state of the share button.
     */
    public boolean getShareButton() {
        return getRawButton(SHARE);
    }

    /**
     * Read the state of the options button.
     * @return the state of the options button.
     */
    public boolean getOptionsButton() {
        return getRawButton(OPTIONS);
    } 
    
    /**
     * Read the state of the PS button.
     * @return the state of the PS button.
     */
    public boolean getPSButton() {
        return getRawButton(PS);
    }
    
    /**
     * Read the state of the Pad button.
     * @return the state of the Pad button.
     */
    public boolean getPadButton() {
        return getRawButton(PAD);
    }
    
    /**
     * Read the value of the right joystick's Y axis.
     * @return the value of the right joystick's Y axis.
     */
    public double getRightTriggerAxis() {
        return getRawAxis(R2_AXIS);
    }
    
    /**
     * Read the value of the right joystick's Y axis.
     * @return the value of the right joystick's Y axis.
     */
    public double getLeftTriggerAxis() {
        return getRawAxis(L2_AXIS);
    }
}
