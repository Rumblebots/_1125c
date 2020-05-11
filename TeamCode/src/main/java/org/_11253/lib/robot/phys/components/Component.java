package org._11253.lib.robot.phys.components;

import org._11253.lib.Global;

/**
 * Template class for components, used internally in org._11253.lib.robot.phys.components package
 */
public class Component
{
    Object component;

    /**
     * Creates a new component, takes a class (component type) as well as
     * a name (hardware name) for the component.
     * @param c the class name (ie. DcMotor.class)
     * @param name the name of the device, as it appears on the HardwareMap
     */
    public Component (Class<?> c, String name)
    {
        component = Global.getHwMap().get(c, name);
    }
}
