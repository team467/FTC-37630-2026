
package org.firstinspires.ftc.teamcode.mechanisms.shooter;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;

public class Shooter extends SubsystemBase {

    private Motor shooterMotor;

    public void init(HardwareMap hardwareMap) {
        shooterMotor = new Motor(hardwareMap, "shooterMotor");

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
}
