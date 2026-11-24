package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.commands.DriveCommand;
import org.firstinspires.ftc.teamcode.commands.RunIntakeCommand;
import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.drive.Drive;

@TeleOp(name = "Command TeleOp")
public class MainTeleOp extends CommandOpMode {
    private Drive drive;
    private Intake intake;
    private GamepadEx driver;

    @Override
    public void initialize() {
        drive = new Drive(hardwareMap, telemetry);
        intake = new Intake(hardwareMap, telemetry);
        driver = new GamepadEx(gamepad1);

        // Sticks drive the robot. If a direction is backwards, flip the minus sign.
        drive.setDefaultCommand(new DriveCommand(
                drive,
                () -> -driver.getLeftY(),
                () -> driver.getLeftX(),
                () -> driver.getRightX()));

        // Hold right bumper to run the intake
        driver.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
                .whileHeld(new RunIntakeCommand(intake));
    }

    @Override
    public void run() {
        super.run();         // runs the scheduler: periodic(), commands, button bindings
        telemetry.update();
    }
}