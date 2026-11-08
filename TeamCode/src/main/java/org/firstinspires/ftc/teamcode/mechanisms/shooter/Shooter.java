package org.firstinspires.ftc.teamcode.mechanisms.shooter;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

public class Shooter extends SubsystemBase {

    private MotorEx shooterMotor;

    private double targetVelocityTPS = 0;

    private static final double VELOCITY_TOLERANCE = 75;

    public void init(HardwareMap hardwareMap) {
        shooterMotor = new MotorEx(hardwareMap, "shooterMotor");

        shooterMotor.setRunMode(Motor.RunMode.VelocityControl);
        shooterMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
    }

    // Set target RPM
    public void shootAtRPM(double rpm) {
        setVelocityRPM(rpm);
    }

    // Convert RPM into ticks per second
    public void setVelocityRPM(double rpm) {
        double cpr = shooterMotor.getCPR();

        if (cpr <= 0) return;

        targetVelocityTPS = Math.abs(rpm) * cpr / 60.0;
    }

    // Update the velocity controller continuously
    public void update() {
        if (targetVelocityTPS > 0) {
            shooterMotor.setVelocity(targetVelocityTPS);
        }
    }

    public double getRPM() {
        double cpr = shooterMotor.getCPR();

        if (cpr <= 0) return 0;

        return Math.abs(shooterMotor.getVelocity()) / cpr * 60.0;
    }

    public boolean isAtTargetVelocity() {
        if (targetVelocityTPS <= 0) return false;

        return Math.abs(
                Math.abs(shooterMotor.getVelocity()) - targetVelocityTPS
        ) <= VELOCITY_TOLERANCE;
    }

    public double getTargetRPM() {
        double cpr = shooterMotor.getCPR();

        if (cpr <= 0) return 0;

        return targetVelocityTPS / cpr * 60.0;
    }

    public double getMotorPower() {
        return shooterMotor.get();
    }

    public double getCPR() {
        return shooterMotor.getCPR();
    }

    public double getMaxTicksPerSecond() {
        return shooterMotor.ACHIEVABLE_MAX_TICKS_PER_SECOND;
    }

    public void stop() {
        targetVelocityTPS = 0;
        shooterMotor.stopMotor();
    }

    public double getVelocityTicksPerSecond() {
        return Math.abs(shooterMotor.getVelocity());
    }

}