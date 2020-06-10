/*
 * **
 *
 * Copyright (c) 2020
 * Copyright last updated on 6/10/20, 6:30 PM
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

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org._11253.lib.drives.Meccanum;
import org._11253.lib.utils.Timed;
import org._11253.lib.utils.async.event.StringEvents;

@TeleOp(name = "Testing & Demonstration", group = "TeleOp")
public class PleaseWork extends Meccanum {
    public PleaseWork() {
        onStart.add(
                new Runnable() {
                    @Override
                    public void run() {
                        StringEvents.schedule("test", 1000, 1000, new Timed() {
                            @Override
                            public Runnable close() {
                                return new Runnable() {
                                    @Override
                                    public void run() {
                                        System.out.println("Test!");
                                    }
                                };
                            }
                        }, false);
                        StringEvents.schedule("test", 1000, 1000, new Timed() {
                            @Override
                            public Runnable close() {
                                return new Runnable() {
                                    @Override
                                    public void run() {
                                        System.out.println("Repeating 1 Test!");
                                    }
                                };
                            }
                        }, true);
                        StringEvents.schedule("test", 2000, 1000, new Timed() {
                            @Override
                            public Runnable close() {
                                return new Runnable() {
                                    @Override
                                    public void run() {
                                        System.out.println("Repeating 2 Test!");
                                    }
                                };
                            }
                        }, true);
                        StringEvents.schedule("test2", 1000, 1000, new Timed() {
                            @Override
                            public Runnable close() {
                                return new Runnable() {
                                    @Override
                                    public void run() {
                                        System.out.println("Repeating 1 Test!");
                                    }
                                };
                            }
                        }, true);
                        StringEvents.schedule("test2", 2000, 1000, new Timed() {
                            @Override
                            public Runnable close() {
                                return new Runnable() {
                                    @Override
                                    public void run() {
                                        System.out.println("Repeating 2 Test!");
                                    }
                                };
                            }
                        }, true);
                    }
                }
        );
    }
}
