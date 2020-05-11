package org._11253.lib.robot.phys.components;

/**
 * Provides an implementation of FTC's default Servo class.
 * This one is obviously better, mostly because it's way cooler.
 */
public class Servo extends Component
{
    com.qualcomm.robotcore.hardware.Servo servoComponent;

    public Servo (String name)
    {
        super(com.qualcomm.robotcore.hardware.Servo.class, name);
        servoComponent = (com.qualcomm.robotcore.hardware.Servo) component;
    }

    public double getPosition ()
    {
        return servoComponent.getPosition();
    }

    public void setPosition (double position)
    {
        servoComponent.setPosition(position);
    }
}
