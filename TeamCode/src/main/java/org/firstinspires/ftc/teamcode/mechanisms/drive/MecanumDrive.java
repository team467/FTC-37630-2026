package org.firstinspires.ftc.teamcode.mechanisms.drive;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

public class MecanumDrive {
    private DcMotor frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor;
    private IMU imu;

    public void init(HardwareMap haMap) {
        frontLeftMotor = haMap.get(DcMotorEx.class, "frontLeftMotor");
        frontRightMotor = haMap.get(DcMotorEx.class, "frontRightMotor");
        backLeftMotor = haMap.get(DcMotorEx.class, "backLeftMotor");
        backRightMotor = haMap.get(DcMotorEx.class, "backRightMotor");
    }
}
