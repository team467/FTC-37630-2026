
package org.firstinspires.ftc.teamcode.mechanisms.shooter;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

public class Shooter extends SubsystemBase {

    private MotorEx shooterMotor;

    public void init(HardwareMap hardwareMap) {
        shooterMotor = new MotorEx(hardwareMap, "shooterMotor");

        shooterMotor.setRunMode(Motor.RunMode.RawPower);
        shooterMotor.setZeroPowerBehavior(
                Motor.ZeroPowerBehavior.BRAKE
        );
    }

    public void start() {
        shooterMotor.set(ShooterConstants.CURRENT_LIMIT);
    }

    public void stop() {
        shooterMotor.stopMotor();
    }

    // Current encoder velocity in ticks per second.
    public double getVelocityTicksPerSecond() {
        return shooterMotor.getVelocity();
    }

    // Current shooter speed in RPM.
    public double getRPM() {
        double ticksPerSecond = Math.abs(
                shooterMotor.getVelocity()
        );

        double ticksPerRevolution = shooterMotor.getCPR();

        if (ticksPerRevolution <= 0) {
            return 0.0;
        }

        return (ticksPerSecond / ticksPerRevolution) * 60.0;
    }
}
