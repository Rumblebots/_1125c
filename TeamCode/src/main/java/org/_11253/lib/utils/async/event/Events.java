/*
 * **
 *
 * Copyright (c) 2020
 * Copyright last updated on 6/5/20, 10:13 PM
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
import org._11253.lib.utils.telem.Telemetry;

import java.util.HashMap;

public class Events {
    public static long initializationTime = System.currentTimeMillis();

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
     * <p>
     *     Once again, I know global / static variables are like
     *     bad or something, but this being static makes it a lot better
     *     at absolutely everything, of course. 100%.
     * </p>
     */
    @SuppressLint("UseSparseArrays")
    public static HashMap<Long, Timed> events = new HashMap<>();

    /**
     * (Hopefully) Will run every cycle of a loop. It'll check the
     * time and make sure everything is working out as it should be.
     * <p>
     * This uses a mark-and-remove system to avoid concurrent
     * modification exceptions. It's a little bit slower,
     * but at least you won't get impossible to fix
     * errors. Yay. Exciting, right!!
     * </p>
     * <p>
     * TODO: This needs some serious optimization.
     * I couldn't really figure out a much better way of
     * doing what I'm trying to, and this seems to work,
     * but whoever is reading this, if you can figure out
     * a more efficient way of getting this to run, please
     * let me know as soon as possible so there's no longer
     * this absolute monstrosity in the middle of this
     * relatively clean codebase.
     * </p>
     */
    @SuppressLint("UseSparseArrays")
    public static void tick() {
        long now = System.currentTimeMillis();
        HashMap<Long, Timed> toBeOpened1 = new HashMap<>();
        HashMap<Long, Timed> toBeOpened2 = new HashMap<>();
        HashMap<Long, Timed> toBeDuring = new HashMap<>();
        HashMap<Long, Timed> toBeClosed = new HashMap<>();
        HashMap<Long, Timed> toRemain = new HashMap<>();
        for (HashMap.Entry<Long, Timed> entry : events.entrySet()) {
            long key = entry.getKey();
            Timed value = entry.getValue();
            if (now > key) {
                toBeClosed.put(key, value);
            } else {
                if (value.ran) {
                    toBeDuring.put(key, value);
                } else {
                    toBeOpened1.put(key, value);
                }
            }
        }
        events = new HashMap<>();
        for (HashMap.Entry<Long, Timed> entry : toBeOpened1.entrySet()) {
            long key = entry.getKey();
            Timed value = entry.getValue();
            value.ran = true;
            toBeOpened2.put(key, value);
        }
        for (HashMap.Entry<Long, Timed> entry : toBeOpened2.entrySet()) {
            entry.getValue().open().run();
            events.put(entry.getKey(), entry.getValue());
        }
        for (HashMap.Entry<Long, Timed> entry : toBeDuring.entrySet()) {
            entry.getValue().during().run();
            events.put(entry.getKey(), entry.getValue());
        }
        for (HashMap.Entry<Long, Timed> entry : toBeClosed.entrySet()) {
            entry.getValue().close().run();
        }
        Telemetry.addData("_1125c_ASYNC_HANDLES", "Current handles", "" + events.size());
    }

    /**
     * Overloaded method for the default scheduler.
     *
     * @param duration how long the event should last
     * @param timed    the actual event
     */
    public static void schedule(long duration, Timed timed) {
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
    public static void schedule(final long duration, int delay, final Timed timed) {
        if (delay != 0) {
            schedule(delay, 0, new Timed() {
                @Override
                public Runnable close() {
                    return new Runnable() {
                        @Override
                        public void run() {
                            schedule(duration, 0, timed);
                            System.out.println("Scheduled post-delay Timed execution!");
                        }
                    };
                }
            });
        } else {
            events.put(System.currentTimeMillis() + duration, timed);
        }
        tick();
    }
}