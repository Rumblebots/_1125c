/*
 * **
 *
 * Copyright (c) 2020
 * Copyright last updated on 6/5/20, 6:30 PM
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

package org._11253.lib.utils.async.event;

import android.annotation.SuppressLint;
import org._11253.lib.utils.Timed;

import java.util.HashMap;

public class Events {
    /**
     * A list of all the timed events.
     * <p>
     * This doesn't use any Java's default stuff for
     * asynchronous functions, mostly because FTC is
     * low-key weird with how they handle everything.
     * Instead of being in an event driven environment,
     * FTC's code runs in a loop based environment. Which
     * is most of the reason I created this library in the
     * first place - provide an event-driven environment.
     * </p>
     * <p>
     * The Integer represents the maximum epoch which this
     * code is allowed to execute past.
     * The Timed object is an extension of TimedCore which
     * just provides a couple Runnables the user can modify.
     * </p>
     */
    @SuppressLint("UseSparseArrays")
    public HashMap<Integer, Timed> events = new HashMap<>();

    /**
     * (Hopefully) Will run every cycle of a loop. It'll check the
     * time and make sure everything is working out as it should be.
     */
    public void tick() {
        int now = (int) System.currentTimeMillis();
        for (HashMap.Entry<Integer, Timed> entry : events.entrySet()) {
            Integer key = entry.getKey();
            Timed value = entry.getValue();
            if (key > now) {
                value.close().run();
                events.remove(key);
            } else {
                if (value.ran) {
                    value.during().run();
                } else {
                    value.open().run();
                    value.ran = true;
                }
                events.put(key, value);
            }
        }
    }

    /**
     * Overloaded method for the default scheduler.
     *
     * @param duration how long the event should last
     * @param timed    the actual event
     */
    public void schedule(int duration, Timed timed) {
        schedule(duration, 0, timed);
    }

    /**
     * Wrapper for the default put function, schedules an event.
     * <p>
     * If the delay is anything other than zero, create a new
     * scheduled event, with a duration of however long the
     * delay is. Once that event expires and the close method
     * is run, schedule another event, with the actual duration
     * and the actual event included.
     * </p>
     * <p>
     * If the delay IS zero, however, calculate the expiration
     * date of the event and schedule an event for that long.
     * </p>
     * <p>
     * Don't worry if this doesn't make any sense - this
     * method will handle any confusion you have and just so perfectly
     * schedule everything nicely for you.
     * </p>
     * <p>
     * In general, when scheduling an event, you're going to use
     * the open and close methods contained within Timed.
     * *** MAKE SURE NOT TO FORGET TO STOP ANYTHING ***
     * ***   YOU NEED TO STOP IN THE CLOSE METHOD!  ***
     * </p>
     *
     * @param duration how long the event should last
     * @param delay    how long until the event is propagated
     * @param timed    the actual event which should be run
     */
    public void schedule(final int duration, int delay, final Timed timed) {
        if (delay != 0) {
            schedule(delay, 0, new Timed() {
                @Override
                public Runnable close() {
                    return new Runnable() {
                        @Override
                        public void run() {
                            schedule(duration, 0, timed);
                        }
                    };
                }
            });
        }
        events.put((int) System.currentTimeMillis() + duration, timed);
    }
}
