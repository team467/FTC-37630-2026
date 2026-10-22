package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

public class Intake extends SubsystemBase {
    private Motor intakeMotor;

    private ServoEx leftIntakeServo;
    private ServoEx rightIntakeServo;

    public Intake(HardwareMap hardwareMap) {
        intakeMotor = new Motor(hardwareMap, IntakeConstants.motorName);
        intakeMotor.setRunMode(Motor.RunMode.RawPower);

        leftIntakeServo = new ServoEx(hardwareMap, IntakeConstants.leftServoName);
        rightIntakeServo = new ServoEx(hardwareMap, IntakeConstants.rightServoName);

        leftIntakeServo.setInverted(true);
    }

    public void runMotor() {
        intakeMotor.set(IntakeConstants.motorPower);
    }

    public void stopMotor() {
        intakeMotor.stopMotor();
    }

    public void openServos() {
        leftIntakeServo.set(IntakeConstants.servoOpenPosition);
        rightIntakeServo.set(IntakeConstants.servoOpenPosition);
    }

    public void closeServos() {
        leftIntakeServo.set(IntakeConstants.servoClosedPosition);
        rightIntakeServo.set(IntakeConstants.servoClosedPosition);
    }

    @Override
    public void periodic() {
    }
}
