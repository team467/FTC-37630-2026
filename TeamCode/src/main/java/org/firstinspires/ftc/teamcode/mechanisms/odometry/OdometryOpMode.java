
package org.firstinspires.ftc.teamcode.mechanisms.odometry;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Odometry OpMode")
public class OdometryOpMode extends LinearOpMode {

    private Odometry odometry;

    @Override
    public void runOpMode() {

        odometry = new Odometry(hardwareMap);

        telemetry.addLine("Pinpoint initialized");
        telemetry.addData("Status", odometry.getStatus());
        telemetry.update();

        waitForStart();

        // Reset while robot is stationary.
        odometry.reset();

        while (opModeIsActive()) {

            odometry.update();

            telemetry.addData("Status", odometry.getStatus());

            telemetry.addData("X", "%.2f in", odometry.getX());
            telemetry.addData("Y", "%.2f in", odometry.getY());

            telemetry.addData(
                    "Heading",
                    "%.2f degrees",
                    Math.toDegrees(odometry.getHeading())
            );

            telemetry.addData("X Encoder", odometry.getEncoderX());
            telemetry.addData("Y Encoder", odometry.getEncoderY());

            telemetry.addData("Frequency", "%.1f Hz",
                    odometry.getFrequency());

            telemetry.update();
        }
    }
}
