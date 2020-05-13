package org._11253.lib.utils;

/**
 * Basic, and really low level, interface for commands.
 * Just ensures that the Command itself isn't missing anything
 * really stupid and simple, like the getRunnable function, which
 * is required for the way I have CommandMaps set up.
 */
public interface CommandCore
{
    /**
     * What's run when ACTIVE state is determined.
     *
     * @return runnable, for the active state.
     */
    Runnable active ();

    /**
     * What's run when INACTIVE state is determined.
     *
     * @return runnable, for the inactive state.
     */
    Runnable inactive ();

    /**
     * Gets whichever runnable is appropriate based on
     * the boolean input.
     *
     * @param state true / false, active / inactive
     * @return runnable, either active or inactive runnable
     */
    Runnable getRunnable (boolean state);
}
