/*
 * ---
 *
 * Copyright (c) 2020
 * Copyright last updated on 6/4/20, 8:49 PM
 * Part of the _1125c library
 *
 * ---
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
 * ---
 *
 */

package org._11253.lib.utils.gen;

/**
 * Basic shifter function.
 * <p>
 * This works similarly to how Toggle works.
 * If you really need this to be explained, there's a little
 * bit of an issue. But, to be fair, why the hell are you looking at this anyway?
 * </p>
 */
public class Shifter {
    private int currentGear;
    private int maxGear;
    private int minGear;
    private boolean canShift;

    public Shifter() {
        this(1, 10, 1);
    }

    public Shifter(int current, int max, int min) {
        this.currentGear = current;
        this.maxGear = max;
        this.minGear = min;
    }

    public void onPressShiftUp() {
        if (canShift && currentGear + 1 <= maxGear) {
            currentGear++;
        }
    }

    public void onPressShiftDown() {
        if (canShift && currentGear - 1 >= minGear) {
            currentGear--;
        }
    }

    public void onRelease() {
        canShift = true;
    }

    public int getCurrentGear() {
        return currentGear;
    }

    public void setCurrentGear(int currentGear) {
        this.currentGear = currentGear;
    }

    public int getMaxGear() {
        return maxGear;
    }

    public void setMaxGear(int maxGear) {
        this.maxGear = maxGear;
    }

    public int getMinGear() {
        return minGear;
    }

    public void setMinGear(int minGear) {
        this.minGear = minGear;
    }

    public boolean isCanShift() {
        return canShift;
    }

    public void setCanShift(boolean canShift) {
        this.canShift = canShift;
    }
}
