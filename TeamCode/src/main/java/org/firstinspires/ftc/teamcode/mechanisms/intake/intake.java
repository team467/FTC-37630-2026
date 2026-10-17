package org.firstinspires.ftc.teamcode.mechanisms.intake;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad2;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class intake {
        DcMotor intakeMotor = null;
        Servo leftServo = null;
        Servo rightServo = null;

        public void start(){
            leftServo = hardwareMap.get(Servo.class, "leftServo");
            rightServo = hardwareMap.get(Servo.class, "rightServo" );
            intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");
            intakeMotor.setPower(1.0);
            leftServo.setPosition(1.0);
            rightServo.setPosition(1.0);
        }
        public void stop() {
            leftServo.setPosition(0.0);
            rightServo.setPosition(0.0);
        }





    }












