package org._11253.lib.op;

import org._11253.lib.Global;
import org._11253.lib.controllers.Controller;
import org._11253.lib.controllers.ControllerMap;
import org._11253.lib.utils.Command;

/**
 * Tele-Op template class which uses the op mode template class.
 * <p>
 *     A user should NOT override the onStart() or onStartLoop() methods
 *     provided here, as they're 'required' for Tele-Op.
 * </p>
 */
public class TeleOp extends Template
{
    public Controller controller1;
    public Controller controller2;

    /**
     * Example method that shows how control mapping is done.
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

    @Override
    public void onStart ()
    {
        Global.setGamepad1(gamepad1);
        Global.setGamepad2(gamepad2);

        controller1 = new Controller(gamepad1);
        controller2 = new Controller(gamepad2);

        mapStartButton();
    }

    @Override
    public void onStartLoop ()
    {
        controller1.map.runMap();
        controller2.map.runMap();
    }
}
