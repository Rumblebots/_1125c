package org._11253.lib.robot;

import android.util.SparseArray;
import org._11253.lib.robot.phys.assm.Subsystem;
import org._11253.lib.utils.gen.id.Id;

public class Robot
{
    public static SparseArray<Subsystem> subsystems = new SparseArray<Subsystem>();

    public static int getIdOfSubsystem (Subsystem search)
    {
        return subsystems.indexOfValue(search);
    }

    public static Subsystem getSubsystemById (Id search)
    {
        return subsystems.get(search.getId());
    }

    public static void addSubsystem (Subsystem subsystem)
    {
        subsystems.put(subsystem.subsystemId.getId(), subsystem);
    }

    public static void removeSubsystem (Subsystem subsystem)
    {
        subsystems.remove(subsystem.subsystemId.getId());
    }
}
