package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="AutoSample", group="19380")
public class AutoSample extends LinearOpMode {

    /*
     * The mecanum drivetrain involves four separate motors that spin in
     * different directions and different speeds to produce the desired
     * movement at the desired speed.
     */

    // declare and initialize four DcMotors.
    private DcMotor front_left  = null;
    private DcMotor front_right = null;
    private DcMotor back_left   = null;
    private DcMotor back_right  = null;

    @Override
    public void runOpMode() {

        // Name strings must match up with the config on the Robot Controller
        // app.
        front_left   = hardwareMap.get(DcMotor.class, "fldrive");
        front_right  = hardwareMap.get(DcMotor.class, "frdrive");
        back_left    = hardwareMap.get(DcMotor.class, "bldrive");
        back_right   = hardwareMap.get(DcMotor.class, "brdrive");
        front_left.setDirection(DcMotor.Direction.REVERSE);
        back_left.setDirection(DcMotor.Direction.REVERSE);
        front_right.setDirection(DcMotor.Direction.FORWARD);
        back_right.setDirection(DcMotor.Direction.FORWARD);
        drive(1, 0.8, 0.8, 0.8, 0.8);
    }

    private void drive(double duration, double frontLeftPower, double frontRightPower, double backLeftPower, double backRightPower) {
        ElapsedTime time = new ElapsedTime();
        while (time.seconds() < duration) {
            front_left.setPower(frontLeftPower);
            front_right.setPower(frontRightPower);
            back_left.setPower(backLeftPower);
            back_right.setPower(backRightPower);
        }
        setDriveStop();
    }

    public void setDriveStop () {
        front_left.setPower(0);
        front_right.setPower(0);
        back_left.setPower(0);
        back_right.setPower(0);
    }}