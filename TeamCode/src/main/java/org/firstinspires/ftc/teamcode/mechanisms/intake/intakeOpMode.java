package org.firstinspires.ftc.teamcode.mechanisms.intake;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.hardware.motors.CRServo;


@TeleOp(name = "Intake OpMode")
 public class intakeOpMode extends LinearOpMode {
    Intake2 intake = new Intake2();
    public CRServo ServoSpin;

    @Override
    public void runOpMode() {
        intake.init(hardwareMap);
        Intake2 intake = new Intake2();
        intake.init(hardwareMap);
        boolean aButtonReleased = true;

        waitForStart();
        ServoSpin.set(0.0);
        while (opModeIsActive()) {
            if (gamepad1.a) {
                if (ServoSpin != ServoSpin.set()) {
                    ServoSpin.set(0.0);
                } if(ServoSpin = ServoSpin.set()){
                    ServoSpin.set(1.0);                    }
                }
             if (aButtonReleased) {
                    if (ServoSpin.get() == 1.0) {
                        ServoSpin.set(0.0);
                    }
             } else{
                 ServoSpin.set(1.0);
                }}
    }
}
                // variable targetPosition
                // if button pressed
                // is not at targetPosition, set(targetPosition)
                // otherwise,
                // if target position is 1
                // targetPosition = 0
                // else
                // targetPosition = 1

            // if (gamepad1.a) {
                 //intake.servoControl(1.0);
           //  } else{
                // intake.servoControl(0.0);
            // }
            // if (gamepad1.x) {
            //    intake.dcControl(1.0);
            //}
                //intake.servoStop();
            //if (gamepad1.left_bumper) {
                     //intake.servoControl(1.0);
                    //intake.servoControl(300.0);