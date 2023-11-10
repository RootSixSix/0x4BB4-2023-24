package org.firstinspires.ftc.teamcode.robot;

public class DriveData {
    private double durationSecs;
    private double frontLeftPower;
    private double frontRightPower;
    private double backLeftPower;
    private double backRightPower;

    public DriveData(double durationSecs, double frontLeftPower, double frontRightPower, double backLeftPower, double backRightPower) {
        this.durationSecs = durationSecs;
        this.frontLeftPower = frontLeftPower;
        this.frontRightPower = frontRightPower;
        this.backLeftPower = backLeftPower;
        this.backRightPower = backRightPower;
    }

    public double getDurationSecs() {
        return durationSecs;
    }

    public double getFrontLeftPower() {
        return frontLeftPower;
    }

    public double getFrontRightPower() {
        return frontRightPower;
    }

    public double getBackLeftPower() {
        return backLeftPower;
    }

    public double getBackRightPower() {
        return backRightPower;
    }
}
