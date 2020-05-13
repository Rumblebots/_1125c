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
    public boolean isRound;
    DcMotor dcMotorComponent;

    public Motor (String name)
    {
        super(DcMotor.class, name);
        dcMotorComponent = (DcMotor) component;
    }

    public double getPower ()
    {
        return dcMotorComponent.getPower();
    }

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
