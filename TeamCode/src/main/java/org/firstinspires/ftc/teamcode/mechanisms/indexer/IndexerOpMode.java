package org.firstinspires.ftc.teamcode.mechanisms.indexer;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "IndexerOpMode", group = "TeleOp")
public class IndexerOpMode extends OpMode {
    Indexer indexer = new Indexer();

    @Override
    public void init() {
        indexer.init(hardwareMap);
    }

    @Override
    public void loop() {
        indexer.indexerLoop(gamepad1.y);
    }
}