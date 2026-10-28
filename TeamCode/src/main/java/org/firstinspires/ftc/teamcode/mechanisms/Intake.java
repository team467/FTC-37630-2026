package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.CRServo;
import com.seattlesolvers.solverslib.hardware.motors.Motor;

public class    Intake extends SubsystemBase {
    private Motor intakeMotor;

    private CRServo leftIntakeServo;
    private CRServo rightIntakeServo;

    public Intake(HardwareMap hardwareMap) {
        intakeMotor = new Motor(hardwareMap, IntakeConstants.motorName);
        intakeMotor.setRunMode(Motor.RunMode.RawPower);

        leftIntakeServo = new CRServo(hardwareMap, IntakeConstants.leftServoName);
        rightIntakeServo = new CRServo(hardwareMap, IntakeConstants.rightServoName);
    }

    public void runMotor() {
        intakeMotor.set(IntakeConstants.motorPower);
    }

    public void stopMotor() {
        intakeMotor.stopMotor();
    }

    public void runServos() {
        leftIntakeServo.set(IntakeConstants.servoPower);
        rightIntakeServo.set(-IntakeConstants.servoPower);
    }

    public void stopServos() {
        leftIntakeServo.set(0);
        rightIntakeServo.set(0);
    }

    @Override
    public void periodic() {
    }
}
