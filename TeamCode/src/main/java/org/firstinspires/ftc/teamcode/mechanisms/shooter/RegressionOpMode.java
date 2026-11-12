
package org.firstinspires.ftc.teamcode.mechanisms.shooter;

import static org.firstinspires.ftc.teamcode.mechanisms.shooter.ShooterConstants.TARGET_RPM;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.drive.Drive;
import org.firstinspires.ftc.teamcode.mechanisms.indexer.Indexer;
import org.firstinspires.ftc.teamcode.mechanisms.intake.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.vision.Vision;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@TeleOp(name = "Regression Test", group = "Testing")
public class RegressionOpMode extends OpMode {

    private Vision vision;
    private Shooter shooter;

    private double targetRPM = 0;
    private boolean shooterOn = false;

    private boolean lastUp;
    private boolean lastDown;
    private boolean lastLeft;
    private boolean lastRight;
    private boolean lastX = true;
    private boolean lastY;

    private final Drive drive = new Drive();
    private final Indexer indexer = new Indexer();

    private Intake intake;
    private boolean intakeOn = false;

    private boolean lastA = false;
    private boolean lastRightBumper = false;

    private static final double SHOOTER_TOLERANCE_RPM = 75;

    private static final double RPM_STEP = 1;
    private static final double P_STEP = 0.0001;

    // DISTANCE LOGGING
    private final List<String> distanceSamples = new ArrayList<>();
    private boolean lastBack = false;
    private int sampleCount = 0;

    @Override
    public void init() {

        shooter = new Shooter();
        shooter.init(hardwareMap);

        vision = new Vision(hardwareMap);

        drive.init(hardwareMap);
        indexer.init(hardwareMap, telemetry);

        intake = new Intake(hardwareMap);

        telemetry.addLine("RegressionOpMode initialized");
        telemetry.update();
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
        if (!lastX) {
            shooterOn = true;
            shooter.shootAtRPM(targetRPM);
        }

        // Stop shooter
        if (gamepad1.y && !lastY) {
            shooterOn = false;
            shooter.stop();
        }

        // Vision
        AprilTagDetection target = vision.getBestDetection();

        if (target != null) {
            drive.setPose(target.robotPose);
        }

        // Field-centric driving
        drive.driveFieldRelative(
                -gamepad1.left_stick_y,
                -gamepad1.left_stick_x,
                gamepad1.right_stick_x
        );

        // Intake toggle
        if (gamepad1.a && !lastA) {
            intakeOn = !intakeOn;
        }

        lastA = gamepad1.a;

        if (intakeOn) {
            intake.runMotor();
            intake.runServos();
        } else {
            intake.stopMotor();
            intake.stopServos();
        }

        // Shooter toggle
        if (gamepad1.right_bumper && !lastRightBumper) {

            shooterOn = !shooterOn;

            if (shooterOn) {
                targetRPM = TARGET_RPM;
                shooter.shootAtRPM(TARGET_RPM);
            } else {
                shooter.stop();
            }
        }

        lastRightBumper = gamepad1.right_bumper;

        // Update controller
        if (shooterOn) {
            shooter.update();
        }

        // Shooter telemetry values
        double currentRPM = shooter.getRPM();
        double error = targetRPM - currentRPM;

        boolean shooterReady =
                shooterOn &&
                        Math.abs(currentRPM - targetRPM)
                                <= SHOOTER_TOLERANCE_RPM;

        // Indexer: manual control OR automatic feeding
        boolean indexerOn = gamepad1.left_bumper || shooterReady;

        indexer.indexerLoop(indexerOn);

        // DISTANCE LOGGING
        double hiveDistance = vision.getHiveDistance();

        if (gamepad1.back && !lastBack) {

            if (Double.isFinite(hiveDistance) && hiveDistance > 0) {

                String sample = String.format(
                        Locale.US,
                        "Sample %d | Distance: %.2f in | Target RPM: %.1f | Actual RPM: %.1f",
                        ++sampleCount,
                        hiveDistance,
                        targetRPM,
                        currentRPM
                );

                distanceSamples.add(sample);
                telemetry.log().add(sample);

            } else {
                telemetry.log().add(
                        "Invalid AprilTag distance. Sample skipped."
                );
            }
        }

        lastBack = gamepad1.back;

        // Shooter PID telemetry
        telemetry.addLine("=== SHOOTER PID TUNING ===");

        telemetry.addData("Target RPM", "%.0f", targetRPM);
        telemetry.addData("Actual RPM", "%.0f", currentRPM);
        telemetry.addData("RPM Error", "%.0f", error);

        telemetry.addData(
                "Motor Power",
                "%.3f",
                shooter.getMotorPower()
        );

        telemetry.addData(
                "Velocity TPS",
                "%.1f",
                shooter.getVelocityTicksPerSecond()
        );

        telemetry.addData("At Target", shooter.isAtTargetVelocity());

        telemetry.addLine("----------------------");

        telemetry.addData("Shooter", shooterOn ? "ON" : "OFF");
        telemetry.addData("Distance to Hive", "%.2f in", hiveDistance);

        telemetry.addData("Logged Samples", sampleCount);

        // Drive telemetry
        telemetry.addData("Drive Mode", "FIELD CENTRIC");

        telemetry.addData(
                "Heading",
                "%.1f degrees",
                drive.getHeadingDegrees()
        );

        telemetry.addData("Intake", intakeOn ? "ON" : "OFF");
        telemetry.addData("Shooter Ready", shooterReady);
        telemetry.addData("Indexer", indexerOn ? "ON" : "OFF");

        telemetry.addData("Shooter RPM", "%.0f RPM", currentRPM);

        telemetry.addData(
                "Shooter Velocity",
                "%.0f ticks/sec",
                shooter.getVelocityTicksPerSecond()
        );

        telemetry.addData("Pose", drive.getPose());

        telemetry.update();

        // Update button states
        lastUp = gamepad1.dpad_up;
        lastDown = gamepad1.dpad_down;
        lastLeft = gamepad1.dpad_left;
        lastRight = gamepad1.dpad_right;

        if (gamepad1.x) {
            lastX = !lastX;
        }

        lastY = gamepad1.y;
    }

    @Override
    public void stop() {
        shooter.stop();
    }
}