package org._11253.lib;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Contains a couple things (hardware map,telem, and gamepads) to be used anywhere.
 * <p>
 *     The HardwareMap must be set before anything else happens.
 *     TODO try to make this happen 100% of the time by using a template op mode
 * </p>
 * <p>
 *     The Telemetry (really hard to spell, damn) needs to be set before telem can be used
 *     anywhere throughout the program.
 *     TODO try to make this happen 100% of the time by using a template op mode
 * </p>
 * <p>
 *     Both of the gamepads are required for LinearOpModes in Tele-Op, but not in auton.
 *     Not setting them in auton won't do anything. Once again, very cool, I know.
 *     TODO try to make this happen in Tele-Op op modes by using a template op mode
 * </p>
 */
public class Global
{
    private static HardwareMap hwMap;

    private static Telemetry telem;

    private static Gamepad gamepad1;

    private static Gamepad gamepad2;

    public static Gamepad getGamepad1 ()
    {
        return gamepad1;
    }

    public static void setGamepad1 (Gamepad gamepad1)
    {
        Global.gamepad1 = gamepad1;
    }

    public static Gamepad getGamepad2 ()
    {
        return gamepad2;
    }

    public static void setGamepad2 (Gamepad gamepad2)
    {
        Global.gamepad2 = gamepad2;
    }

    public static Telemetry getTelem ()
    {
        return telem;
    }

    public static void setTelem (Telemetry telem)
    {
        Global.telem = telem;
    }

    public static HardwareMap getHwMap ()
    {
        return hwMap;
    }

    public static void setHwMap (HardwareMap hwMap)
    {
        Global.hwMap = hwMap;
    }
}
