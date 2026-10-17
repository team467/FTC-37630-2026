package org.firstinspires.ftc.teamcode.mechanisms.intake;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import androidx.collection.CircularArray;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "intake OpMode")
public class intakeOpMode extends LinearOpMode {
    public DcMotor leftMotor = null;
    public DcMotor rightMotor = null;

    @Override
    public void runOpMode() {
        leftMotor = hardwareMap.get(DcMotor.class, "leftMotor");
        rightMotor = hardwareMap.get(DcMotor.class, "rightMotor");
        waitForStart();
        while (opModeIsActive()) {
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

