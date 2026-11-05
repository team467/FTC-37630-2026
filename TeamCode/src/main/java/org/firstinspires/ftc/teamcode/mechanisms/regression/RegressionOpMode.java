package org.firstinspires.ftc.teamcode.mechanisms.regression;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.regression.Regression;
import org.firstinspires.ftc.teamcode.mechanisms.shooter.Shooter;

@TeleOp(name = "Regression Test")
public class RegressionOpMode extends OpMode {

    private Shooter shooter;

    private double distance = 10;
    private double targetRPM = 0;

    private boolean lastLeft;
    private boolean lastRight;
    private boolean lastA;
    private boolean lastB;

    @Override
    public void init() {
        shooter = new Shooter();
        shooter.init(hardwareMap);
    }

    @Override
    public void loop() {

        if (gamepad1.dpad_right && !lastRight) {
            distance++;
        }

        if (gamepad1.dpad_left && !lastLeft) {
            distance = Math.max(0, distance - 1);
        }

        if (gamepad1.a && !lastA) {
            targetRPM = Regression.calculateRPM(distance);
            shooter.shootAtRPM(targetRPM);
        }

        if (gamepad1.b && !lastB) {
            shooter.stop();
            targetRPM = 0;
        }

        // IMPORTANT: Update velocity controller EVERY loop
        shooter.update();

        telemetry.addData("Distance", distance);
        telemetry.addData("Target RPM", targetRPM);
        telemetry.addData("Actual RPM", shooter.getRPM());
        telemetry.addData("Motor Power", shooter.getMotorPower());
        telemetry.addData("CPR", shooter.getCPR());
        telemetry.addData("Max TPS", shooter.getMaxTicksPerSecond());
        telemetry.addData("At Target", shooter.isAtTargetVelocity());

        telemetry.update();

        lastLeft = gamepad1.dpad_left;
        lastRight = gamepad1.dpad_right;
        lastA = gamepad1.a;
        lastB = gamepad1.b;
    }

    @Override
    public void stop() {
        shooter.stop();
    }
}

// D-Pad Left = Decrease distance by 1 inch
// D-Pad Right = Increase distance by 1 inch
// A = Calculate RPM
// B = Stop Shooter
