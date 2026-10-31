package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.CRServo;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Intake extends SubsystemBase {
    private Motor intakeMotor;

    private CRServo leftIntakeServo;
    private ServoEx rightIntakeServo;
    private Telemetry telemetry;

    public Intake(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;

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

    public void updateTelemetry() {
        telemetry.addData("Intake Motor Power", intakeMotor.get());
        telemetry.addData("Intake Motor Raw Power", intakeMotor.getRawPower());
        telemetry.addData("Intake Motor Position", intakeMotor.getCurrentPosition());
        telemetry.addData("Intake Motor Velocity", intakeMotor.getRate());
        telemetry.addData("Left Intake Servo Power", leftIntakeServo.getRawPower());
        telemetry.addData("Right Intake Servo Angle", rightIntakeServo.get());
        telemetry.addData("Right Intake Servo Position", rightIntakeServo.getRawPosition());
    }

    @Override
    public void periodic() {
    }
}
