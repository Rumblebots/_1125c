/*
 * **
 *
 * Copyright (c) 2020
 * Copyright last updated on 6/4/20, 11:31 PM
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

package org._11253.lib.utils.telem;

import org._11253.lib.Global;

import java.util.HashMap;

/**
 * A high-level abstraction of FTC's telemetry.
 * <p>
 * This utility class contains two different types
 * of telemetry.
 *     <ul>
 *         <li>
 *             <b>Temporary</b>
 *             Temporary telemetry is wiped away every single time
 *             the telemetry data currently stored is printed. This functions
 *             exactly like FTC's default telemetry system - in order for this
 *             to be displayed, it must be assigned a value every single time
 *             telemetry is printed. Temporary telemetry IS accessible. addLine
 *             and addData still return with String keys, which are in the format
 *             of "temp_#." Although I'm not entirely sure for what reason you'd want
 *             to have access to temporary telemetry, it's there anyway.
 *         </li>
 *         <li>
 *             <b>Permanent</b>
 *             Permanent telemetry is NOT wiped away whenever all of the
 *             telemetry data is printed. This is useful for storing values
 *             which you don't want to have to manually update the value of
 *             every single time you want telemetry to be printed. Permanent data
 *             works with a key-based system, meaning you have to use String keys
 *             to refer to or access Permanent telemetry. All of the permanent telemetry
 *             related functions return Strings, which are the keys that you're using.
 *         </li>
 *     </ul>
 * </p>
 */
public class Telemetry {
    /**
     * An internally-used HashMap containing Telem objects.
     * <p>
     * This should only be used internally.
     * </p>
     * <p>
     * Each Telem object is iterated over while printing
     * telemetry data. Note that each contained Telem object
     * can be either DATA or LINE, which both extend the default
     * Telem, meaning there will always be a getMessage method.
     * </p>
     */
    public HashMap<String, Telem> telemetry = new HashMap<String, Telem>();
    /**
     * The highest number temp there is / can be
     * or whatever the hell it's supposed to be.
     */
    private int currentTemporaryCap = 0;

    /**
     * Temporary access for telemetry, specifically the
     * data portion. Use this when you just want to throw in
     * a disposable telemetry data message thingy.
     *
     * @param caption the caption you want
     * @param data    the data you want
     * @return the key of the newly created thingy
     */
    public String addData(String caption, String data) {
        int number = currentTemporaryCap;
        String name = "temp_" + number;
        addData(name, caption, data);
        currentTemporaryCap++;
        return name;
    }

    /**
     * Permanent access with a key.
     * <p>
     * You'll probably end up using this version the most - it doesn't
     * have a field for SEPARATOR, which, quite likely, you're not
     * going to be editing anyway.
     * </p>
     *
     * @param key     the key you'd like to use
     * @param caption the caption of the data
     * @param data    the data portion of the data
     * @return the key you're using
     */
    public String addData(String key, String caption, String data) {
        return addData(key, caption, ": ", data);
    }

    /**
     * Big boy function which actually creates a new Data TELEM object
     * and does all the cool stuff with it.
     *
     * @param key       the key you'd like to use
     * @param caption   the caption of the data
     * @param separator the separator used in the message
     * @param data      the actual data portion of the data
     * @return the key you're using
     */
    public String addData(String key, String caption, String separator, String data) {
        Data dataTelem;
        if (telemetry.containsKey(key)) {
            dataTelem = (Data) telemetry.get(key);
        } else {
            dataTelem = new Data();
        }
        dataTelem.setCaption(caption, false);
        dataTelem.setSeparator(separator, false);
        dataTelem.setData(data);
        telemetry.put(key, dataTelem);
        return key;
    }

    /**
     * Temporary access to adding a line.
     *
     * @param line the text you're using.
     * @return the String key.
     */
    public String addLine(Object line) {
        int number = currentTemporaryCap;
        String name = "temp_" + number;
        currentTemporaryCap++;
        return addLine(name, line);
    }

    /**
     * Permanent access to use when adding a line.
     * <p>
     * This one contains a key as well as text,
     * meaning you'll get more permanent access to it.
     * Once again, very cool, I know.
     * </p>
     *
     * @param key  the key you're using
     * @param line the text of the line
     * @return the key which you're using
     */
    public String addLine(String key, Object line) {
        Line lineTelem;
        if (telemetry.containsKey(key)) {
            lineTelem = (Line) telemetry.get(key);
        } else {
            lineTelem = new Line();
        }
        lineTelem.setMessage(line.toString());
        return key;
    }

    /**
     * Removes a bit of data.
     *
     * @param key
     * @return
     */
    public String removeData(String... key) {
        return remove(key);
    }

    public String removeLine(String... key) {
        return remove(key);
    }

    public String remove() {
        return remove("");
    }

    public String remove(String... key) {
        for (String k : key) {
            telemetry.remove(k);
        }
        return key[0];
    }

    public void clear() {
        telemetry = new HashMap<String, Telem>();
        currentTemporaryCap = 0;
    }

    public void printTelemetry() {
        org.firstinspires.ftc.robotcore.external.Telemetry t = Global.getTelem();
        for (HashMap.Entry<String, Telem> entry : telemetry.entrySet()) {
            t.addLine(entry.getValue().getMessage());
            if (entry.getKey().contains("temp_")) {
                telemetry.remove(entry.getKey());
                currentTemporaryCap--;
            }
        }
        t.update();
    }

    /**
     * A list of all the possible types.
     */
    enum Telems { // A list of the different types of telems which are available.
        DATA,     // Contains caption - value pair.
        LINE,     // Contains just a single line of text.
        TELEM,    // The most simple version.
    }
}
