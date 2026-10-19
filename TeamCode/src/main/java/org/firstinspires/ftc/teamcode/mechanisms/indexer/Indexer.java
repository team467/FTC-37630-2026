package org.firstinspires.ftc.teamcode.mechanisms.indexer;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Indexer {
    private Servo Servo;


    public void init(HardwareMap hwMap) {
        Servo = hwMap.get(Servo.class, "Servo");

    }

    public void setPosition(double position) {
        Servo.setPosition(position);

    }
}