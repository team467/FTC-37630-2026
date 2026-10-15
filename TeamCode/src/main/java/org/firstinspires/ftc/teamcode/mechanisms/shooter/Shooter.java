package org.firstinspires.ftc.teamcode.mechanisms.shooter;

import static org.firstinspires.ftc.teamcode.mechanisms.shooter.ShooterConstants.CURRENT_LIMIT;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;

public class Shooter extends SubsystemBase {

    private Motor shooterMotor;

    public void init(HardwareMap hardwareMap) {

        shooterMotor = new Motor(hardwareMap, "shooterMotor");
        shooterMotor.setRunMode(Motor.RunMode.VelocityControl);
        shooterMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

    }

    public void start() {
        shooterMotor.set(CURRENT_LIMIT);
    }

    public void stop() {
        shooterMotor.set(0.0);
    }
}