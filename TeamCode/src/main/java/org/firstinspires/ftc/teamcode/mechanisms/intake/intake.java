package org.firstinspires.ftc.teamcode.mechanisms.intake;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad2;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

public class intake{
    public static void main (String[] args) {
        DcMotor leftMotor;
        DcMotor rightMotor;
        leftMotor = hardwareMap.get(DcMotor.class, "leftMotor");
        rightMotor = hardwareMap.get(DcMotor.class, "rightMotor");
        {
            leftMotor.setPower(1.0);
            rightMotor.setPower(-1.0);
            {
              double driverInput = -gamepad1.left_stick_y;
              leftMotor.setPower(driverInput);
              rightMotor.setPower(driverInput);
                }
                }
                }
            }














