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

@TeleOp(name = "intake OpMode")
public class intakeOpMode extends LinearOpMode {
    @Override
    public void runOpMode() {
                double driverInput = -gamepad1.left_stick_x;
        LEFT_SERVO_PORT. = (driverInput);
        RIGHT_SERVO_PORT. = (driverInput);
        }
    }


