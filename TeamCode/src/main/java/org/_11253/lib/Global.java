package org._11253.lib;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Contains a couple things (hardware map,telem, and gamepads) to be used anywhere.
 * <p>
 * The HardwareMap must be set before anything else happens.
 * TODO try to make this happen 100% of the time by using a template op mode
 * </p>
 * <p>
 * The Telemetry (really hard to spell, damn) needs to be set before telem can be used
 * anywhere throughout the program.
 * TODO try to make this happen 100% of the time by using a template op mode
 * </p>
 * <p>
 * Both of the gamepads are required for LinearOpModes in Tele-Op, but not in auton.
 * Not setting them in auton won't do anything. Once again, very cool, I know.
 * TODO try to make this happen in Tele-Op op modes by using a template op mode
 * </p>
 */
public class Global
{
    /**
     * Hardware map which should be used globally.
     * This has a getter and setter method so you shouldn't actually be touching this at any point.
     */
    private static HardwareMap hwMap;

    /**
     * Telem to be used globally. Not really needed, but it's
     * cool or something, and makes a lot of things quite a bit easier.
     */
    private static Telemetry telem;

    /**
     * Global instance of gamepad1
     */
    private static Gamepad gamepad1;

    /**
     * Global instance of gamepad2
     */
    private static Gamepad gamepad2;

    /**
     * Getter method for gamepad1
     *
     * @return gamepad1
     */
    public static Gamepad getGamepad1 ()
    {
        return gamepad1;
    }

    /**
     * Setter method for gamepad1
     *
     * @param gamepad1 new gamepad
     */
    public static void setGamepad1 (Gamepad gamepad1)
    {
        Global.gamepad1 = gamepad1;
    }

    /**
     * Getter method for gamepad2
     *
     * @return gamepad2
     */
    public static Gamepad getGamepad2 ()
    {
        return gamepad2;
    }

    /**
     * Setter method for gamepad2
     *
     * @param gamepad2 new gamepad
     */
    public static void setGamepad2 (Gamepad gamepad2)
    {
        Global.gamepad2 = gamepad2;
    }

    /**
     * Getter method for telem
     *
     * @return global instance of telem
     */
    public static Telemetry getTelem ()
    {
        return telem;
    }

    /**
     * Setter method for telem
     *
     * @param telem new telem
     */
    public static void setTelem (Telemetry telem)
    {
        Global.telem = telem;
    }

    /**
     * Getter method for hardware map
     *
     * @return instance of hardware map
     */
    public static HardwareMap getHwMap ()
    {
        return hwMap;
    }

    /**
     * Setter method for hardware map
     *
     * @param hwMap new hardware map
     */
    public static void setHwMap (HardwareMap hwMap)
    {
        Global.hwMap = hwMap;
    }
}
