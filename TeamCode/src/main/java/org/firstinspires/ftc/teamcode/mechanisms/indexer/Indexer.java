package org.firstinspires.ftc.teamcode.mechanisms.indexer;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.CRServo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Indexer extends SubsystemBase {

    private CRServo windmillServo;
    private Telemetry telemetry;

    private static final double POWER = 1.0;

    public Indexer(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;

        windmillServo = new CRServo(hardwareMap, "windmillServo");
        windmillServo.set(0);
    }

    public void start() {
        windmillServo.set(-POWER);
    }

    public void stop() {
        windmillServo.set(0);
    }

    @Override
    public void periodic() {
        telemetry.addData("Indexer Power", windmillServo.getRawPower());
    }
}
