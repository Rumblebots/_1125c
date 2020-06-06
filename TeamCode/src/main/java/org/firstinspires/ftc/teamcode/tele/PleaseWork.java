/*
 * **
 *
 * Copyright (c) 2020
 * Copyright last updated on 6/6/20, 2:34 PM
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
import org._11253.lib.utils.Timed;
import org._11253.lib.utils.ToggleableCommand;
import org._11253.lib.utils.async.event.Events;

@TeleOp(name = "Telemetry Testing", group = "TeleOp")
public class PleaseWork extends org._11253.lib.op.TeleOp {
    Drivetrain drivetrain = new Drivetrain();

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
                        System.out.println("STARTED ON_START RUNNING, SCHEDULING EVENT");
                        Events.schedule(2000, 0, new Timed() {
                            @Override
                            public Runnable open() {
                                return new Runnable() {
                                    @Override
                                    public void run() {
                                        System.out.println("Opened!");
                                    }
                                };
                            }

                            @Override
                            public Runnable during() {
                                return super.during();
                            }

                            @Override
                            public Runnable close() {
                                return new Runnable() {
                                    @Override
                                    public void run() {
                                        System.out.println("Closed!");
                                    }
                                };
                            }
                        }, true);
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
