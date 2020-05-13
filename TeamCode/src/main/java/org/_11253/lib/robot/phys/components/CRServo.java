package org._11253.lib.robot.phys.components;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import org._11253.lib.utils.math.Math;

/**
 * An implementation of the default CRServo.
 * Adds some additional functionality over it, including
 * CRServo smoothing / rounding (curving the next CRServo power based on what it is currently
 * and what the next target value is).
 */
public class CRServo extends Component
{
    public boolean isRound;
    com.qualcomm.robotcore.hardware.CRServo crServoComponent;

    public CRServo (String name)
    {
        super(com.qualcomm.robotcore.hardware.CRServo.class, name);
        crServoComponent = (com.qualcomm.robotcore.hardware.CRServo) component;
    }

    public double getPower ()
    {
        return crServoComponent.getPower();
    }

    public void setPower (double power)
    {
        if (!isRound)
        {
            crServoComponent.setPower(power);
        }
        else
        {
            crServoComponent.setPower(Math.average(power, getPower()));
        }
    }

    public DcMotorSimple.Direction getDirection ()
    {
        return crServoComponent.getDirection();
    }

    public void setDirection (DcMotorSimple.Direction direction)
    {
        crServoComponent.setDirection(direction);
    }
}
