package org.firstinspires.ftc.teamcode.mechanisms.intake;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Intake OpMode")
public class IntakeOpMode extends OpMode {
    private Intake intake;

    private boolean motorOn = false;
    private boolean servosOn = false;

    private boolean lastA = false;

    @Override
    public void init() {
        intake = new Intake(hardwareMap);
    }

    @Override
    public void loop() {
        boolean a = gamepad1.a;

        if (a && !lastA) {
            motorOn = !motorOn;

            servosOn = !servosOn;
        }

        lastA = a;

        if (motorOn) {
            intake.runMotor();
        } else {
            intake.stopMotor();
        }

        if (servosOn) {
            intake.runServos();
        } else {
            intake.reverseServos();
        }

        telemetry.addData("Motor On", motorOn);
        telemetry.addData("Servos On", servosOn);
        telemetry.update();
    }
}
