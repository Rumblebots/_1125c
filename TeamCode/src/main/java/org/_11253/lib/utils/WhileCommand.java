/*
 * **
 *
 * Copyright (c) 2020
 * Copyright last updated on 6/9/20, 10:12 PM
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

package org._11253.lib.utils;

import org._11253.lib.utils.async.event.Events;

/**
 * A type of command which runs while a condition is true.
 * <p>
 * I'm sorry this is written so incredibly poorly, but hey,
 * it still works, so who's really complaining?
 * </p>
 * <p>
 * This ***should*** help with event-driven stuff, because it
 * lets you run something while and only while something else
 * is working.
 * <p>
 * Look, I have to say, I'm really into this whole event system
 * I created. I may or may not be stroking my cock over some shitty
 * code, but, hey. It's okay.
 * </p>
 *
 * @author Colin Robertson
 */
public class WhileCommand {
    /**
     * Internally used running command.
     * <p>
     * This is the part which actually interacts
     * with the events interface to schedule events
     * to run a little bit later. The user doesn't
     * have to worry about any of that, because I'm
     * just so incredibly polite.
     * </p>
     *
     * @param runnable the runnable which should be run.
     */
    private void _run(Runnable runnable) {
        if (check()) {
            Events.schedule(10, new Timed() {
                @Override
                public Runnable close() {
                    return new Runnable() {
                        @Override
                        public void run() {
                            active().run();
                            _run(active());
                        }
                    };
                }
            });
        }
    }

    /**
     * **must** be overriden by user, it's the code that actually runs.
     *
     * @return a runnable to run
     */
    public Runnable active() {
        return new Runnable() {
            @Override
            public void run() {

            }
        };
    }

    /**
     * Check function. The user should override this with
     * whatever state they want.
     *
     * @return whether it's true or not
     */
    public boolean check() {
        return true;
    }

    /**
     * Actually schedule the command itself
     */
    public final void scheduleWhileCommand() {
        _run(active());
    }
}
