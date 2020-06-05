/*
 * ---
 *
 * Copyright (c) 2020
 * Copyright last updated on 6/4/20, 9:02 PM
 * Part of the _1125c library
 *
 * ---
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
 * ---
 *
 */

package org._11253.lib.controllers;

import com.qualcomm.robotcore.hardware.Gamepad;
import org._11253.lib.utils.Command;

import java.util.HashMap;
import java.util.Objects;

/**
 * Controller mapping interface to make mapping buttons a little bit smoother.
 * <p>
 * Enum "States" contains all of the possible boolean states a controller can have.
 * The HashMap (states mapped to runnables) contains mapping between a specific boolean state and
 * a function for it to execute if it comes out true. If the button is not pressed, or if there is
 * nothing mapped to it, don't do anything.
 * </p>
 * <p>
 * Note that multiple states can be active at once - for example, both the
 * A button and B button can be active at the same time.
 * </p>
 */
public class ControllerMap {
    Gamepad gamepad;
    /**
     * A map of all of the commands which should be executed.
     * <p>
     * This is interfaced through the functions bind and unbind.
     * </p>
     */
    private HashMap<States, Command> commandMap = new HashMap<>();

    public ControllerMap(Gamepad gamepad) {
        this.gamepad = gamepad;
    }

    /**
     * Used to map a certain state to a function.
     *
     * @param state   a state of the gamepad
     * @param command a function to execute
     */
    public final void bind(States state, Command command) {
        commandMap.put(state, command);
    }

    /**
     * Used to unmap a certain state from the ControllerMap.
     *
     * @param state the state which should be unmapped
     */
    public final void unbind(States state) {
        commandMap.remove(state);
    }

    /**
     * Run all of the code which is stored in the map.
     */
    public final void runMap() {
        Objects.requireNonNull(commandMap.get(States.START)).getRunnable(gamepad.start).run();
        Objects.requireNonNull(commandMap.get(States.A)).getRunnable(gamepad.a).run();
        Objects.requireNonNull(commandMap.get(States.B)).getRunnable(gamepad.b).run();
        Objects.requireNonNull(commandMap.get(States.X)).getRunnable(gamepad.x).run();
        Objects.requireNonNull(commandMap.get(States.Y)).getRunnable(gamepad.y).run();
        Objects.requireNonNull(commandMap.get(States.DPAD_UP)).getRunnable(gamepad.dpad_up).run();
        Objects.requireNonNull(commandMap.get(States.DPAD_RIGHT)).getRunnable(gamepad.dpad_right).run();
        Objects.requireNonNull(commandMap.get(States.DPAD_DOWN)).getRunnable(gamepad.dpad_down).run();
        Objects.requireNonNull(commandMap.get(States.DPAD_LEFT)).getRunnable(gamepad.dpad_left).run();
        Objects.requireNonNull(commandMap.get(States.RIGHT_BUMPER)).getRunnable(gamepad.right_bumper).run();
        Objects.requireNonNull(commandMap.get(States.LEFT_BUMPER)).getRunnable(gamepad.left_bumper).run();
        Objects.requireNonNull(commandMap.get(States.RIGHT_STICK)).getRunnable(gamepad.right_stick_x != 0 || gamepad.right_stick_y != 0).run();
        Objects.requireNonNull(commandMap.get(States.LEFT_STICK)).getRunnable(gamepad.left_stick_x != 0 || gamepad.left_stick_y != 0).run();
        Objects.requireNonNull(commandMap.get(States.RIGHT_STICK_X)).getRunnable(gamepad.right_stick_x != 0).run();
        Objects.requireNonNull(commandMap.get(States.RIGHT_STICK_Y)).getRunnable(gamepad.right_stick_y != 0).run();
        Objects.requireNonNull(commandMap.get(States.LEFT_STICK_X)).getRunnable(gamepad.left_stick_x != 0).run();
        Objects.requireNonNull(commandMap.get(States.LEFT_STICK_Y)).getRunnable(gamepad.left_stick_y != 0).run();
        Objects.requireNonNull(commandMap.get(States.STICK)).getRunnable(gamepad.right_stick_x != 0 || gamepad.right_stick_y != 0 || gamepad.left_stick_x != 0 || gamepad.left_stick_y != 0).run();
        Objects.requireNonNull(commandMap.get(States.RIGHT_TRIGGER)).getRunnable(gamepad.right_trigger != 0).run();
        Objects.requireNonNull(commandMap.get(States.LEFT_TRIGGER)).getRunnable(gamepad.left_trigger != 0).run();
    }

    /**
     * Enums for every possible "state" the controller can be in.
     * <p>
     * Note that states for stick movements only accept the following conditions:
     *     <ul>
     *         <li>"RIGHT_STICK": Right stick's X <b>or</b> Y is offset by anything at all.</li>
     *         <li>"LEfT_STICK": Left stick's X <b>or</b> Y is offset by anything at all.</li>
     *         <li>"STICK": Right <b>or</b> left stick's X <b>or</b> Y is offset by anything at all</li>
     *     </ul>
     *     This means there is no specific states for left stick offset or right stick offset.
     *     TODO implement different states for X and Y axes on both sticks
     *      Add a way to change sensitivity or dead zones on stick movement detections
     * </p>
     */
    public enum States {
        START,
        A,
        B,
        X,
        Y,
        RIGHT_BUMPER,
        LEFT_BUMPER,
        DPAD_UP,
        DPAD_RIGHT,
        DPAD_DOWN,
        DPAD_LEFT,
        RIGHT_STICK,
        LEFT_STICK,
        RIGHT_STICK_X,
        RIGHT_STICK_Y,
        LEFT_STICK_X,
        LEFT_STICK_Y,
        STICK,
        RIGHT_TRIGGER,
        LEFT_TRIGGER
    }
}
