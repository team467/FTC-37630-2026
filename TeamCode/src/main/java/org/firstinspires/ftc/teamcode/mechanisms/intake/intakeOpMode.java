package org.firstinspires.ftc.teamcode.mechanisms.intake;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.linearOpMode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

@TeleOp(name = "Intake OpMode")
 public class intakeOpMode extends LinearOpMode {
    Intake2 intake = new Intake2();

    @Override
    public void runOpMode() {
        intake.init(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.x) {
                intake.dcControl(1.0);
            }
                if (gamepad1.left_bumper) {
                    // intake.servoControl(1.0);
                    intake.servoControl(300.0);
                }
                if (gamepad1.b) {
                    intake.stop();
                }
        }
    }
}

