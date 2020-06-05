/*
 * **
 *
 * Copyright (c) 2020
 * Copyright last updated on 6/4/20, 9:11 PM
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

package org._11253.lib.utils.gen;

/**
 * Basic toggle utility.
 * <p>
 * This should be mapped to a button on a controller, which is run
 * in a constant / never-ending loop environment.
 *     <ul>
 *         <li>
 *             onPress() is triggered when the button is pressed
 *             <ul>
 *                 <li>This means you should add a new mapping to a controller and have it run the function onPress.</li>
 *             </ul>
 *         </li>
 *         <li>
 *             onRelease() is triggered when the button is no longer pressed
 *             <ul>
 *                 <li>This means you should add a new mapping to a controller and have it run the function onRelease.</li>
 *             </ul>
 *         </li>
 *     </ul>
 * </p>
 */
public class Toggle {
    public boolean state = true;
    public boolean canBeChanged = true;

    public void onPress() {
        if (canBeChanged) {
            state = !state;
            canBeChanged = false;
        }
    }

    public void onRelease() {
        canBeChanged = true;
    }
}
