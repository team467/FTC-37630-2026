package org.firstinspires.ftc.teamcode.mechanisms.intake;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

public class Intake2 {
    private ServoEx leftintakeServo;
    private ServoEx rightintakeServo;
    private DcMotor intakeMotor;

    public Intake2(HardwareMap hwMap) {

        leftintakeServo = (ServoEx) hwMap.get(Servo.class, "leftintakeServo");
        rightintakeServo = (ServoEx) hwMap.get(Servo.class, "rightintakeServo");
        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");
    }

    public void dcControl(double power) {
        intakeMotor.setPower(1.0);
    }

    public void servoControl(double position) {
        leftintakeServo.set(position);
        rightintakeServo.set(-position);
    }
}
