package org.firstinspires.ftc.teamcode.mechanisms.indexer;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.hardware.motors.CRServo;

public class Indexer {
    private CRServo windmillServo;
    double NextPos;
    public void init(HardwareMap hwMap) {
        windmillServo = new CRServo(hwMap, "windmillServo");
    }
    public void indexerLoop(boolean on) {
        telemetry.addData("Measured Servo Position", windmillServo.get()); // Debug Tool
        if (on) {
            NextPos = (windmillServo.get() + 0.51d) - Math.floor(windmillServo.get() + 0.51d);
            windmillServo.set(NextPos);
        }
    }
}