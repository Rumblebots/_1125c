package org._11253.lib.op;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org._11253.lib.Global;

/**
 * Template LinearOpMode which extends the default (FTC) LinearOpMode.
 * <p>
 *     This should be used by user op modes as a very core basis.
 * </p>
 */
public class Template extends LinearOpMode
{
    /**
     * Run after onStart but before the event loop.
     */
    public void load ()
    {

    }

    /**
     * Method that should be used externally from user's OpMode.
     * <p>
     *     This should be overridden by the user's op mode.
     *     The code in here is executed during the game loop.
     * </p>
     */
    public void run ()
    {

    }

    /**
     * Code that should be run before the while (opModeIsActive()) portion.
     * <p>
     *     This should also be overridden by user code. It's not required for the
     *     program to function, however.
     * </p>
     */
    public void onStart ()
    {

    }

    /**
     * Code that should be run after the while (opModeIsActive()) portion is over.
     * <p>
     *     This should also be overridden by user code. It's not required for the
     *     program to function, however.
     * </p>
     */
    public void onStop ()
    {

    }

    /**
     * Code that should be run at the very start of the loop.
     * <p>
     *     This should also be overridden by user code. It's not required for the
     *     program to function, however.
     * </p>
     */
    public void onStartLoop ()
    {

    }

    /**
     * Code that should be run at the very end of the loop.
     * <p>
     *     This should also be overridden by user code. It's not required for the
     *     program to function, however.
     * </p>
     */
    public void onStopLoop ()
    {

    }

    /**
     * Base / template code that every op mode uses.
     * <p>
     *     This code should NOT be overridden by the user, as everything they could possibly need to do
     *     can be done externally.
     * </p>
     */
    @Override
    public void runOpMode ()
    {
        Global.setHwMap(hardwareMap);
        Global.setTelem(telemetry);

        onStart();

        load();

        while (opModeIsActive())
        {
            onStartLoop();

            run();

            onStopLoop();
        }

        onStop();
    }
}
