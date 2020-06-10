/*
 * **
 *
 * Copyright (c) 2020
 * Copyright last updated on 6/9/20, 10:12 PM
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
import org._11253.lib.controllers.ControllerMap;
import org._11253.lib.motors.MotorPower;
import org._11253.lib.robot.phys.assm.Drivetrain;
import org._11253.lib.robot.phys.components.sensors.Distance;
import org._11253.lib.utils.Timed;
import org._11253.lib.utils.ToggleableCommand;
import org._11253.lib.utils.WhileCommand;
import org._11253.lib.utils.async.event.Events;
import org._11253.lib.utils.telem.Telemetry;

/**
 * Demonstrates / example-s how a bunch of stuff works.
 * <p>
 * This is a somewhat real-world instance of how
 * this library would be used in a legitimate context.
 * </p>
 */
@TeleOp(name = "Testing & Demonstration", group = "TeleOp")
public class PleaseWork extends org._11253.lib.op.TeleOp {
    Drivetrain drivetrain = new Drivetrain();
    Distance distance;

    private ToggleableCommand driveCommand = new ToggleableCommand() {
        @Override
        public Runnable active() {
            return new Runnable() {
                @Override
                public void run() {
                    drivetrain.setPower(new MotorPower(
                            controller1.getRightY(),
                            controller1.getLeftY(),
                            controller1.getRightY(),
                            controller1.getLeftY()
                    ));
                }
            };
        }

        @Override
        public Runnable inactive() {
            return new Runnable() {
                @Override
                public void run() {
                    drivetrain.setPower(new MotorPower());
                }
            };
        }
    };

    public PleaseWork() {
        onStart.add(
                new Runnable() {
                    @Override
                    public void run() {
                        drivetrain.init();
                    }
                },
                new Runnable() {
                    @Override
                    public void run() {
                        mapDriveControls();
                    }
                },
                new Runnable() {
                    @Override
                    public void run() {
                        mapEventControls();
                    }
                },
                new Runnable() {
                    @Override
                    public void run() {
                        distance = new Distance("distanceSensorRight");
                        Events.schedule(1300, 0, new Timed() {
                            @Override
                            public Runnable open() {
                                return new Runnable() {
                                    @Override
                                    public void run() {
                                        Telemetry.addData(
                                                "_1125c_DISTANCE_SENSOR_TEST",
                                                "Distance sensor reading (right)",
                                                distance.map.inch() + ""
                                        );
                                    }
                                };
                            }
                        }, true);
                    }
                },
                new Runnable() {
                    @Override
                    public void run() {
                        WhileCommand testWhileCommand = new WhileCommand() {
                            @Override
                            public boolean check() {
                                return distance.map.inch() > 10;
                            }

                            @Override
                            public Runnable active() {
                                return new Runnable() {
                                    @Override
                                    public void run() {
                                        System.out.println("Still running!");
                                    }
                                };
                            }
                        };
                        testWhileCommand.scheduleWhileCommand();
                    }
                }
        );
    }

    private void mapDriveControls() {
        controller1.map.bind(ControllerMap.States.STICK, driveCommand);
    }

    private void mapEventControls() {
        controller1.map.bind(ControllerMap.States.DPAD_UP, new ToggleableCommand() {
            @Override
            public Runnable active() {
                return new Runnable() {
                    @Override
                    public void run() {
                        Events.schedule(1000, new Timed() {
                            @Override
                            public Runnable open() {
                                return new Runnable() {
                                    @Override
                                    public void run() {
                                        disable();
                                        driveCommand.disable();
                                        drivetrain.setPower(new MotorPower(
                                                0.25,
                                                0.25,
                                                0.25,
                                                0.25
                                        ));
                                    }
                                };
                            }

                            @Override
                            public Runnable close() {
                                return new Runnable() {
                                    @Override
                                    public void run() {
                                        enable();
                                        driveCommand.enable();
                                        drivetrain.setPower(new MotorPower());
                                    }
                                };
                            }
                        });
                    }
                };
            }
        });
        controller1.map.bind(ControllerMap.States.DPAD_DOWN, new ToggleableCommand() {
            @Override
            public Runnable active() {
                return new Runnable() {
                    @Override
                    public void run() {
                        Events.schedule(1000, new Timed() {
                            @Override
                            public Runnable open() {
                                return new Runnable() {
                                    @Override
                                    public void run() {
                                        disable();
                                        driveCommand.disable();
                                        drivetrain.setPower(new MotorPower(
                                                -0.25,
                                                -0.25,
                                                -0.25,
                                                -0.25
                                        ));
                                    }
                                };
                            }

                            @Override
                            public Runnable close() {
                                return new Runnable() {
                                    @Override
                                    public void run() {
                                        enable();
                                        driveCommand.enable();
                                        drivetrain.setPower(new MotorPower());
                                    }
                                };
                            }
                        });
                    }
                };
            }
        });
    }
}
