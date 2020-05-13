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
public class ControllerMap
{
    /**
     * A map of all of the commands which should be executed.
     * <p>
     * This is interfaced through the functions bind and unbind.
     * </p>
     */
    private HashMap<States, Command> commandMap = new HashMap<States, Command>();
    Gamepad gamepad;

    public ControllerMap (Gamepad gamepad)
    {
        this.gamepad = gamepad;
    }

    /**
     * Used to map a certain state to a function.
     *
     * @param state   a state of the gamepad
     * @param command a function to execute
     */
    public void bind (States state, Command command)
    {
        commandMap.put(state, command);
    }

    /**
     * Used to unmap a certain state from the ControllerMap.
     *
     * @param state the state which should be unmapped
     */
    public void unbind (States state)
    {
        commandMap.remove(state);
    }

    /**
     * Run all of the code which is stored in the map.
     */
    public void runMap ()
    {
//        if (commandMap.containsKey(States.START))
        Objects.requireNonNull(commandMap.get(States.START)).getRunnable(gamepad.start).run();
//        if (commandMap.containsKey(States.A))
        Objects.requireNonNull(commandMap.get(States.A)).getRunnable(gamepad.a).run();
//        if (commandMap.containsKey(States.B))
        Objects.requireNonNull(commandMap.get(States.B)).getRunnable(gamepad.b).run();
//        if (commandMap.containsKey(States.X))
        Objects.requireNonNull(commandMap.get(States.X)).getRunnable(gamepad.x).run();
//        if (commandMap.containsKey(States.Y))
        Objects.requireNonNull(commandMap.get(States.Y)).getRunnable(gamepad.y).run();
//        if (commandMap.containsKey(States.DPAD_UP))
        Objects.requireNonNull(commandMap.get(States.DPAD_UP)).getRunnable(gamepad.dpad_up).run();
//        if (commandMap.containsKey(States.DPAD_RIGHT))
        Objects.requireNonNull(commandMap.get(States.DPAD_RIGHT)).getRunnable(gamepad.dpad_right).run();
//        if (commandMap.containsKey(States.DPAD_DOWN))
        Objects.requireNonNull(commandMap.get(States.DPAD_DOWN)).getRunnable(gamepad.dpad_down).run();
//        if (commandMap.containsKey(States.DPAD_LEFT))
        Objects.requireNonNull(commandMap.get(States.DPAD_LEFT)).getRunnable(gamepad.dpad_left).run();
//        if (commandMap.containsKey(States.RIGHT_BUMPER))
        Objects.requireNonNull(commandMap.get(States.RIGHT_BUMPER)).getRunnable(gamepad.right_bumper).run();
//        if (commandMap.containsKey(States.LEFT_BUMPER))
        Objects.requireNonNull(commandMap.get(States.LEFT_BUMPER)).getRunnable(gamepad.left_bumper).run();
//        if (commandMap.containsKey(States.RIGHT_STICK))
        Objects.requireNonNull(commandMap.get(States.RIGHT_STICK)).getRunnable(gamepad.right_stick_x != 0 || gamepad.right_stick_y != 0).run();
//        if (commandMap.containsKey(States.LEFT_STICK))
        Objects.requireNonNull(commandMap.get(States.LEFT_STICK)).getRunnable(gamepad.left_stick_x != 0 || gamepad.left_stick_y != 0).run();
//        if (commandMap.containsKey(States.STICK))
        Objects.requireNonNull(commandMap.get(States.STICK)).getRunnable(gamepad.right_stick_x != 0 || gamepad.right_stick_y != 0 || gamepad.left_stick_x != 0 || gamepad.left_stick_y != 0).run();
//        if (commandMap.containsKey(States.RIGHT_TRIGGER))
        Objects.requireNonNull(commandMap.get(States.RIGHT_TRIGGER)).getRunnable(gamepad.right_trigger != 0).run();
//        if (commandMap.containsKey(States.LEFT_TRIGGER))
        Objects.requireNonNull(commandMap.get(States.LEFT_TRIGGER)).getRunnable(gamepad.left_trigger != 0).run();
//        if (appendableMap.size() > 0)
//        {
//            for (int i = 0; i < appendableMap.size(); i++)
//            {
//
//            }
//        }
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
    public enum States
    {
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
        STICK,
        RIGHT_TRIGGER,
        LEFT_TRIGGER
    }
}
