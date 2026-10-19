package org.firstinspires.ftc.teamcode.mechanisms.intake;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

@TeleOp
 public class intakeOpMode extends OpMode {
    Intake2 intake;

    @Override
    public void init() {
        intake = new Intake2(hardwareMap);
    }

    @Override
    public void loop() {
        if (gamepad1.x) {
            intake.dcControl(1.0);
        }
        if (gamepad1.right_bumper){
            intake.servoControl(1.0);
        }
        }


        }