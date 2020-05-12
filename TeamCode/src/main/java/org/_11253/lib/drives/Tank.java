package org._11253.lib.drives;

import org._11253.lib.controllers.ControllerMap;
import org._11253.lib.motors.MotorPower;
import org._11253.lib.op.TeleOp;
import org._11253.lib.robot.Robot;
import org._11253.lib.robot.phys.assm.Drivetrain;
import org._11253.lib.utils.Command;
import org._11253.lib.utils.gen.Shifter;

/**
 * Short and sweet drive train method which can be extended by other actual op modes.
 * <p>
 *     Includes a basic speed-shifter (divisor) as well as mapping really simple
 *     controls to controller 1, which is really just
 * </p>
 */
public class Tank extends TeleOp
{
    public Drivetrain drivetrain = new Drivetrain();

    public Shifter divisor = new Shifter(1, 3, 1);

    public Tank ()
    {
        Robot.addSubsystem(new Drivetrain());
    }

    /**
     * Map basic tank controls, which are just the following:
     * <ul>
     *     <li>right-left movement</li>
     *     <li>gear shifting</li>
     * </ul>
     */
    public void mapControls ()
    {
        controller1.map.bind(ControllerMap.States.STICK, new Command ()
        {
            public Runnable active = new Runnable ()
            {
                @Override
                public void run ()
                {
                    drivetrain.setPower(new MotorPower ()
                    {
                        double frontRightPower = controller1.getRightY() / divisor.getCurrentGear();
                        double frontLeftPower = controller1.getLeftY() / divisor.getCurrentGear();
                        double backRightPower = controller1.getRightY() / divisor.getCurrentGear();
                        double backLeftPower = controller1.getLeftX() / divisor.getCurrentGear();
                    });
                }
            };
            public Runnable inactive = new Runnable ()
            {
                @Override
                public void run ()
                {
                    /*
                     * If there's no input, just stop the whole robot (kinda similar to braking).
                     */
                    drivetrain.setPower(new MotorPower ()
                    {
                        double frontRightPower = 0;
                        double frontLeftPower = 0;
                        double backRightPower = 0;
                        double backLeftPower = 0;
                    });
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
                    divisor.onPressShiftUp();
                }
            };
        });
    }

    @Override
    public void load ()
    {
        drivetrain.init();
        mapControls();
    }

    @Override
    public void run ()
    {

    }
}
