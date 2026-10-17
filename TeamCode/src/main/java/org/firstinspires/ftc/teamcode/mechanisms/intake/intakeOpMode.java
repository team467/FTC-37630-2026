package org.firstinspires.ftc.teamcode.mechanisms.intake;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.teamcode.mechanisms.intake.intakeConstants.LEFT_SERVO_PORT;
import static org.firstinspires.ftc.teamcode.mechanisms.intake.intakeConstants.RIGHT_SERVO_PORT;

import androidx.appcompat.graphics.drawable.AnimatedStateListDrawableCompat;
import androidx.collection.CircularArray;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.Arrays;

@TeleOp(name = "intake OpMode")
public class intakeOpMode extends LinearOpMode {
    @Override
    public void runOpMode() {
        Servo leftServo = hardwareMap.get(Servo.class, "left_servo");
        Servo rightServo = hardwareMap.get(Servo.class, "right_servo");
        while (opModeIsActive()) {
            double driverInput;
            driverInput = -gamepad1.left_stick_x;
            for (Servo servo : Arrays.asList(leftServo, rightServo)) {
                servo.setPosition(driverInput);
            }
        }
        }


        }

