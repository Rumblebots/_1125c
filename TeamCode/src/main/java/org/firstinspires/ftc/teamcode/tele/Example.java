package org.firstinspires.ftc.teamcode.tele;

import org._11253.lib.Global;
import org._11253.lib.controllers.ControllerMap;
import org._11253.lib.op.TeleOp;
import org._11253.lib.utils.Command;
import org._11253.lib.utils.gen.Shifter;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp (name = "Example", group = "TeleOp")
public class Example extends TeleOp
{
    private Shifter gearShifter = new Shifter();

    private void map ()
    {
        controller1.map.bind(ControllerMap.States.A, new Command ()
        {
            public Runnable active = new Runnable ()
            {
                @Override
                public void run ()
                {
                    Global.getTelem().addLine("Hey, look at that! The A button is pressed right now!");
                }
            };
            public Runnable inactive = new Runnable ()
            {
                @Override
                public void run ()
                {
                    Global.getTelem().addLine("Sadly, the A button is not pressed right now.");
                }
            };
        });
        controller1.map.bind(ControllerMap.States.DPAD_UP, new Command ()
        {
            public Runnable active = new Runnable ()
            {
                @Override
                public void run ()
                {
                    gearShifter.onPressShiftUp();
                }
            };
            public Runnable inactive = new Runnable ()
            {
                @Override
                public void run ()
                {
                    gearShifter.onRelease();
                }
            };
        });
        controller1.map.bind(ControllerMap.States.DPAD_DOWN, new Command ()
        {
           public Runnable active = new Runnable ()
           {
               @Override
               public void run ()
               {
                   gearShifter.onPressShiftDown();
               }
           };
           public Runnable inactive = new Runnable ()
           {
               @Override
               public void run ()
               {
                   gearShifter.onRelease();
               }
           };
        });
    }

    @Override
    public void load ()
    {
        map();
    }

    @Override
    public void run ()
    {
        Global.getTelem().update();
    }
}
