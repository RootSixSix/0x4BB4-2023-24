package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


import org.firstinspires.ftc.teamcode.util.Utility;

/**
 * This is an example minimal implementation of the mecanum drivetrain
 * for demonstration purposes.  Not tested and not guaranteed to be bug free.
 *
 * @author Brandon Gong
 */
@TeleOp(name="Teleop", group="19380")
public class MecanumDriveSample extends OpMode {

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
    double driveMultiplicative = 0.8;
    boolean turboMode = false;
    double rotationMultiplicative = 0.75;

    /*
     * These are the variables used to ramp the power
     * to the motors up and down.  The curr_ variables
     * are the current power to the respective motors.
     * After calculating the powers based on the controller's
     * sticks, we compare the calculated power to the
     * curr_ power and, if the calculated power is
     * greater than the curr_ power, we add accel to
     * the curr_ power; otherwise, we subtract deccel
     * from the curr_ power.  This should reduce wheel
     * spin on acceleration and skid from deccelleration.
     */
    double accel = 0.02;
    double deccel = 0.02;
    double curr_leftFrontPower  = 0;
    double curr_rightFrontPower = 0;
    double curr_leftBackPower   = 0;
    double curr_rightBackPower  = 0;

    @Override
    public void init() {

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
    }

    @Override
    public void loop() {

        // Mecanum drive is controlled with three axes: drive (front-and-back),
        // strafe (left-and-right), and twist (rotating the whole chassis).
        double drive  = Utility.deadStick(-gamepad1.left_stick_y);
        double strafe = Utility.deadStick(gamepad1.left_stick_x);
        double twist  = Utility.deadStick(gamepad1.right_stick_x);

        /*
         * If we had a gyro and wanted to do field-oriented control, here
         * is where we would implement it.
         *
         * The idea is fairly simple; we have a robot-oriented Cartesian (x,y)
         * coordinate (strafe, drive), and we just rotate it by the gyro
         * reading minus the offset that we read in the init() method.
         * Some rough pseudocode demonstrating:
         *
         * if Field Oriented Control:
         *     get gyro heading
         *     subtract initial offset from heading
         *     convert heading to radians (if necessary)
         *     new strafe = strafe * cos(heading) - drive * sin(heading)
         *     new drive  = strafe * sin(heading) + drive * cos(heading)
         *
         * If you want more understanding on where these rotation formulas come
         * from, refer to
         * https://en.wikipedia.org/wiki/Rotation_(mathematics)#Two_dimensions
         */

        // You may need to multiply some of these by -1 to invert direction of
        // the motor.  This is not an issue with the calculations themselves.

        double leftFrontPower  = drive + strafe + (rotationMultiplicative*twist);
        double rightFrontPower = drive - strafe - (rotationMultiplicative*twist);
        double leftBackPower   = drive - strafe + (rotationMultiplicative*twist);
        double rightBackPower  = drive + strafe - (rotationMultiplicative*twist);

        if (leftFrontPower > curr_leftFrontPower) {
            curr_leftFrontPower += accel;
        } else {
            curr_leftFrontPower -= deccel;
        }
        if (rightFrontPower > curr_rightFrontPower) {
            curr_rightFrontPower += accel;
        } else {
            curr_rightFrontPower -= deccel;
        }
        if (leftBackPower >  curr_leftBackPower) {
            curr_leftBackPower += accel;
        } else {
            curr_leftBackPower -= deccel;
        }
        if (rightBackPower > curr_rightBackPower) {
            curr_rightBackPower += accel;
        } else {
            curr_rightBackPower -= deccel;
        }

        double max;
        max = Math.max(Math.abs(curr_leftFrontPower), Math.abs(curr_rightFrontPower));
        max = Math.max(max, Math.abs(curr_leftBackPower));
        max = Math.max(max, Math.abs(curr_rightBackPower));

        if (max > 1.0) {
            curr_leftFrontPower  /= max;
            curr_rightFrontPower /= max;
            curr_leftBackPower   /= max;
            curr_rightBackPower  /= max;
        }

/*
       This was in here twice.  Commented out 11/4/23

       if (max > 1.0) {
            leftFrontPower  /= max;
            rightFrontPower /= max;
            leftBackPower   /= max;
            rightBackPower  /= max;
        }
*/

        front_left.setPower(driveMultiplicative*curr_leftFrontPower);
        front_right.setPower(driveMultiplicative*curr_rightFrontPower);
        back_left.setPower(driveMultiplicative*curr_leftBackPower);
        back_right.setPower(driveMultiplicative*curr_rightBackPower);



      //  telemetry.addData("Front left/Right", "%4.2f, %4.2f", leftFrontPower, rightFrontPower);
        //telemetry.addData("Back  left/Right", "%4.2f, %4.2f", leftBackPower, rightBackPower);
        //telemetry.update();


    }
}