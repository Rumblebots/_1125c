package org._11253.lib.robot.phys.assm;

import org._11253.lib.utils.gen.id.Id;
import org._11253.lib.utils.gen.id.IdRegister;

/**
 * Abstract class with the (very small) amount of things required for a subsystem.
 * That's really all there is to it.
 * <p>
 *     While creating a sub-system, making sure that components do not have
 *     a value set until the init function runs.
 * </p>
 */
public abstract class Subsystem
{
    /*
     * The Id of the subsystem, used for doing super cool stuff, of course.
     */
    public Id subsystemId;

    /*
     * Whenever a new subsystem is created, get a new ID.
     */
    public Subsystem ()
    {
        subsystemId = IdRegister.getNextId();
    }

    /**
     * Required function for all sub-systems.
     * This is where any declared components should have a value assigned to them.
     */
    public abstract void init ();
}
