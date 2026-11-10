package org.firstinspires.ftc.teamcode.mechanisms.drive;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
import com.seattlesolvers.solverslib.hardware.motors.Motor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class Drive {
    MecanumDrive mecanumDrive;
    IMU imu;
    Telemetry telemetry;

    Motor frontLeftMotor;
    Motor frontRightMotor;
    Motor backLeftMotor;
    Motor backRightMotor;

    public void init(HardwareMap hwMap, Telemetry telemetry) {
        this.telemetry = telemetry;

        frontLeftMotor = new Motor(hwMap, "frontLeftMotor");
        frontRightMotor = new Motor(hwMap, "frontRightMotor");
        backLeftMotor = new Motor(hwMap, "backLeftMotor");
        backRightMotor = new Motor(hwMap, "backRightMotor");

        mecanumDrive = new MecanumDrive(frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor);

        imu = hwMap.get(IMU.class, "imu");
    }

    public void driveFieldRelative(double forward, double strafe, double rotate) {
        mecanumDrive.driveFieldCentric(strafe, forward, rotate, 0);
    }

    public void updateTelemetry() {
        telemetry.addData("Front Left Power", frontLeftMotor.get());
        telemetry.addData("Front Left Position", frontLeftMotor.getCurrentPosition());
        telemetry.addData("Front Left Velocity", frontLeftMotor.getRate());

        telemetry.addData("Front Right Power", frontRightMotor.get());
        telemetry.addData("Front Right Position", frontRightMotor.getCurrentPosition());
        telemetry.addData("Front Right Velocity", frontRightMotor.getRate());

        telemetry.addData("Back Left Power", backLeftMotor.get());
        telemetry.addData("Back Left Position", backLeftMotor.getCurrentPosition());
        telemetry.addData("Back Left Velocity", backLeftMotor.getRate());

        telemetry.addData("Back Right Power", backRightMotor.get());
        telemetry.addData("Back Right Position", backRightMotor.getCurrentPosition());
        telemetry.addData("Back Right Velocity", backRightMotor.getRate());

        telemetry.addData("Heading", imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));
    }
}
