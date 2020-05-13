package org._11253.lib.utils;

/**
 * Standardized implementation of CommandCore.
 * <p>
 * For use in Command mapping (think ControllerMap).
 * I really can't think of too many other purposes, but yeah,
 * you get what I'm saying.
 * </p>
 */
public class Command implements CommandCore
{
    /**
     * What's run when ACTIVE state is determined.
     *
     * @return runnable, for the active state.
     */
    public Runnable active ()
    {
        return new Runnable()
        {
            @Override
            public void run ()
            {

            }
        };
    }

    /**
     * What's run when INACTIVE state is determined.
     *
     * @return runnable, for the inactive state.
     */
    public Runnable inactive ()
    {
        return new Runnable()
        {
            @Override
            public void run ()
            {

            }
        };
    }

    /**
     * Returns whichever runnable is appropriate based on the input.
     *
     * @param state true / false, active / inactive
     * @return active or inactive runnable
     */
    public final Runnable getRunnable (boolean state)
    {
        return state ? active() : inactive();
    }
}
