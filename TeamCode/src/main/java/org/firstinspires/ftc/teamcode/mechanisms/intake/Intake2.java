package org.firstinspires.ftc.teamcode.mechanisms.intake;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.CRServoEx;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
public class Intake2 extends SubsystemBase {
    private CRServo leftintakeServo;
    private CRServo rightintakeServo;
    private Motor intakeMotor;

    public void init(HardwareMap hwMap) {
        leftintakeServo = hwMap.get(CRServo.class, intakeConstants.leftServoName);
        rightintakeServo = hwMap.get(CRServo.class, intakeConstants.rightServoName);
        intakeMotor = new Motor(hwMap, "intakeMotor");
    }

    public void dcControl(double power) {
        intakeMotor.set(1.0);
    }
public void stop(){
        intakeMotor.set(0.0);
}
    public void servoControl(double power) {
        leftintakeServo.setPower(1.0);
        rightintakeServo.setPower(-1.0);
    }
}
