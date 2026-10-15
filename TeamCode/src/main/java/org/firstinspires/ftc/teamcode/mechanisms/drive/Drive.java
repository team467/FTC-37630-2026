package org.firstinspires.ftc.teamcode.mechanisms.drive;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
import com.seattlesolvers.solverslib.hardware.motors.Motor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class Drive {
    MecanumDrive mecanumDrive;
    IMU imu;
    public void init(HardwareMap hwMap) {
        Motor frontLeftMotor = new Motor(hwMap, "frontLeftMotor");
        frontLeftMotor.setRunMode(Motor.RunMode.VelocityControl);

        mecanumDrive = new MecanumDrive(
                new Motor(hwMap, "frontLeftMotor"),
                new Motor(hwMap, "frontRightMotor"),
                new Motor(hwMap, "backLeftMotor"),
                new Motor(hwMap, "backRightMotor")
        );

        imu = hwMap.get(IMU.class, "imu");
    }

    public void driveFieldRelative(double forward, double strafe, double rotate) {
        // Somehow use this for gyroAngle: imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
        mecanumDrive.driveFieldCentric(strafe, forward, rotate, 0);
    }
}
