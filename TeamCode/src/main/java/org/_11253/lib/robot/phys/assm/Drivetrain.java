package org._11253.lib.robot.phys.assm;

import org._11253.lib.Global;
import org._11253.lib.motors.MotorPower;
import org._11253.lib.robot.phys.components.Motor;

/**
 * Template for a drive train.
 * <p>
 *     init() must be called before the motors are used, and the
 *     name of the motors can be changed via the strings
 *     <ul>
 *         <li>frontRightName</li>
 *         <li>frontLeftName</li>
 *         <li>backRightName</li>
 *         <li>backLeftName</li>
 *     </ul>
 * </p>
 */
public class Drivetrain
{
    public static Motor frontRight;
    public static Motor frontLeft;
    public static Motor backRight;
    public static Motor backLeft;

    public String frontRightName = "frontRight";
    public String frontLeftName = "frontLeft";
    public String backRightName = "backRight";
    public String backLeftName = "backLeft";

    public boolean isRound = false;

    public MotorPower getPower ()
    {
        return new MotorPower()
        {
            double frontRightPower = frontRight.getPower();
            double frontLeftPower = frontLeft.getPower();
            double backRightPower = backRight.getPower();
            double backLeftPower = backLeft.getPower();
        };
    }

    public void init ()
    {
        if (Global.getHwMap() == null)
            throw new NullPointerException("Global hardware map has to be initialized before initializing the drive train.");

        frontRight = new Motor(frontRightName);
        frontLeft = new Motor(frontLeftName);
        backRight = new Motor(backRightName);
        backLeft = new Motor(backLeftName);

        frontRight.isRound = isRound;
        frontLeft.isRound = isRound;
        backRight.isRound = isRound;
        backLeft.isRound = isRound;
    }

    public void setPower (MotorPower motorPower)
    {
        frontRight.setPower(motorPower.frontRightPower);
        frontLeft.setPower(motorPower.frontLeftPower);
        backRight.setPower(motorPower.backRightPower);
        backLeft.setPower(motorPower.backLeftPower);
    }
}
