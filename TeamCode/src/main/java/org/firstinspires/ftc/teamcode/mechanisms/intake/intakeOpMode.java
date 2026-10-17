package org.firstinspires.ftc.teamcode.mechanisms.intake;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.hardware.motors.Motor;

@TeleOp
public class intakeOpMode extends OpMode{
    private CRServo leftServo;
    private CRServo rightServo;
    private DcMotor intakeMotor;
    @Override
    public void init() {
        leftServo = hardwareMap.get(CRServo.class, "left_servo");
        rightServo = hardwareMap.get(CRServo.class, "right_servo");
        intakeMotor = hardwareMap.get(DcMotor.class, "intake_Motor");
    }
    @Override
    public void loop() {
        if (gamepad1.x) {
            leftServo.setPower(1.0);
        } else {
            leftServo.setPower(0.0);
        }
        if (gamepad1.a) {
            rightServo.setPower(1.0);
        } else {
            rightServo.setPower(0.0);
        }
         intakeOpMode loop;{
            if (gamepad1.b) {
                intakeMotor.setPower(1.0);
            } else {
                intakeMotor.setPower(0.0);
            }
        }
        }
    }

