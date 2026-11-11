
package org.firstinspires.ftc.teamcode.mechanisms;

import static org.firstinspires.ftc.teamcode.mechanisms.shooter.ShooterConstants.TARGET_RPM;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.drive.Drive;
import org.firstinspires.ftc.teamcode.mechanisms.indexer.Indexer;
import org.firstinspires.ftc.teamcode.mechanisms.intake.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.shooter.Shooter;
import org.firstinspires.ftc.teamcode.mechanisms.vision.Vision;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

@TeleOp (name = "Orchestrator")
public class SuperOpMode extends OpMode {

    private final Drive drive = new Drive();
    private final Indexer indexer = new Indexer();
    private final Shooter shooter = new Shooter();

    private Vision vision;
    private Intake intake;

    private boolean intakeOn = false;
    private boolean lastA = false;

    private boolean shooterOn = false;
    private boolean lastRightBumper = false;

    private static final double SHOOTER_TOLERANCE_RPM = 75;

    @Override
    public void init() {
        drive.init(hardwareMap);
        indexer.init(hardwareMap, telemetry);
        shooter.init(hardwareMap);

        intake = new Intake(hardwareMap);
        vision = new Vision(hardwareMap);

        telemetry.addLine("SuperOpMode initialized");
        telemetry.update();
    }

    @Override
    public void loop() {

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

        // Intake toggle (press detection)
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

        // Shooter toggle (press detection)
        if (gamepad1.right_bumper) {
            shooterOn = !shooterOn;

            if (shooterOn) {
                shooter.shootAtRPM(TARGET_RPM);
            } else {
                shooter.stop();
            }
        }

        if (shooterOn) {
            shooter.update();
        }


        lastRightBumper = gamepad1.right_bumper;

        // Shooter readiness
        double currentRPM = shooter.getRPM();

        boolean shooterReady =
                shooterOn &&
                        Math.abs(currentRPM - TARGET_RPM) <= SHOOTER_TOLERANCE_RPM;

        // Indexer: manual control OR automatic feeding
        boolean indexerOn = gamepad1.left_bumper || shooterReady;

        // Only ONE indexer command per loop
        indexer.indexerLoop(indexerOn);

        // Telemetry
        telemetry.addData("Drive Mode", "FIELD CENTRIC");

        telemetry.addData(
                "Heading",
                "%.1f degrees",
                drive.getHeadingDegrees()
        );

        telemetry.addData("Intake", intakeOn ? "ON" : "OFF");
        telemetry.addData("Shooter", shooterOn ? "ON" : "OFF");
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
    }

    @Override
    public void stop() {
        drive.stop();
        intake.stopMotor();
        intake.stopServos();
        indexer.indexerLoop(false);
        shooter.stop();
    }
}
