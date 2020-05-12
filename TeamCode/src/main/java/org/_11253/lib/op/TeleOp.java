package org._11253.lib.op;

import org._11253.lib.Global;
import org._11253.lib.controllers.Controller;
import org._11253.lib.controllers.ControllerMap;
import org._11253.lib.utils.Command;

/**
 * Tele-Op template class which uses the op mode template class.
 * <p>
 *     Provides a very simple extension of the default operation mode template.
 *     There's really not much else it does. Yeah, that's it. Exciting, I know.
 * </p>
 */
public class TeleOp extends Template
{
    public Controller controller1;
    public Controller controller2;

    /**
     * Example method that shows how control mapping is done.
     * <p>
     *     This can be over-written in a user's extension of the TeleOp class by simply
     *     re-mapping what happens on START. This just exists as as a demonstration of how
     *     control mapping works.
     * </p>
     */
    public void mapStartButton ()
    {
        controller1.map.bind(ControllerMap.States.START, new Command ()
        {
            public Runnable active = new Runnable()
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
            public Runnable inactive = new Runnable()
            {
                @Override
                public void run ()
                {
                    // Don't do anything here.
                }
            };
        });
    }

    /**
     * If you'd like to modify what this function does, you should create another
     * class which extends Template and modify it from there.
     * <p>
     *     Declared as final to ensure the user doesn't accidentally forget to run to set up the controllers.
     *     In the driver-controlled mode of operation, the controllers obviously have to do things, which
     *     is why this is set up as it is.
     * </p>
     */
    @Override
    public final void onStart ()
    {
        Global.setGamepad1(gamepad1);
        Global.setGamepad2(gamepad2);

        controller1 = new Controller(gamepad1);
        controller2 = new Controller(gamepad2);

        mapStartButton();
    }

    /**
     * If you'd like to modify what this function does, you should create another
     * class which extends Template and modify it from there.
     * <p>
     *     Declared as final to ensure the user doesn't accidentally forget to run the controller's maps.
     *     In the driver-controlled mode of operation, the controllers obviously have to do things, which
     *     is why this is set up as it is.
     * </p>
     */
    @Override
    public final void onStartLoop ()
    {
        controller1.map.runMap();
        controller2.map.runMap();
    }
}
