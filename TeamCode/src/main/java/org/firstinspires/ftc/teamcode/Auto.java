package org.firstinspires.ftc.teamcode;

import com.pedropathing.api.Paths;
import com.pedropathing.follower.Follower;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.CommandScheduler;
import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;
import com.seattlesolvers.solverslib.pedroCommand.FollowPathCommand;

import org.firstinspires.ftc.teamcode.mechanisms.drive.Drive;
import org.firstinspires.ftc.teamcode.mechanisms.intake.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.shooter.Shooter;

import java.util.Base64;


@Autonomous(name = "AutoMode", group = "AutoOp")
public class AutoMode extends OpMode {
    private Drive driveSubsystem;
    private Follower follower;
    private Intake intakeSubsystem;
    private Shooter shooterSubsystem;

    // Your original Red Alliance Poses
    private final Pose startRed = new Pose(10.0, 43.3, Math.toRadians(-90));
    private final Pose endRed = new Pose(10.21, 7.26, Math.toRadians(-90));
    private final Pose endPosRed = new Pose(10.23, 21.13, Math.toRadians(-90));
    private final Pose endTurnRed = new Pose(57.58, 27.17, Math.toRadians(-90));
    private final Pose endShootRed = new Pose(57.47, 37.09, Math.toRadians(-90));
    private static final double SHOOTER_SPINUP_SECONDS = 2;

    @Override
    public void init() {
        CommandScheduler.getInstance().reset();
        follower = new Follower(HardwareMap hardwareMap);
        follower.setPose(startRed);
        driveSubsystem = new Drive();
        intakeSubsystem = new Intake(hardwareMap);
        shooterSubsystem = new Shooter();
        shooterSubsystem.init(hardwareMap);
        Path path0 = Paths.line(startRed, endRed).tangent();
        Path path1 = Paths.line(endRed, endPosRed).tangent();
        Path path2 = Paths.line(endPosRed, endTurnRed).tangent();
        Path path3 = Paths.line(endTurnRed, endShootRed).tangent();
        SequentialCommandGroup autoGroup = new SequentialCommandGroup(
                new FollowPathCommand(follower, path0),
                new FollowPathCommand(follower, path1),
                new FollowPathCommand(follower, path2),
                new FollowPathCommand(follower, path3),
                new WaitCommand(SHOOTER_SPINUP_SECONDS),
                new InstantCommand(() -> shooterSubsystem.setVelocityRPM(3000)));

        CommandScheduler.getInstance().schedule(autoGroup);
    }
    @Override
        public void loop() {

            CommandScheduler.getInstance().run();

            follower.update();


        }
    }
