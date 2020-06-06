/*
 * **
 *
 * Copyright (c) 2020
 * Copyright last updated on 6/5/20, 9:54 PM
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
import org._11253.lib.drives.Tank;
import org._11253.lib.motors.MotorPower;
import org._11253.lib.utils.Command;
import org._11253.lib.utils.Timed;
import org._11253.lib.utils.async.event.Events;

@TeleOp(name = "Telemetry Testing", group = "TeleOp")
public class PleaseWork extends Tank {
    private boolean doesUserInputMatter = true;

    public PleaseWork() {
        super();
        addToOnStart();
        onStart.add(
                new Runnable() {
                    @Override
                    public void run() {
                        Events.schedule(100, 200, new Timed() {
                            @Override
                            public Runnable close() {
                                return new Runnable() {
                                    @Override
                                    public void run() {
                                        mapDelaySystem();
                                    }
                                };
                            }
                        });
                    }
                },
                new Runnable() {
                    @Override
                    public void run() {
                        Events.schedule(100, 100, new Timed() {
                            @Override
                            public Runnable close() {
                                return new Runnable() {
                                    @Override
                                    public void run() {
                                        mapOtherControls();
                                    }
                                };
                            }
                        });
                    }
                }
        );
    }

    private void mapOtherControls() {
//        controller1.map.unbind(ControllerMap.States.STICK);
        controller1.map = new ControllerMap(gamepad1);
//        controller1.map.bind(ControllerMap.States.STICK, new Command() {
//            @Override
//            public Runnable active() {
//                return new Runnable() {
//                    @Override
//                    public void run() {
//                        if (doesUserInputMatter) {
//                            telem.addData("_1125c_DRIVING",
//                                    "Motors engaged?",
//                                    " ",
//                                    "True");
//                            drivetrain.setPower(new MotorPower(
//                                    controller1.getRightY() / divisor.getCurrentGear(),
//                                    controller1.getLeftY() / divisor.getCurrentGear(),
//                                    controller1.getRightY() / divisor.getCurrentGear(),
//                                    controller1.getLeftY() / divisor.getCurrentGear()
//                            ));
//                        } else {
//                            doesUserInputMatter = true;
//                        }
//                    }
//                };
//            }
//
//            @Override
//            public Runnable inactive() {
//                return new Runnable() {
//                    @Override
//                    public void run() {
//                        telem.addData("_1125c_DRIVING",
//                                "Motors engaged?",
//                                " ",
//                                "True");
//                        drivetrain.setPower(new MotorPower());
//                    }
//                };
//            }
//        });
    }
//        onStart.add(
//                new Runnable() {
//                    @Override
//                    public void run() {
//                        mapDelaySystem();
//                    }
//                });

    private void mapDelaySystem() {
        controller1.map.bind(ControllerMap.States.DPAD_RIGHT, new Command() {
            boolean canBeBound = true;

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
                                        drivetrain.setPower(new MotorPower(0.25, 0.25, 0.25, 0.25));
                                        doesUserInputMatter = false;
                                    }
                                };
                            }

                            @Override
                            public Runnable close() {
                                return new Runnable() {
                                    @Override
                                    public void run() {
                                        drivetrain.setPower(new MotorPower());
                                        canBeBound = true;
                                        doesUserInputMatter = true;
                                    }
                                };
                            }
                        });
                        canBeBound = false;
                    }
                };
            }
        });
        controller1.map.bind(ControllerMap.States.DPAD_LEFT, new Command() {
            boolean canBeBound = true;

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
                                        drivetrain.setPower(new MotorPower(-0.25, -0.25, -0.25, -0.25));
                                        doesUserInputMatter = false;
                                    }
                                };
                            }

                            @Override
                            public Runnable close() {
                                return new Runnable() {
                                    @Override
                                    public void run() {
                                        drivetrain.setPower(new MotorPower());
                                        canBeBound = true;
                                        doesUserInputMatter = true;
                                    }
                                };
                            }
                        });
                        canBeBound = false;
                    }
                };
            }
        });
    }

    private void addToOnStart() {
        onStart.add(new Runnable() {
            @Override
            public void run() {
                Events.schedule(10000, 2000, new Timed() {
                    /**
                     * When the timed execution begins.
                     * <p>
                     * This is the very first Runnable which is run.
                     * After the first time the Runnable is run,
                     * this won't execute anymore. You might be looking
                     * for either during or close methods if you
                     * want stuff to happen after that.
                     * </p>
                     *
                     * @return a runnable containing the timed execution stuff
                     */
                    @Override
                    public Runnable open() {
                        return new Runnable() {
                            @Override
                            public void run() {
                                System.out.println("Opened!");
                            }
                        };
                    }

                    /**
                     * While the timed execution is in progress
                     * <p>
                     * It's unlikely you're going to be using this,
                     * but oh well. This is code which is executed every
                     * single tick, aside from the first and last ticks.
                     * </p>
                     *
                     * @return a runnable containing the timed execution stuff
                     */
                    @Override
                    public Runnable during() {
                        return new Runnable() {
                            @Override
                            public void run() {
                            }
                        };
                    }

                    /**
                     * The final thing that's run when the timed execution closes.
                     * <p>
                     * This is the last bit of code which is executed before the
                     * event is deleted from the scheduler for all of eternity.
                     * </p>
                     *
                     * @return a runnable containing finishing / closing code.
                     */
                    @Override
                    public Runnable close() {
                        return new Runnable() {
                            @Override
                            public void run() {
                                System.out.println("Closed!");
                            }
                        };
                    }
                });
            }
        });
    }
}
