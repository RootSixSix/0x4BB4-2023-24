package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="RedFrontstageAuto", group="19380")
public class RedFrontstageAuto extends AutoDriveBase {

    private DriveData[] data = {
            new DriveData(1, 0.8, 0.8, 0.8, 0.8),
            new DriveData(1, -0.8, -0.8, -0.8, -0.8),
    };

    @Override
    public DriveData[] getData() {
        return data;
    }
}
