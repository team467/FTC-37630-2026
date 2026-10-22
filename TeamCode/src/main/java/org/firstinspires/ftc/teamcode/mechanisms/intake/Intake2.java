package org.firstinspires.ftc.teamcode.mechanisms.intake;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;
public class Intake2 extends SubsystemBase {
    private ServoEx leftServo;
    private ServoEx rightServo;
    private Motor intakeMotor;

    public void init(HardwareMap hwMap) {

        leftServo = new ServoEx(hwMap, "leftintakeServo",0.0, 300.0);
        rightServo = new ServoEx(hwMap, "rightintakeServo",0.0, 300.0);
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
        leftServo.set(0.0);
        rightServo.set(300.0);
    }
}
