package org.firstinspires.ftc.teamcode.mechanisms.indexer;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Indexer OpMode", group = "Mechanisms")
public class IndexerOpMode extends OpMode {
    private final Indexer indexer = new Indexer();

    @Override
    public void init() {
        indexer.init(hardwareMap);
        telemetry.addData("Status", "Indexer Initialized");
        telemetry.update();
    }

    @Override
    public void loop() {
        double targetPosition = gamepad1.y
                ? IndexerConstants.INDEXER_SCORE_POSITION
                : IndexerConstants.INDEXER_HOME_POSITION;

        indexer.setPosition(targetPosition);

        telemetry.addData("Y Button Pressed", gamepad1.y);
        telemetry.addData("Indexer Position", targetPosition);
        telemetry.update();
    }
}