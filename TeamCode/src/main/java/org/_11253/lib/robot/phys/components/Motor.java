package org._11253.lib.robot.phys.components;

import com.qualcomm.robotcore.hardware.DcMotor;
import org._11253.lib.utils.math.Math;

/**
 * An implementation of the default DcMotor.
 * Adds some additional functionality over it, including
 * motor smoothing / rounding (curving the next motor power based on what it is currently
 * and what the next target value is).
 */
public class Motor extends Component
{
    /**
     * A boolean which determines whether or not the
     * values of the component should be adjusted very
     * suddenly or more slowly, based on averaging.
     */
    public boolean isRound;
    DcMotor dcMotorComponent;

    public Motor (String name)
    {
        super(DcMotor.class, name);
        dcMotorComponent = (DcMotor) component;
    }

    /**
     * Gets the power from the motor component.
     *
     * @return the motor component's value.
     */
    public double getPower ()
    {
        return dcMotorComponent.getPower();
    }

    /**
     * Sets the motor component's power based on input.
     * <p>
     * isRound can toggle whether or not it should be rounded.
     * </p>
     *
     * @param power the new power level
     */
    public void setPower (double power)
    {
        if (!isRound)
        {
            dcMotorComponent.setPower(power);
        }
        else
        {
            dcMotorComponent.setPower(Math.average(power, getPower()));
        }
    }
}
