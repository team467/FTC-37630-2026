package org.firstinspires.ftc.teamcode.mechanisms.shooter;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Shooter OpMode")
public class ShooterOpMode extends LinearOpMode {

    @Override
    public void runOpMode() {

        Shooter shooter = new Shooter();
        shooter.init(hardwareMap, telemetry);

        telemetry.addLine("Shooter initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            if (gamepad1.right_bumper) {
                shooter.start();
            } else {
                shooter.stop();
            }

            telemetry.addData("Shooter On", gamepad1.right_bumper);
            shooter.updateTelemetry();
            telemetry.update();
        }
    }
}