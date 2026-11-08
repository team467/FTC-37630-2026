package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

@TeleOp(name = "Intake OpMode")
public class IntakeOpMode extends CommandOpMode {
    private Intake intake;
    private GamepadEx driverOp;

    @Override
    public void initialize() {
        intake = new Intake(hardwareMap, telemetry);
        driverOp = new GamepadEx(gamepad1);

        driverOp.getGamepadButton(GamepadKeys.Button.A)
                .toggleWhenPressed(
                        new InstantCommand(intake::run, intake),
                        new InstantCommand(intake::stop, intake)
                );
    }

    @Override
    public void run() {
        super.run();
        telemetry.update();
    }
}
