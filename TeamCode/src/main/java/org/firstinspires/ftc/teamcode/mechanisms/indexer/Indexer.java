package org.firstinspires.ftc.teamcode.mechanisms.indexer;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

public class Indexer {
    private ServoEx windmillServo;
    public void init(HardwareMap hwMap) {
        ServoEx windmillServo = new ServoEx(hwMap, "windmillServo");
    }
    public void IndexerLoop(boolean On) {
        telemetry.addData("Measured Servo Position", windmillServo.get()); // Debug Tool
        if (On) {
            if (windmillServo.get() == 0.00d) {windmillServo.set(1.00);}
            else {windmillServo.set(0.00);}
        }
    }
}
