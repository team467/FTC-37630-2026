package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.CRServoEx;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

class Intake extends SubsystemBase {

    // one DC motor
    private MotorEx intakeMotor;

    // two continuous rotation servos
    private CRServoEx leftIntakeServo;
    private CRServoEx rightIntakeServo;

    public void init(HardwareMap hardwareMap) {

        // make the DC motor
        intakeMotor = new MotorEx(hardwareMap, "intakeMotor");
        intakeMotor.setRunMode(Motor.RunMode.RawPower);

        // make the two servos
        leftIntakeServo = new CRServoEx(hardwareMap, "leftIntakeServo");
        rightIntakeServo = new CRServoEx(hardwareMap, "rightIntakeServo");

        // flip the left servo so both servos spin the same way
        leftIntakeServo.setInverted(true);
    }
    
    // dcControl -> takes in power
    // servoControl -> takes in position

    public void servoControl(double servoPower) {
        
        leftIntakeServo.set(servoPower);
        rightIntakeServo.set(servoPower);
    }
    
    public void dcControl(double dcPower) {
        
        intakeMotor.set(dcPower);
    }
}
