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
    /**
     * The controller's map
     */
    public ControllerMap map;

    /**
     * The controller's internal gamepad
     */
    private Gamepad gamepad;

    /**
     * Create a new controller and a new controller map.
     *
     * @param gamepad the gamepad which the controller should be created off of.
     */
    public Controller (Gamepad gamepad)
    {
        this.gamepad = gamepad;
        map = new ControllerMap(gamepad);
    }

    /**
     * Get the left stick's x value
     *
     * @return left stick's x value
     */
    public final double getLeftX ()
    {
        return gamepad.left_stick_x;
    }

    /**
     * Get the left stick's y value
     *
     * @return left stick's y value
     */
    public final double getLeftY ()
    {
        return gamepad.left_stick_y;
    }

    /**
     * Get the right stick's x value
     *
     * @return right stick's x value
     */
    public final double getRightX ()
    {
        return gamepad.right_stick_x;
    }

    /**
     * Get the right stick's y value
     *
     * @return right stick's y value
     */
    public final double getRightY ()
    {
        return gamepad.right_stick_y;
    }

    /**
     * Get the right trigger's current value
     *
     * @return the right trigger's value
     */
    public final double getRightTrigger ()
    {
        return gamepad.right_trigger;
    }

    /**
     * Get the left trigger's current value
     *
     * @return the left trigger's value
     */
    public final double getLeftTrigger ()
    {
        return gamepad.left_trigger;
    }

    /**
     * Boolean whether or not button is pressed down.
     *
     * @return whether the button is pressed down or not (true = yes, false = no)
     */
    public final boolean getA ()
    {
        return gamepad.a;
    }

    /**
     * Boolean whether or not button is pressed down.
     *
     * @return whether the button is pressed down or not (true = yes, false = no)
     */
    public final boolean getB ()
    {
        return gamepad.b;
    }

    /**
     * Boolean whether or not button is pressed down.
     *
     * @return whether the button is pressed down or not (true = yes, false = no)
     */
    public final boolean getX ()
    {
        return gamepad.x;
    }

    /**
     * Boolean whether or not button is pressed down.
     *
     * @return whether the button is pressed down or not (true = yes, false = no)
     */
    public final boolean getY ()
    {
        return gamepad.y;
    }

    /**
     * Boolean whether or not button is pressed down.
     *
     * @return whether the button is pressed down or not (true = yes, false = no)
     */
    public final boolean getDpadUp ()
    {
        return gamepad.dpad_up;
    }

    /**
     * Boolean whether or not button is pressed down.
     *
     * @return whether the button is pressed down or not (true = yes, false = no)
     */
    public final boolean getDpadRight ()
    {
        return gamepad.dpad_right;
    }

    /**
     * Boolean whether or not button is pressed down.
     *
     * @return whether the button is pressed down or not (true = yes, false = no)
     */
    public final boolean getDpadDown ()
    {
        return gamepad.dpad_down;
    }

    /**
     * Boolean whether or not button is pressed down.
     *
     * @return whether the button is pressed down or not (true = yes, false = no)
     */
    public final boolean getDpadLeft ()
    {
        return gamepad.dpad_left;
    }

    /**
     * Boolean whether or not button is pressed down.
     *
     * @return whether the button is pressed down or not (true = yes, false = no)
     */
    public final boolean getRightBumper ()
    {
        return gamepad.right_bumper;
    }

    /**
     * Boolean whether or not button is pressed down.
     *
     * @return whether the button is pressed down or not (true = yes, false = no)
     */
    public final boolean getLeftBumper ()
    {
        return gamepad.left_bumper;
    }

    /**
     * Boolean whether or not button is pressed down.
     *
     * @return whether the button is pressed down or not (true = yes, false = no)
     */
    public final boolean getStart ()
    {
        return gamepad.start;
    }

    /**
     * Boolean whether or not button is pressed down.
     *
     * @return whether the button is pressed down or not (true = yes, false = no)
     */
    public final boolean getSelect ()
    {
        return gamepad.guide;
    }
}
