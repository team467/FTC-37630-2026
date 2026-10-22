package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Intake OpMode")
public class IntakeOpMode extends OpMode {
    private Intake intake;

    private boolean motorOn = false;
    private boolean servosOn = false;

    private boolean lastA = false;
    private boolean lastLeftTrigger = false;

    @Override
    public void init() {
        intake = new Intake(hardwareMap);
    }

    @Override
    public void loop() {
        boolean a = gamepad1.a;
        boolean leftTrigger = gamepad1.left_trigger > 0.5;

        if (a && !lastA) {
            motorOn = !motorOn;
        }

        if (leftTrigger && !lastLeftTrigger) {
            servosOn = !servosOn;
        }

        lastA = a;
        lastLeftTrigger = leftTrigger;

        if (motorOn) {
            intake.runMotor();
        } else {
            intake.stopMotor();
        }

        if (servosOn) {
            intake.runServos();
        } else {
            intake.stopServos();
        }

        telemetry.addData("Motor On", motorOn);
        telemetry.addData("Servos On", servosOn);
        telemetry.update();
    }
}
