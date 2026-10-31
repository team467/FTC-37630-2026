package org.firstinspires.ftc.teamcode.mechanisms.shooter;

import static org.firstinspires.ftc.teamcode.mechanisms.shooter.ShooterConstants.CURRENT_LIMIT;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Shooter extends SubsystemBase {

    private Motor shooterMotor;
    private Telemetry telemetry;

    public void init(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;

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

    public void updateTelemetry() {
        telemetry.addData("Shooter Motor Power", shooterMotor.get());
        telemetry.addData("Shooter Motor Raw Power", shooterMotor.getRawPower());
        telemetry.addData("Shooter Motor Position", shooterMotor.getCurrentPosition());
        telemetry.addData("Shooter Motor Velocity", shooterMotor.getRate());
    }
}
