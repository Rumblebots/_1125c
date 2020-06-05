/*
 * **
 *
 * Copyright (c) 2020
 * Copyright last updated on 6/5/20, 11:52 AM
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

package org._11253.lib.robot.phys.components.sensors;

import com.qualcomm.robotcore.hardware.DistanceSensor;
import org._11253.lib.robot.phys.components.Component;
import org._11253.lib.utils.async.tasks.RepeatingTask;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/**
 * Wrapper implementation of distance sensor.
 *
 * @see Color
 * @see RepeatingTask
 * <p>
 * This works just like the color sensor's implementation does,
 * with a map-value thing to make sure it's not polled too
 * frequently. During the 2019-2020 SKYSTONE season, my team
 * had issues with distance sensors crashing I2C ports and
 * causing the whole robot to shit itself, so this should
 * (hopefully) circumvent that. If you find the delay to be
 * too high or too low, you can feel free to customize that
 * to your likings, just don't blame me if something goes
 * absolutely horribly & miserably wrong.
 * </p>
 */
public class Distance extends Component {
    public DistanceMap map;

    DistanceSensor sensor;

    /**
     * Create a new repeating task to update the value once
     * every so often.
     * <p>
     * Currently, as of 6/5/2020 at 11:51 AM, this
     * value is 100 milliseconds. I don't have a robot
     * with me, so I can't test and make sure this
     * is good enough, but I'm really hoping it is.
     * </p>
     */
    RepeatingTask task = new RepeatingTask(new Runnable() {
        @Override
        public void run() {
            update();
        }
    });

    public Distance(String name) {
        super(DistanceSensor.class, name);
        sensor = (DistanceSensor) component;
        task.scheduleRepeatingTask(100); // TODO: make sure this value is good enough
    }

    /**
     * Gets the distance the sensor picks up
     *
     * @return distance in {unit}
     */
    private double getDistanceCm() {
        return sensor.getDistance(DistanceUnit.CM);
    }

    /**
     * Gets the distance the sensor picks up
     *
     * @return distance in {unit}
     */
    private double getDistanceIn() {
        return sensor.getDistance(DistanceUnit.INCH);
    }

    /**
     * Gets the distance the sensor picks up
     *
     * @return distance in {unit}
     */
    private double getDistanceM() {
        return sensor.getDistance(DistanceUnit.METER);
    }

    /**
     * Gets the distance the sensor picks up
     *
     * @return distance in {unit}
     */
    private double getDistanceMm() {
        return sensor.getDistance(DistanceUnit.MM);
    }

    private DistanceMap read() {
        return new DistanceMap(
                getDistanceCm(),
                getDistanceIn(),
                getDistanceM(),
                getDistanceMm()
        );
    }

    private void update() {
        map = read();
    }

    public class DistanceMap {
        private double Cm, Inch, Meter, Mm;

        public DistanceMap() {
            this(0, 0, 0, 0);
        }

        public DistanceMap(double cm, double inch, double meter, double mm) {
            this.Cm = cm;
            this.Inch = inch;
            this.Meter = meter;
            this.Mm = mm;
        }

        public DistanceMap getDistanceMap() {
            return this;
        }

        /**
         * Gets the distance the sensor picks up
         *
         * @return distance in {unit}
         */
        public double cm() {
            return Cm;
        }

        /**
         * Gets the distance the sensor picks up
         *
         * @return distance in {unit}
         */
        public double inch() {
            return Inch;
        }

        /**
         * Gets the distance the sensor picks up
         *
         * @return distance in {unit}
         */
        public double meter() {
            return Meter;
        }

        /**
         * Gets the distance the sensor picks up
         *
         * @return distance in {unit}
         */
        public double mm() {
            return Mm;
        }
    }
}
