/*
 * **
 *
 * Copyright (c) 2020
 * Copyright last updated on 6/5/20, 4:17 PM
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

package org.firstinspires.ftc.teamcode.tele;

import org._11253.lib.controllers.ControllerMap;
import org._11253.lib.drives.Tank;
import org._11253.lib.utils.Command;
import org._11253.lib.utils.telem.Telemetry;

public class PleaseWork extends Tank {
    private Telemetry telem = new Telemetry();

    private void testing() {
        controller1.map.bind(
                ControllerMap.States.DPAD_DOWN,
                new Command() {
                    @Override
                    public Runnable active() {
                        return new Runnable() {
                            @Override
                            public void run() {
                                telem.addData("lastPressed", "Last pressed", "DPAD_DOWN");
                            }
                        };
                    }
                }
        );
        controller1.map.bind(
                ControllerMap.States.DPAD_UP,
                new Command() {
                    @Override
                    public Runnable active() {
                        return new Runnable() {
                            @Override
                            public void run() {
                                telem.addData("lastPressed", "Last pressed", "DPAD_UP");
                            }
                        };
                    }
                }
        );
        controller1.map.bind(
                ControllerMap.States.DPAD_LEFT,
                new Command() {
                    @Override
                    public Runnable active() {
                        return new Runnable() {
                            @Override
                            public void run() {
                                telem.addData("lastPressed", "Last pressed", "DPAD_LEFT");
                            }
                        };
                    }
                }
        );
    }

    @Override
    public void initOp() {
        testing(); // hopefully bind stuff to controller
    }
}
