package org._11253.lib.utils.math;

/**
 * Basic comparator class.
 * <p>
 * This is used to compare numbers, such as 'is this
 * number close enough to this number?' and so on and so forth.
 * It's fairly simple to use, or at least I'd hope so.
 * </p>
 */
public class Comparator
{
    /**
     * The tolerance (+/-) which will still return true.
     */
    private double tolerance;

    /**
     * Basic, argumentless constructor. If nothing is provided, the
     * tolerance is automatically assumed to be 1.0
     */
    public Comparator ()
    {
        this(1.0);
    }

    /**
     * Basic, with argument, constructor.
     *
     * @param tolerance the tolerance which should be used
     */
    public Comparator (double tolerance)
    {
        this.tolerance = tolerance;
    }

    /**
     * Compares two numbers.
     *
     * @param one the first number
     * @param two the second number
     * @return whether or not the numbers are comparable based on tolerance.
     */
    public boolean compare (double one, double two)
    {
        return (two <= one + tolerance) && (two >= one - tolerance);
    }

    /**
     * Returns the current tolerance value.
     *
     * @return tolerance
     */
    public double getTolerance ()
    {
        return this.tolerance;
    }

    /**
     * Sets the current tolerance value.
     * <p>
     * If possible, it's suggested that this isn't used, but rather,
     * the constructor with a param (tolerance) is used, just to simplify things
     * a little bit, but who the hell am I to tell you what to do?
     * </p>
     *
     * @param tolerance the new tolerance.
     */
    public void setTolerance (double tolerance)
    {
        this.tolerance = tolerance;
    }
}
