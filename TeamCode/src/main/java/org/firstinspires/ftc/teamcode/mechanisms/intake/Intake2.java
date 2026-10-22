package org.firstinspires.ftc.teamcode.mechanisms.intake;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;
public class Intake2 extends SubsystemBase {
    private CRServo leftServo;
    private CRServo rightServo;
    private Motor intakeMotor;

    public void init(HardwareMap hwMap) {

       leftServo = hardwareMap.get(CRServo.class, "leftintakeServo");
        rightServo = hardwareMap.get(CRServo.class, "rightintakeServo");
        intakeMotor = new Motor(hwMap, "intakeMotor");
    }

    public void dcControl(double power) {
        intakeMotor.set(1.0);
    }
public void stop(){
        intakeMotor.set(0.0);
}
    public void servoControl(double position) {
        leftServo.setPower(0.0);
        rightServo.setPower(300.0);
    }
}
