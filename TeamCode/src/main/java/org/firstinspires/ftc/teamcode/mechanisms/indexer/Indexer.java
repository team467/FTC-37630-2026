package org.firstinspires.ftc.teamcode.mechanisms.indexer;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

public class Indexer {
    private ServoEx windmillServo;
    public void init(HardwareMap hwMap) {
        ServoEx windmillServo = new ServoEx(hwMap, "windmillServo");
    }
    public void turnWindmill(double Angle) {
        windmillServo.set(windmillServo.get() + Angle);
    }
}
