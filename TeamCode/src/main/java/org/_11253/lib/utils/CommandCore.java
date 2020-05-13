package org._11253.lib.utils;

public interface CommandCore
{
    Runnable active ();

    Runnable inactive ();

    Runnable getRunnable (boolean state);
}
