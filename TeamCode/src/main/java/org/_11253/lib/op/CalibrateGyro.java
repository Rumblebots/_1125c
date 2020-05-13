package org._11253.lib.op;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ReadWriteFile;
import org._11253.lib.Global;
import org._11253.lib.robot.phys.components.sensors.IMU;
import org.firstinspires.ftc.robotcore.internal.system.AppUtil;

import java.io.File;

/**
 * Simple OpMode to calibrate the gyroscope and write it's data
 * to a file for later usage in different OpModes. This should be
 * run before doing anything that uses pre-saved gyroscope data.
 */
@TeleOp(name = "Calibrate Gyroscope", group = "default")
public final class CalibrateGyro extends Template
{
    IMU imu;
    BNO055IMU.Parameters params;
    boolean hasNotBeenCalibrated;

    // TODO make this a little bit cleaner.
    public CalibrateGyro ()
    {
        onStart.add(new Runnable()
        {
            @Override
            public void run ()
            {
                lOnStart();
            }
        }, new Runnable()
        {
            @Override
            public void run ()
            {
                lLoad();
            }
        });
        run.add(new Runnable()
        {
            @Override
            public void run ()
            {
                lRun();
            }
        });
    }

    private void lOnStart ()
    {
        imu = new IMU("imu");
        params = new BNO055IMU.Parameters();
        params.loggingEnabled = true;
        params.loggingTag = "IMU";
        hasNotBeenCalibrated = true;
    }

    private void lLoad ()
    {
        imu.getImu().initialize(params);
        telemetry.clear();
    }

    private void lRun ()
    {
        if (imu.getImu().isGyroCalibrated() && hasNotBeenCalibrated)
        {
            Global.getTelem().addLine("Writing calibration data.");
            Global.getTelem().update();
            BNO055IMU.CalibrationData calibrationData = imu.getImu().readCalibrationData();
            String name = "IMUCalibrationData.json";
            File file = AppUtil.getInstance().getSettingsFile(name);
            ReadWriteFile.writeFile(file, calibrationData.serialize());
            hasNotBeenCalibrated = false;
        }
        else
        {
            Global.getTelem().addLine("Finished writing calibration data.");
            Global.getTelem().addLine("Exit this OpMode and run another one.");
            Global.getTelem().addLine("Note that this data does not persist through hard restarts.");
            Global.getTelem().update();
        }
    }
}
