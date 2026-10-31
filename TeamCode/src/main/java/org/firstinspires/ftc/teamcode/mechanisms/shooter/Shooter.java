
package org.firstinspires.ftc.teamcode.mechanisms.shooter;

import static org.firstinspires.ftc.teamcode.mechanisms.shooter.ShooterConstants.TARGET_RPM;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

public class Shooter extends SubsystemBase {

    private MotorEx shooterMotor;

    private double targetVelocityTPS = 0;

    // Allowed velocity error (ticks per second)
    private static final double VELOCITY_TOLERANCE = 75;

    public void init(HardwareMap hardwareMap) {

        shooterMotor = new MotorEx(hardwareMap, "shooterMotor");

        shooterMotor.setRunMode(Motor.RunMode.VelocityControl);

        shooterMotor.setZeroPowerBehavior(
                Motor.ZeroPowerBehavior.BRAKE
        );
    }

    // Set target velocity in ticks per second
    public void setVelocity(double ticksPerSecond) {

        targetVelocityTPS = Math.abs(ticksPerSecond);

        shooterMotor.setVelocity(TARGET_RPM);
    }

    // Set target speed in motor RPM
    public void setVelocityRPM(double rpm) {

        double ticksPerRevolution = shooterMotor.getCPR();

        if (ticksPerRevolution <= 0) {
            return;
        }

        double ticksPerSecond =
                rpm * ticksPerRevolution / 60.0;

        setVelocity(ticksPerSecond);
    }

    // Read actual encoder velocity
    public double getVelocityTicksPerSecond() {
        return Math.abs(shooterMotor.getVelocity());
    }

    // Convert actual motor velocity to RPM
    public double getRPM() {

        double ticksPerRevolution = shooterMotor.getCPR();

        if (ticksPerRevolution <= 0) {
            return 0;
        }

        return getVelocityTicksPerSecond()
                / ticksPerRevolution * 60.0;
    }

    // Check whether shooter reached its target speed
    public boolean isAtTargetVelocity() {

        if (targetVelocityTPS <= 0) {
            return false;
        }

        return Math.abs(
                getVelocityTicksPerSecond() - targetVelocityTPS
        ) <= VELOCITY_TOLERANCE;
    }

    // Stop shooter
    public void stop() {

        targetVelocityTPS = 0;

        shooterMotor.stopMotor();
    }

    public double getTargetVelocity() {
        return targetVelocityTPS;
    }
}
