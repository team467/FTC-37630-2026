package org.firstinspires.ftc.teamcode.OpModePedro;
import com.pedropathing.paths.curves.Line;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;


@TeleOp
public class AutonomousOpMode extends OpMode{
    private Follower follower;
    public enum PathState{
        SHOOT_PRELOAD, DRIVE_STARTPOS_SHOOT_POS
    }
    PathState pathState;
    private final Pose startPose = new Pose(5.82,108.82,Math.toRadians(30));
    private final Pose shootPose = new Pose(70.86,54.82,Math.toRadians(230));
private PathChain driveStartPosShootPos;

public void statePathUpdate() {
    switch(pathState){
        case DRIVE_STARTPOS_SHOOT_POS:
        follower.followPath(driveStartPosShootPos,true);
        pathState = PathState.SHOOT_PRELOAD;
        break;
        case SHOOT_PRELOAD:
            //check is follower done its path?
            if(!follower.isBusy()){
                //TODO add logic to flywheel shooter
                telemetry.addLine("Done Path 1");
            }
            break;
        default:
            telemetry.addLine("No State Commanded");
            break;

}
}
@Override
    public void init(){

    }
    @Override
    public void loop(){}
}







