package org.firstinspires.ftc.teamcode.mechanisms.drive;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Drive OpMode")
public class MecanumDriveOpMode extends OpMode {
    private final Drive drive = new Drive();

    @Override
    public void init() {
        drive.init(hardwareMap, telemetry);
    }

    @Override
    public void loop() {
        drive.driveFieldRelative(
                gamepad1.left_stick_y,
                gamepad1.left_stick_x,
                gamepad1.right_stick_x
        );

        drive.updateTelemetry();
        telemetry.update();
    }
}
