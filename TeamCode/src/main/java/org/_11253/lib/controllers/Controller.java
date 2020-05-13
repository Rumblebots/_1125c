package org._11253.lib.controllers;

import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * Wrapper class for FTC's default Gamepad
 * <p>
 * This provides some additional functionality over the default gamepad.
 * </p>
 */
public class Controller
{
    public ControllerMap map;
    private Gamepad gamepad;

    public Controller (Gamepad gamepad)
    {
        this.gamepad = gamepad;
        map = new ControllerMap(gamepad);
    }

    public double getLeftX ()
    {
        return gamepad.left_stick_x;
    }

    public double getLeftY ()
    {
        return gamepad.left_stick_y;
    }

    public double getRightX ()
    {
        return gamepad.right_stick_x;
    }

    public double getRightY ()
    {
        return gamepad.right_stick_y;
    }

    public double getRightTrigger ()
    {
        return gamepad.right_trigger;
    }

    public double getLeftTrigger ()
    {
        return gamepad.left_trigger;
    }

    public boolean getA ()
    {
        return gamepad.a;
    }

    public boolean getB ()
    {
        return gamepad.b;
    }

    public boolean getX ()
    {
        return gamepad.x;
    }

    public boolean getY ()
    {
        return gamepad.y;
    }

    public boolean getDpadUp ()
    {
        return gamepad.dpad_up;
    }

    public boolean getDpadRight ()
    {
        return gamepad.dpad_right;
    }

    public boolean getDpadDown ()
    {
        return gamepad.dpad_down;
    }

    public boolean getDpadLeft ()
    {
        return gamepad.dpad_left;
    }

    public boolean getRightBumper ()
    {
        return gamepad.right_bumper;
    }

    public boolean getLeftBumper ()
    {
        return gamepad.left_bumper;
    }

    public boolean getStart ()
    {
        return gamepad.start;
    }

    public boolean getSelect ()
    {
        return gamepad.guide;
    }
}
