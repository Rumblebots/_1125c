package org._11253.lib.op;

import org._11253.lib.Global;
import org._11253.lib.controllers.Controller;
import org._11253.lib.controllers.ControllerMap;
import org._11253.lib.utils.Command;

/**
 * Tele-Op template class which uses the op mode template class.
 * <p>
 * Provides a very simple extension of the default operation mode template.
 * There's really not much else it does. Yeah, that's it. Exciting, I know.
 * </p>
 */
public class TeleOp extends Template
{
    public Controller controller1;
    public Controller controller2;

    /**
     * Constructor. Yeah, that's really it.
     */
    public TeleOp ()
    {
        mapStartButton();
        addToStart();
        addToOnStartRun();
    }

    /**
     * Example method that shows how control mapping is done.
     * <p>
     * This can be over-written in a user's extension of the TeleOp class by simply
     * re-mapping what happens on START. This just exists as as a demonstration of how
     * control mapping works.
     * </p>
     */
    private void mapStartButton ()
    {
        controller1.map.bind(ControllerMap.States.START, new Command()
        {
            @Override
            public Runnable active ()
            {
                return new Runnable()
                {
                    @Override
                    public void run ()
                    {
                        /*
                         * If the start button is pressed, run the code that's here.
                         * In this case, all that this code does is add some telem and then update it.
                         */
                        Global.getTelem().addLine("The start button is currently pressed!");
                        Global.getTelem().update();
                    }
                };
            }

            @Override
            public Runnable inactive ()
            {
                return new Runnable()
                {
                    @Override
                    public void run ()
                    {
                        /*
                         * If the start button is not pressed, don't do anything at all.
                         */
                    }
                };
            }
        });
    }

    /**
     * Add to the onStart list in Template class.
     * <p>
     * This just sets the global gamepads and sets up
     * both of the controllers for use in other files.
     * </p>
     */
    private void addToStart ()
    {
        onStart.add(new Runnable()
        {
            @Override
            public void run ()
            {
                Global.setGamepad1(gamepad1);
                Global.setGamepad2(gamepad2);
                controller1 = new Controller(gamepad1);
                controller2 = new Controller(gamepad2);
            }
        });
    }

    /**
     * Run the controller's maps whenever the loop begins again.
     * <p>
     * I don't know why anybody would possibly want to change this,
     * but even if they did, they can't, so it would really suck to be them,
     * now wouldn't it?
     * </p>
     */
    private void addToOnStartRun ()
    {
        onStartRun.add(new Runnable()
        {
            @Override
            public void run ()
            {
                controller1.map.runMap();
                controller2.map.runMap();
            }
        });
    }
}
