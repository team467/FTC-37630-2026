package org.firstinspires.ftc.teamcode.mechanisms.drive;

import com.seattlesolvers.solverslib.command.CommandBase;

import java.util.function.DoubleSupplier;

public class DriveCommand extends CommandBase {

    private final Drive drive;
    private final DoubleSupplier forward;
    private final DoubleSupplier strafe;
    private final DoubleSupplier rotate;

    public DriveCommand(
            Drive drive,
            DoubleSupplier forward,
            DoubleSupplier strafe,
            DoubleSupplier rotate
    ) {
        this.drive = drive;
        this.forward = forward;
        this.strafe = strafe;
        this.rotate = rotate;

        addRequirements(drive);
    }

    @Override
    public void execute() {
        drive.driveFieldRelative(
                forward.getAsDouble(),
                strafe.getAsDouble(),
                rotate.getAsDouble()
        );
    }

    @Override
    public void end(boolean interrupted) {
        drive.stop();
    }
}
