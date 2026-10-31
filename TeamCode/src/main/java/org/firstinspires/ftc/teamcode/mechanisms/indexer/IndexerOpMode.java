
package org.firstinspires.ftc.teamcode.mechanisms.indexer;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "IndexerOpMode", group = "TeleOp")
public class IndexerOpMode extends OpMode {

    private final Indexer indexer = new Indexer();

    @Override
    public void init() {
        indexer.init(hardwareMap, telemetry);
    }

    @Override
    public void loop() {
        indexer.indexerLoop(gamepad1.x);
        indexer.updateTelemetry();
        telemetry.update();
    }
}