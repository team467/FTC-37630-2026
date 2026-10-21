package org.firstinspires.ftc.teamcode.mechanisms.indexer;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "IndexerOpMode", group = "TeleOp")
public class IndexerOpMode extends OpMode {
    Indexer main = new Indexer();

    @Override
    public void init() {}

    @Override
    public void loop() {
        main.IndexerLoop(gamepad1.y);
    }
}
