package org.firstinspires.ftc.teamcode.mechanisms.drive;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.RunCommand;

@TeleOp(name = "Drive OpMode")
public class MecanumDriveOpMode extends CommandOpMode {
    private Drive drive;

    @Override
    public void initialize() {
        drive = new Drive(hardwareMap, telemetry);

        drive.setDefaultCommand(new RunCommand(
                () -> drive.driveFieldRelative(
                        gamepad1.left_stick_y,
                        gamepad1.left_stick_x,
                        gamepad1.right_stick_x
                ),
                drive
        ));
    }

    @Override
    public void run() {
        super.run();
        telemetry.update();
    }
}
