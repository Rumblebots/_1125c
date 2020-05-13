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

    /**
     * Gets the servo's current position.
     *
     * @return the servo's position.
     */
    public double getPosition ()
    {
        return servoComponent.getPosition();
    }

    /**
     * Set the servo's target position
     *
     * @param position the next position to aim for
     */
    public void setPosition (double position)
    {
        servoComponent.setPosition(position);
    }
}
