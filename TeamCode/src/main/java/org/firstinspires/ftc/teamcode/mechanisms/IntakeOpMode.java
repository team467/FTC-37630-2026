package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

@TeleOp(name = "Intake OpMode")
public class IntakeOpMode extends OpMode {

    private Intake intake = new Intake();
    private GamepadEx gamepadEx;

    @Override
    public void init() {
        intake.init(hardwareMap);
        gamepadEx = new GamepadEx(gamepad1);
    }

    @Override
    public void loop() {
        gamepadEx.readButtons();

        if (gamepadEx.getButton(GamepadKeys.Button.A)) {
            intake.dcControl(1);
        }

        if (gamepadEx.getButton(GamepadKeys.Button.B)) {
            intake.servoControl(1);
        }
    }
}


