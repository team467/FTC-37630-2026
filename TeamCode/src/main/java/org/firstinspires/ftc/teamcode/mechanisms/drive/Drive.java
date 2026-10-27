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
package org.firstinspires.ftc.teamcode.mechanisms.drive;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
import com.seattlesolvers.solverslib.geometry.Pose2d;
import com.seattlesolvers.solverslib.geometry.Rotation2d;
import com.seattlesolvers.solverslib.geometry.Translation2d;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.kinematics.wpilibkinematics.MecanumDriveKinematics;
import com.seattlesolvers.solverslib.kinematics.wpilibkinematics.MecanumDriveOdometry;
import com.seattlesolvers.solverslib.kinematics.wpilibkinematics.MecanumDriveWheelSpeeds;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class Drive {

    private static final double WHEEL_RADIUS_IN = 2.04724;
    private static final double TICKS_PER_REV = 28;
    private static final double GEAR_RATIO = 19;
    private static final double WHEEL_OFFSET_X_IN = 4.961;
    private static final double WHEEL_OFFSET_Y_IN = 9.670705;
    private static final boolean RIGHT_MOTORS_IDENTICAL_WIRING = true;

    private static final double METERS_PER_TICK =
            (2.0 * Math.PI * WHEEL_RADIUS_IN * 0.0254)
                    / (TICKS_PER_REV * GEAR_RATIO);

    private static final double RIGHT_ENCODER_SIGN =
            RIGHT_MOTORS_IDENTICAL_WIRING ? -1.0 : 1.0;

    private MecanumDrive mecanumDrive;
    private IMU imu;
    private MecanumDriveKinematics kinematics;
    private MecanumDriveOdometry odometry;

    private Motor frontLeftMotor;
    private Motor frontRightMotor;
    private Motor backLeftMotor;
    private Motor backRightMotor;

    private double lastTimestamp;
    private double lastFrontLeftTicks;
    private double lastFrontRightTicks;
    private double lastBackLeftTicks;
    private double lastBackRightTicks;

    public void init(HardwareMap hwMap) {
        frontLeftMotor = new Motor(hwMap, "frontLeftMotor");
        frontRightMotor = new Motor(hwMap, "frontRightMotor");
        backLeftMotor = new Motor(hwMap, "backLeftMotor");
        backRightMotor = new Motor(hwMap, "backRightMotor");

        frontLeftMotor.setRunMode(Motor.RunMode.VelocityControl);

        mecanumDrive = new MecanumDrive(
                frontLeftMotor,
                frontRightMotor,
                backLeftMotor,
                backRightMotor
        );

        //mecanumDrive.setRightSideInverted(!RIGHT_MOTORS_IDENTICAL_WIRING);

        imu = hwMap.get(IMU.class, "imu");
        imu.initialize(new IMU.Parameters(
                new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.UP,
                        RevHubOrientationOnRobot.UsbFacingDirection.FORWARD
                )
        ));

        double x = WHEEL_OFFSET_X_IN * 0.0254;
        double y = WHEEL_OFFSET_Y_IN * 0.0254;

        kinematics = new MecanumDriveKinematics(
                new Translation2d(x, y),
                new Translation2d(x, -y),
                new Translation2d(-x, y),
                new Translation2d(-x, -y)
        );

        odometry = new MecanumDriveOdometry(
                kinematics,
                getHeadingRotation()
        );

        resetEncoderTracking();
    }

    public void driveFieldRelative(double forward, double strafe, double rotate) {
        mecanumDrive.driveFieldCentric(
                -strafe,
                forward,
                rotate,
                0
        );
    }

    public void updateOdometry() {
        double now = System.nanoTime() / 1_000_000_000.0;
        double dt = now - lastTimestamp;

        if (dt <= 0.0) {
            return;
        }

        double frontLeftTicks = frontLeftMotor.getCurrentPosition();
        double frontRightTicks = frontRightMotor.getCurrentPosition();
        double backLeftTicks = backLeftMotor.getCurrentPosition();
        double backRightTicks = backRightMotor.getCurrentPosition();

        double frontLeftDelta = frontLeftTicks - lastFrontLeftTicks;
        double frontRightDelta = frontRightTicks - lastFrontRightTicks;
        double backLeftDelta = backLeftTicks - lastBackLeftTicks;
        double backRightDelta = backRightTicks - lastBackRightTicks;

        lastFrontLeftTicks = frontLeftTicks;
        lastFrontRightTicks = frontRightTicks;
        lastBackLeftTicks = backLeftTicks;
        lastBackRightTicks = backRightTicks;
        lastTimestamp = now;

        MecanumDriveWheelSpeeds wheelSpeeds = new MecanumDriveWheelSpeeds(
                frontLeftDelta * METERS_PER_TICK / dt,
                frontRightDelta * METERS_PER_TICK * RIGHT_ENCODER_SIGN / dt,
                backLeftDelta * METERS_PER_TICK / dt,
                backRightDelta * METERS_PER_TICK * RIGHT_ENCODER_SIGN / dt
        );

        odometry.updateWithTime(
                now,
                getHeadingRotation(),
                wheelSpeeds
        );
    }

    public Pose2d getPose() {
        return odometry.getPoseMeters();
    }

    public void setPose(double xMeters, double yMeters, double headingDegrees) {
        odometry.resetPosition(
                new Pose2d(
                        xMeters,
                        yMeters,
                        Rotation2d.fromDegrees(headingDegrees)
                ),
                getHeadingRotation()
        );

        resetEncoderTracking();
    }

    public void resetEncoderTracking() {
        frontLeftMotor.resetEncoder();
        frontRightMotor.resetEncoder();
        backLeftMotor.resetEncoder();
        backRightMotor.resetEncoder();

        lastFrontLeftTicks = 0.0;
        lastFrontRightTicks = 0.0;
        lastBackLeftTicks = 0.0;
        lastBackRightTicks = 0.0;
        lastTimestamp = System.nanoTime() / 1_000_000_000.0;
    }

    private double getHeadingDegrees() {
        return imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);
    }

    private Rotation2d getHeadingRotation() {
        return Rotation2d.fromDegrees(getHeadingDegrees());
    }
}