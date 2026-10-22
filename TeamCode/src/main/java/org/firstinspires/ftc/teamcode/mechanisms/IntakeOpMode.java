package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.mechanisms.drive.Drive;

@TeleOp(name = "Intake OpMode")
public class IntakeOpMode extends OpMode {
    private Intake intake;
    private Drive drive;
    private GamepadEx driverOp;

    @Override
    public void init() {
        // Make the intake (1 motor and 2 servos)
        intake = new Intake(hardwareMap);

        // Make the drive (same as the Drive OpMode)
        drive = new Drive();
        drive.init(hardwareMap);

        // Make the gamepad
        driverOp = new GamepadEx(gamepad1);
    }

    @Override
    public void loop() {
        // Drive with the sticks (same buttons as before)
        drive.driveFieldRelative(
                gamepad1.left_stick_y,
                gamepad1.left_stick_x,
                gamepad1.right_stick_x
        );

        // Hold A to run the intake motor
        if (driverOp.getButton(GamepadKeys.Button.A)) {
            intake.runMotor();
        } else {
            intake.stopMotor();
        }

        // Hold the left trigger (L2) to run the servos
        double leftTrigger = driverOp.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER);
        if (leftTrigger > 0.5) {
            intake.runServos();
        } else {
            intake.stopServos();
        }

        telemetry.addData("Left Trigger", leftTrigger);
        telemetry.update();
    }
}
