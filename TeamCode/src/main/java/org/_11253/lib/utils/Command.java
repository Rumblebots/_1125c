package org._11253.lib.utils;

public class Command
{
    public Runnable active = new Runnable ()
    {
        @Override
        public void run ()
        {

        }
    };
    public Runnable inactive = new Runnable ()
    {
        @Override
        public void run ()
        {

        }
    };

    public Runnable getRunnable (boolean active)
    {
        return active ? this.active : this.inactive;
    }
}
