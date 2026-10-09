package org.firstinspires.ftc.teamcode.mechanisms.drive;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class MecanumDriveOpMode extends OpMode {
    private final MecanumDrive drive = new MecanumDrive();

    @Override
    public void init() {
        drive.init(hardwareMap);
    }

    @Override
    public void loop() {
        drive.driveFieldRelative(
                gamepad1.left_stick_y,
                gamepad1.left_stick_x,
                gamepad1.right_stick_x
        );
    }
}
