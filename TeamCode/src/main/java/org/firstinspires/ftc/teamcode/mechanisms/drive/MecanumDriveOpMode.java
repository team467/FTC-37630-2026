
package org.firstinspires.ftc.teamcode.mechanisms.drive;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.vision.Vision;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

@TeleOp(name = "Drive + Vision")
public class MecanumDriveOpMode extends OpMode {

    private static final double TARGET_DISTANCE_IN = 12.0;
    private static final double DISTANCE_TOLERANCE_IN = 1.0;
    private static final double BEARING_TOLERANCE_DEG = 2.0;

    private static final double ROTATION_KP = 0.015;
    private static final double FORWARD_KP = 0.035;

    private static final double MAX_ROTATION = 0.25;
    private static final double MAX_FORWARD = 0.20;

    private final Drive drive = new Drive();
    private Vision vision;

    @Override
    public void init() {
        drive.init(hardwareMap);
        vision = new Vision(hardwareMap);

        telemetry.addLine("Vision initialized");
        telemetry.addLine("Hold A to align and approach a tag");
        telemetry.update();
    }

    @Override
    public void loop() {
        drive.updateOdometry();

        AprilTagDetection target = vision.getBestDetection();

        if (!gamepad1.a) {
            // Preserve the existing manual drive controls.
            drive.driveFieldRelative(
                    gamepad1.left_stick_y,
                    gamepad1.left_stick_x,
                    gamepad1.right_stick_x
            );

            telemetry.addData("Mode", "MANUAL");
        } else if (target == null) {
            // Never continue approaching without a visible target.
            drive.driveFieldRelative(0, 0, 0);
            telemetry.addData("Mode", "AUTO: SEARCH");
            telemetry.addLine("No valid AprilTag detected");
        } else {
            double bearing = target.ftcPose.bearing;
            double distance = target.ftcPose.range;

            double rotation = 0.0;
            double forward = 0.0;

            // Center the target horizontally.
            if (Math.abs(bearing) > BEARING_TOLERANCE_DEG) {
                rotation = Range.clip(
                        -ROTATION_KP * bearing,
                        -MAX_ROTATION,
                        MAX_ROTATION
                );
            }

            // Approach only when the target is nearly centered.
            if (Math.abs(bearing) <= 8.0
                    && distance > TARGET_DISTANCE_IN
                            + DISTANCE_TOLERANCE_IN) {

                forward = Range.clip(
                        FORWARD_KP
                                * (distance - TARGET_DISTANCE_IN),
                        0.0,
                        MAX_FORWARD
                );
            }

            // Stop advancing inside the distance tolerance.
            drive.driveFieldRelative(forward, 0, rotation);

            telemetry.addData("Mode", "AUTO: ALIGN");
            telemetry.addData("Tag ID", target.id);
            telemetry.addData("Bearing (deg)", "%.2f", bearing);
            telemetry.addData("Distance (in)", "%.2f", distance);
            telemetry.addData("Forward", "%.2f", forward);
            telemetry.addData("Rotation", "%.2f", rotation);
        }

        telemetry.addData("Odometry pose", drive.getPose());
        telemetry.update();
    }

    @Override
    public void stop() {
        drive.driveFieldRelative(0, 0, 0);

        if (vision != null) {
            vision.close();
        }
    }
}