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

package org._11253.lib.utils;

public class Timed implements TimedCore {
    public boolean ran = false;

    /**
     * When the timed execution begins.
     *
     * @return a runnable containing the timed execution stuff
     */
    public Runnable open() {
        return new Runnable() {
            @Override
            public void run() {

            }
        };
    }

    /**
     * While the timed execution is in progress
     *
     * @return a runnable containing the timed execution stuff
     */
    public Runnable during() {
        return new Runnable() {
            @Override
            public void run() {

            }
        };
    }

    /**
     * The final thing that's run when the timed execution closes.
     *
     * @return a runnable containing finishing / closing code.
     */
    public Runnable close() {
        return new Runnable() {
            @Override
            public void run() {

            }
        };
    }
}
