package org.firstinspires.ftc.teamcode.mechanisms.intake;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.opencv.core.Core.min;

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
    ServoEx speedServo = new ServoEx(hardwareMap, "speed_servo", 0.0, 300.0);
    public void servoControl(double position) {
        ServoEx leftServo = new ServoEx(hardwareMap,"leftintakeServo", 0.0 ,300.0);
                ServoEx rightServo = new ServoEx(hardwareMap, "rightintakeServo", 0.0,300.0);
        leftServo.set(0.0);
        rightServo.set(300.0);
    }
}
