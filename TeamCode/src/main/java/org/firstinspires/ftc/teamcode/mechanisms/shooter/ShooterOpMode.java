package org.firstinspires.ftc.teamcode.mechanisms.shooter;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

@TeleOp(name = "Shooter OpMode")
public class ShooterOpMode extends CommandOpMode {
    private Shooter shooter;
    private GamepadEx driverOp;

    @Override
    public void initialize() {
        shooter = new Shooter(hardwareMap, telemetry);
        driverOp = new GamepadEx(gamepad1);

        driverOp.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
                .whileHeld(new InstantCommand(shooter::start, shooter))
                .whenReleased(new InstantCommand(shooter::stop, shooter));
    }

    @Override
    public void run() {
        super.run();
        telemetry.update();
    }
}
