package org.firstinspires.ftc.teamcode.pedro_pathing;

import static com.pedropathing.api.Paths.line;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;
import com.seattlesolvers.solverslib.pedroCommand.FollowPathCommand;

import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.indexer.Indexer;
import org.firstinspires.ftc.teamcode.mechanisms.shooter.Shooter;

@Autonomous(name = "Pedro Auto")
public class PedroAuto extends CommandOpMode {
    private Follower follower;
    private Shooter shooter;
    private Intake intake;
    private Indexer indexer;

    private final PoseFactory p = PoseFactory.degrees();
    private final Pose startPose = p.of(9, 24, 0);
    private final Pose scorePose = p.of(36, 36, 45);
    private final Pose pickupStartPose = p.of(26, 48, 180);
    private final Pose pickupEndPose = p.of(11, 48, 180);
    private final Pose parkPose = p.of(15, 108, 90);

    private Path goToFirstScore;
    private Path goToPickup;
    private Path grabBalls;
    private Path goToScore;
    private Path park;

    public void buildPaths() {
        goToFirstScore = line(startPose, scorePose)
                .linear(startPose, scorePose);

        goToPickup = line(scorePose, pickupStartPose)
                .linear(scorePose, pickupStartPose);

        grabBalls = line(pickupStartPose, pickupEndPose)
                .linear(pickupStartPose, pickupEndPose);

        goToScore = line(pickupEndPose, scorePose)
                .linear(pickupEndPose, scorePose);

        park = line(scorePose, parkPose)
                .linear(scorePose, parkPose);
    }

    private SequentialCommandGroup shootBalls() {
        return new SequentialCommandGroup(
                new InstantCommand(shooter::start, shooter),
                new WaitCommand(1500),
                new InstantCommand(indexer::start, indexer),
                new WaitCommand(2000),
                new InstantCommand(indexer::stop, indexer),
                new InstantCommand(shooter::stop, shooter)
        );
    }

    private InstantCommand intakeOn() {
        return new InstantCommand(intake::run, intake);
    }

    private InstantCommand intakeOff() {
        return new InstantCommand(intake::stop, intake);
    }

    @Override
    public void initialize() {
        super.reset();

        HardwareMap harRidwareMap = null;
        shooter = new Shooter(harRidwareMap, telemetry);

        intake = new Intake(hardwareMap, telemetry);

        indexer = new Indexer(hardwareMap, telemetry);

        follower = Pedro_Constants.create(hardwareMap);
        follower.setPose(startPose);
        buildPaths();

        SequentialCommandGroup autonomousSequence = new SequentialCommandGroup(
                new FollowPathCommand(follower, goToFirstScore),
                shootBalls(),

                new FollowPathCommand(follower, goToPickup),
                intakeOn(),
                new FollowPathCommand(follower, grabBalls, 0.4),
                new WaitCommand(500),
                intakeOff(),

                new FollowPathCommand(follower, goToScore),
                shootBalls(),

                new FollowPathCommand(follower, park, false)
        );

        schedule(autonomousSequence);
    }

    @Override
    public void run() {
        super.run();

        follower.update();

        telemetry.addData("X", follower.pose().x());
        telemetry.addData("Y", follower.pose().y());
        telemetry.addData("Heading", follower.pose().heading());
        telemetry.update();
    }
}
