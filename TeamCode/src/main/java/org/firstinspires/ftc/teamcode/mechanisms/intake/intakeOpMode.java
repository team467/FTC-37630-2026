package org.firstinspires.ftc.teamcode.mechanisms.intake;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name = "Intake OpMode")
 public class intakeOpMode extends LinearOpMode {
    Intake2 intake = new Intake2();

    @Override
    public void runOpMode() {
        intake.init(hardwareMap);
        Intake2 intake = new Intake2();
        intake.init(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.x) {
                intake.dcControl(1.0);
            }
            if (gamepad1.left_bumper) {
                intake.leftintakeServo(1.0); // Full speed forward continuously while held
            } else if (gamepad1.a) {
                intake.rightintakeServo(-1.0); // Full speed reverse continuously while held (Optional)
            } else {
                intake.servoStop();
               // if (gamepad1.left_bumper) {
                    // intake.servoControl(1.0);
                    //intake.servoControl(300.0);
                }
                //if (gamepad1.b) {
                    intake.stop();
                }
        }
    }

