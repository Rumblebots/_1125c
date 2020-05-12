package org._11253.lib.robot.phys.components.sensors;

import com.qualcomm.hardware.bosch.BNO055IMU;
import org._11253.lib.robot.phys.components.Component;

public class IMU extends Component
{
    BNO055IMU imuComponent;

    public IMU (String name)
    {
        super(BNO055IMU.class, name);
    }

    public BNO055IMU getImu ()
    {
        return imuComponent;
    }
}
