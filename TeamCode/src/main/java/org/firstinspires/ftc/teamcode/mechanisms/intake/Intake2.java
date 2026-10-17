package org.firstinspires.ftc.teamcode.mechanisms.intake;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Intake2 {
    private CRServo leftServo;
    private CRServo rightServo;
    private DcMotor intakeMotor;
    public void init(HardwareMap hwMap){
        CRServo leftServo = (CRServo) hwMap.get(Servo.class, "leftServo");
        CRServo rightServo = (CRServo) hwMap.get(Servo.class, "rightServo");
        DcMotor intakeMotor = hardwareMap.get(DcMotor.class, "intake_Motor");
    }
    public void serLeftServo(double speed){
        leftServo.setPower(1.0);
        rightServo.setPower(-1.0);
        intakeMotor.setPower(1.0);
    }

}
