package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.seattlesolvers.solverslib.hardware.motors.Motor;

import org.firstinspires.ftc.teamcode.mechanisms.drive.Drive;

@TeleOp(name = "SuperOpMode", group = "TeleOp")
public class SuperOpMode extends OpMode {

    private final Drive drive = new Drive();
    private final Indexer indexer = new Indexer();
    private final Shooter shooter = new Shooter();
    public CRServo leftintakeServo;
    public CRServo rightintakeServo;
    private Motor intakeMotor;

    private final ElapsedTime shooterTimer = new ElapsedTime();

    private Vision vision;
    private Intake intake;

    private boolean intakeOn = false;
    private boolean lastA = false;

    private boolean shooterOn = false;
    private boolean lastRightBumper = false;
    private boolean shooterTimerStarted = false;

    private static final double SHOOTER_SPINUP_SECONDS = 0.5;

    @Override
    public void init() {

    }

    @Override
    public void loop() {

    }

    public class Autonomousjava {
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

    }
}
