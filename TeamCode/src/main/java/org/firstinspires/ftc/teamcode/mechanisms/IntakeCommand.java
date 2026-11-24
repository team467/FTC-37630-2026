package org.firstinspires.ftc.teamcode.mechanisms;

import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.command.Subsystem;

import org.firstinspires.ftc.teamcode.mechanisms.drive.Drive;
import java.util.function.DoubleSupplier;

public class DriveCommand extends CommandBase {
    private final Drive drive;
    private final DoubleSupplier forward, strafe, rotate;

    public DriveCommand(Drive drive, DoubleSupplier forward, DoubleSupplier strafe, DoubleSupplier rotate) {
        this.drive = drive;
        this.forward = forward;
        this.strafe = strafe;
        this.rotate = rotate;
        addRequirements((Subsystem) drive);
    }

    @Override
    public void execute() {
        drive.driveFieldRelative(forward.getAsDouble(), strafe.getAsDouble(), rotate.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
        drive.stop();
    }
}