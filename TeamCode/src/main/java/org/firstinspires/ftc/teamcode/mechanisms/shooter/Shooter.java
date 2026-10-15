package org.firstinspires.ftc.teamcode.mechanisms.shooter;

import static org.firstinspires.ftc.teamcode.mechanisms.shooter.ShooterConstants.CURRENT_LIMIT;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Shooter {

    private DcMotor shooterMotor;

    public void init(HardwareMap hardwareMap) {
        shooterMotor = hardwareMap.get(DcMotor.class, "shooterMotor");
        shooterMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        shooterMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void start() {
        shooterMotor.setPower(CURRENT_LIMIT);
    }

    public void stop() {
        shooterMotor.setPower(0.0);
    }
}