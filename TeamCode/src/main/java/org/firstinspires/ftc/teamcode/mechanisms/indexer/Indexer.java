package org.firstinspires.ftc.teamcode.mechanisms.indexer;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Indexer {
    private Servo windmillServo;
    private Telemetry telemetry;

    private boolean previousOn = false;
    private double targetPosition = 0.0;

    // Change this to set the size of each step.
    private static final double STEP = 0.25;

    public void init(HardwareMap hwMap, Telemetry telemetry) {
        this.telemetry = telemetry;
        windmillServo = hwMap.get(Servo.class, "windmillServo");
        this.telemetry = telemetry;

        windmillServo.setPosition(targetPosition);
    }

    public void indexerLoop(boolean on) {
        if (on && !previousOn) {
            targetPosition += STEP;

            if (targetPosition > 1.0) {
                targetPosition = 0.0;
            }

            windmillServo.setPosition(targetPosition);
        }

        previousOn = on;

        telemetry.addData("Target Position", targetPosition);
        telemetry.update();
    }
}