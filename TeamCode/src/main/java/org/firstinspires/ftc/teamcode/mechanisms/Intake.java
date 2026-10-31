package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.CRServo;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

public class Intake extends SubsystemBase {
    private Motor intakeMotor;

    private CRServo leftIntakeServo;
    private ServoEx rightIntakeServo;

    public Intake(HardwareMap hardwareMap) {
        intakeMotor = new Motor(hardwareMap, IntakeConstants.motorName);
        intakeMotor.setRunMode(Motor.RunMode.RawPower);

        leftIntakeServo = new CRServo(hardwareMap, IntakeConstants.leftServoName);
        rightIntakeServo = new ServoEx(hardwareMap, IntakeConstants.rightServoName, IntakeConstants.rightServoRange);
    }

    public void runMotor() {
        intakeMotor.set(IntakeConstants.motorPower);
    }

    public void stopMotor() {
        intakeMotor.stopMotor();
    }

    public void runServos() {
        leftIntakeServo.set(IntakeConstants.servoPower);
        rightIntakeServo.set(IntakeConstants.rightServoOpenAngle);
    }

    public void reverseServos() {
        leftIntakeServo.set(IntakeConstants.servoReversePower);
        rightIntakeServo.set(IntakeConstants.rightServoClosedAngle);
    }

    @Override
    public void periodic() {
    }
}
