package org._11253.lib.utils;

public class Command implements CommandCore
{
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

    public final Runnable getRunnable (boolean state)
    {
        return state ? active() : inactive();
    }
}
