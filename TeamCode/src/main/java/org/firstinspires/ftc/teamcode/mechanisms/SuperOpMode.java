
package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.mechanisms.drive.Drive;
import org.firstinspires.ftc.teamcode.mechanisms.indexer.Indexer;
import org.firstinspires.ftc.teamcode.mechanisms.intake.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.shooter.Shooter;

@TeleOp(name = "SuperOpMode", group = "TeleOp")
public class SuperOpMode extends OpMode {

    private final Drive drive = new Drive();
    private final Indexer indexer = new Indexer();
    private final Shooter shooter = new Shooter();
    private final ElapsedTime shooterTimer = new ElapsedTime();

    private Intake intake;

    private boolean intakeOn = false;
    private boolean lastA = false;

    private boolean shooterOn = false;
    private boolean lastRightBumper = false;
    private boolean shooterTimerStarted = false;

    private static final double SHOOTER_SPINUP_SECONDS = 0.5;

    @Override
    public void init() {
        drive.init(hardwareMap);
        indexer.init(hardwareMap, telemetry);
        shooter.init(hardwareMap);
        intake = new Intake(hardwareMap);

        telemetry.addLine("SuperOpMode initialized");
        telemetry.update();
    }

    @Override
    public void loop() {
        // Driving
        drive.driveFieldRelative(
                -gamepad1.left_stick_y,
                gamepad1.left_stick_x,
                gamepad1.right_stick_x
        );

        // Intake toggle: A
        boolean a = gamepad1.a;

        if (a && !lastA) {
            intakeOn = !intakeOn;
        }

        lastA = a;

        if (intakeOn) {
            intake.runMotor();
            intake.runServos();
        } else {
            intake.stopMotor();
            intake.stopServos();
        }

        // Shooter toggle: right bumper
        if (gamepad1.right_bumper && !lastRightBumper) {
            shooterOn = !shooterOn;

            if (shooterOn) {
                shooter.start();
                shooterTimer.reset();
                shooterTimerStarted = true;
            } else {
                shooter.stop();
                shooterTimerStarted = false;
            }
        }

        lastRightBumper = gamepad1.right_bumper;

        // Indexer: X runs only after shooter spin-up delay
        boolean shooterReady =
                shooterOn
                        && shooterTimerStarted
                        && shooterTimer.seconds() >= SHOOTER_SPINUP_SECONDS;

        indexer.indexerLoop(gamepad1.x && shooterReady);

        // Telemetry
        telemetry.addData("Intake", intakeOn ? "ON" : "OFF");
        telemetry.addData("Shooter", shooterOn ? "ON" : "OFF");
        telemetry.addData("Shooter Ready", shooterReady ? "YES" : "SPINNING UP");
        telemetry.addData(
                "Indexer",
                gamepad1.x && shooterReady ? "ON" : "OFF"
        );
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
