package org.firstinspires.ftc.teamcode.mechanisms.indexer;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

@TeleOp(name = "IndexerOpMode", group = "TeleOp")
public class IndexerOpMode extends CommandOpMode {
    private Indexer indexer;
    private GamepadEx driverOp;

    @Override
    public void initialize() {
        indexer = new Indexer(hardwareMap, telemetry);
        driverOp = new GamepadEx(gamepad1);

        driverOp.getGamepadButton(GamepadKeys.Button.X)
                .whileHeld(new InstantCommand(indexer::start, indexer))
                .whenReleased(new InstantCommand(indexer::stop, indexer));
    }

    @Override
    public void run() {
        super.run();
        telemetry.update();
    }
}
