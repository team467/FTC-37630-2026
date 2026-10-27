
package org.firstinspires.ftc.teamcode.vision;

import android.util.Size;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;

public final class Vision implements AutoCloseable {

    private static final String CAMERA_NAME = "webcam";

    private final AprilTagProcessor processor;
    private final VisionPortal portal;

    public Vision(HardwareMap hardwareMap) {
        processor = new AprilTagProcessor.Builder()
                .setOutputUnits(
                        DistanceUnit.INCH,
                        AngleUnit.DEGREES
                )
                .build();

        portal = new VisionPortal.Builder()
                .setCamera(
                        hardwareMap.get(
                                WebcamName.class,
                                CAMERA_NAME
                        )
                )
                .setCameraResolution(new Size(640, 480))
                .addProcessor(processor)
                .build();
    }

    public AprilTagDetection getBestDetection() {
        List<AprilTagDetection> detections =
                processor.getDetections();

        AprilTagDetection best = null;

        for (AprilTagDetection detection : detections) {
            if (detection.metadata == null
                    || detection.ftcPose == null) {
                continue;
            }

            if (best == null
                    || detection.ftcPose.range
                    < best.ftcPose.range) {
                best = detection;
            }
        }

        return best;
    }

    @Override
    public void close() {
        portal.close();
    }
}