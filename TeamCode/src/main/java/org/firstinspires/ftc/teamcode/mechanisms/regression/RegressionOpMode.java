
package org.firstinspires.ftc.teamcode.mechanisms.regression;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.shooter.Shooter;

@TeleOp(name = "Regression Test", group = "Testing")
public class RegressionOpMode extends OpMode {

    private Shooter shooter;

    private double targetRPM = 1000;

    private boolean shooterOn = false;

    private boolean lastUp;
    private boolean lastDown;
    private boolean lastLeft;
    private boolean lastRight;
    private boolean lastX;
    private boolean lastY;

    private static final double RPM_STEP = 100;
    private static final double P_STEP = 0.0001;

    @Override
    public void init() {
        shooter = new Shooter();
        shooter.init(hardwareMap);
    }

    @Override
    public void loop() {

        // Adjust target RPM
        if (gamepad1.dpad_up && !lastUp) {
            targetRPM += RPM_STEP;
        }

        if (gamepad1.dpad_down && !lastDown) {
            targetRPM = Math.max(0, targetRPM - RPM_STEP);
        }

        // Start shooter
        if (gamepad1.x && !lastX) {
            shooterOn = true;
            shooter.shootAtRPM(targetRPM);
        }

        // Stop shooter
        if (gamepad1.y && !lastY) {
            shooterOn = false;
            shooter.stop();
        }

        // Update controller every loop
        if (shooterOn) {
            shooter.update();
        }

        double actualRPM = shooter.getRPM();
        double error = targetRPM - actualRPM;

        telemetry.addLine("=== SHOOTER PID TUNING ===");

        telemetry.addData("Target RPM", "%.0f", targetRPM);
        telemetry.addData("Actual RPM", "%.0f", actualRPM);
        telemetry.addData("RPM Error", "%.0f", error);

        telemetry.addData("Motor Power", "%.3f",
                shooter.getMotorPower());

        telemetry.addData("Velocity TPS", "%.1f",
                shooter.getVelocityTicksPerSecond());

        telemetry.addData("At Target",
                shooter.isAtTargetVelocity());

        telemetry.addLine("----------------------");
        telemetry.addData("Shooter", shooterOn ? "ON" : "OFF");

        telemetry.update();

        lastUp = gamepad1.dpad_up;
        lastDown = gamepad1.dpad_down;
        lastLeft = gamepad1.dpad_left;
        lastRight = gamepad1.dpad_right;
        lastX = gamepad1.x;
        lastY = gamepad1.y;
    }

    @Override
    public void stop() {
        shooter.stop();
    }
}
