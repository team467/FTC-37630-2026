package org.firstinspires.ftc.teamcode.mechanisms.intake;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.SimpleServo;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

public class Intake2 extends SubsystemBase {
    private ServoEx leftServo;
    private ServoEx rightServo;
    private Motor intakeMotor;

    public void init(HardwareMap hwMap) {

        leftServo = new ServoEx(hwMap, "leftintakeServo");
        rightServo = new ServoEx(hwMap, "rightintakeServo");
        intakeMotor = new Motor(hwMap, "intakeMotor");
    }

    public void dcControl(double power) {
        intakeMotor.set(1.0);
    }
public void stop(){
        intakeMotor.set(0.0);
}
    public void servoControl(double position) {
        leftServo.set(position);
        rightServo.set(-position);
    }
}
