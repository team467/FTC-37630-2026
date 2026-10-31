
package org.firstinspires.ftc.teamcode.mechanisms.drive;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.mechanisms.vision.Vision;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagSingleDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagClusterDetection;

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

        if (target != null)
            drive.setPose(target.robotPose);

        drive.driveFieldRelative(
                gamepad1.left_stick_y,
                gamepad1.left_stick_x,
                gamepad1.right_stick_x
        );

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
