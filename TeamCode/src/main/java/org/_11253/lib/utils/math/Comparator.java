package org._11253.lib.utils.math;

public class Comparator
{
    private double tolerance;

    public Comparator ()
    {
        this(1.0);
    }

    public Comparator (double tolerance)
    {
        this.tolerance = tolerance;
    }

    public boolean compare (double one, double two)
    {
        return (two <= one + tolerance) && (two >= one - tolerance);
    }

    public double getTolerance ()
    {
        return this.tolerance;
    }

    public void setTolerance (double tolerance)
    {
        this.tolerance = tolerance;
    }
}
