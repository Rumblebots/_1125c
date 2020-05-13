package org._11253.lib.op;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org._11253.lib.Global;
import org._11253.lib.utils.jrep.ListWrapper;

import java.util.ArrayList;

/**
 * Template LinearOpMode which extends the default (FTC) LinearOpMode.
 * <p>
 * This should be used by user op modes as a very core basis.
 * </p>
 * TODO try to see if there's a better method of fucking with the lists -
 * maybe see about making them objects so the code here is smaller and there's
 * no internal functions?
 */
public class Template extends LinearOpMode
{
    /**
     * List of Runnables to be run before the start of the
     * operation mode. This is before waitForStart() finishes;
     */
    public ListWrapper<Runnable> beforeStart = new ListWrapper<>(new ArrayList<Runnable>());

    /**
     * List of Runnables to be run after waitForStart() finishes.
     */
    public ListWrapper<Runnable> onStart = new ListWrapper<>(new ArrayList<Runnable>());

    /**
     * List of Runnables to run at the start of the loop.
     */
    public ListWrapper<Runnable> onStartRun = new ListWrapper<>(new ArrayList<Runnable>());

    /**
     * List of Runnables to run as the main portion of the loop.
     */
    public ListWrapper<Runnable> run = new ListWrapper<>(new ArrayList<Runnable>());

    /**
     * List of Runnables to run after the loop finishes.
     */
    public ListWrapper<Runnable> onFinishRun = new ListWrapper<>(new ArrayList<Runnable>());

    /**
     * List of Runnables to be run after the loop finishes being, well,
     * looped.
     */
    public ListWrapper<Runnable> onFinish = new ListWrapper<>(new ArrayList<Runnable>());

    /**
     * Run all of the runnables contained in a list.
     *
     * @param list List<'Runnable'> which should be run.
     */
    public final void runList (ListWrapper<Runnable> list)
    {
        for (Runnable runnable : list.list)
        {
            runnable.run();
        }
    }

    /**
     * Internal function, just don't mess with it and you're good.
     */
    private void fBeforeStart ()
    {
        runList(beforeStart);
    }

    /**
     * Internal function, just don't mess with it and you're good.
     */
    private void fOnStart ()
    {
        runList(onStart);
    }

    /**
     * Internal function, just don't mess with it and you're good.
     */
    private void fOnStartRun ()
    {
        runList(onStartRun);
    }

    /**
     * Internal function, just don't mess with it and you're good.
     */
    private void fRun ()
    {
        runList(run);
    }

    /**
     * Internal function, just don't mess with it and you're good.
     */
    private void fOnFinishRun ()
    {
        runList(onFinishRun);
    }

    /**
     * Internal function, just don't mess with it and you're good.
     */
    private void onFinish ()
    {
        runList(onFinish);
    }

    /**
     * Base / template code that every op mode uses.
     * <p>
     * This code should NOT be overridden by the user, as everything they could possibly need to do
     * can be done externally.
     * </p>
     * <p>
     * Note that this framework was primarily designed with TeleOp in mind, so it makes sense
     * why this might seem a little awkward to use for auton. However, it's still pretty easy to do so -
     * all you have to do is EXACTLY what you'd do otherwise. If you can't understand how to do that,
     * I'm not entirely sure how or why you're reading this in the first place.
     * </p>
     */
    @Override
    public final void runOpMode ()
    {
        Global.setHwMap(hardwareMap);
        Global.setTelem(telemetry);

        fBeforeStart();

        waitForStart();

        fOnStart();

        while (opModeIsActive())
        {
            fOnStartRun();

            fRun();

            fOnFinishRun();
        }

        fOnFinishRun();
    }
}
