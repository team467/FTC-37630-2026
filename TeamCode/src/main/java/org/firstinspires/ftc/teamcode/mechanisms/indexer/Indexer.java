
package org.firstinspires.ftc.teamcode.mechanisms.indexer;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Indexer {

    private CRServo windmillServo;
    private Telemetry telemetry;

    private static final double POWER = 1.0;

    public void init(HardwareMap hwMap, Telemetry telemetry) {
        this.telemetry = telemetry;

        windmillServo = hwMap.get(CRServo.class, "windmillServo");
        windmillServo.setPower(0);
    }

    public void indexerLoop(boolean on) {
        if (on) {
            windmillServo.setPower(-POWER);
        } else {
            windmillServo.setPower(0);
        }

        telemetry.addData("Indexer Running", on);
        telemetry.addData("Indexer Power", windmillServo.getPower());
    }
}
