/*
 * **
 *
 * Copyright (c) 2020
 * Copyright last updated on 6/9/20, 5:49 PM
 * Part of the _1125c library
 *
 * **
 *
 * Permission is granted, free of charge, to any person obtaining
 * a copy of this software and / or any of it's related source code or
 * documentation ("Software") to copy, merge, modify, publish,
 * distribute, sublicense, and / or sell copies of Software.
 *
 * All Software included is provided in an "as is" state, without any
 * type or form of warranty. The Authors and Copyright Holders of this
 * piece of software, documentation, or source code waive all
 * responsibility and shall not be liable for any claim, damages, or
 * other forms of liability, regardless of the form it may take.
 *
 * Any form of re-distribution of Software is required to have this same
 * copyright notice included in any source files or forms of documentation
 * which have stemmed or branched off of the original Software.
 *
 * **
 *
 */

package org._11253.lib.robot.phys.assm;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import org._11253.lib.Global;
import org._11253.lib.motors.MotorPower;
import org._11253.lib.robot.phys.components.Motor;

/**
 * Template for a drive train.
 * <p>
 * init() must be called before the motors are used, and the
 * name of the motors can be changed via the strings
 *     <ul>
 *         <li>frontRightName</li>
 *         <li>frontLeftName</li>
 *         <li>backRightName</li>
 *         <li>backLeftName</li>
 *     </ul>
 * </p>
 * @author Colin Robertson
 */
public class Drivetrain extends Subsystem {
    public static Motor frontRight;
    public static Motor frontLeft;
    public static Motor backRight;
    public static Motor backLeft;

    public String frontRightName = "frontRight";
    public String frontLeftName = "frontLeft";
    public String backRightName = "backRight";
    public String backLeftName = "backLeft";

    public boolean isRound = false;

    public MotorPower getPower() {
        return new MotorPower() {
            double frontRightPower = frontRight.getPower();
            double frontLeftPower = frontLeft.getPower();
            double backRightPower = backRight.getPower();
            double backLeftPower = backLeft.getPower();
        };
    }

    public void setPower(MotorPower motorPower) {
        frontRight.setPower(motorPower.frontRightPower);
        frontLeft.setPower(motorPower.frontLeftPower);
        backRight.setPower(motorPower.backRightPower);
        backLeft.setPower(motorPower.backLeftPower);
    }

    public void init() {
        if (Global.getHwMap() == null) {
            throw new NullPointerException("Global hardware map has to be initialized before initializing the drive train.");
        }

        frontRight = new Motor(frontRightName);
        frontLeft = new Motor(frontLeftName);
        backRight = new Motor(backRightName);
        backLeft = new Motor(backLeftName);

        frontRight.isRound = isRound;
        frontLeft.isRound = isRound;
        backRight.isRound = isRound;
        backLeft.isRound = isRound;

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
    }
}
