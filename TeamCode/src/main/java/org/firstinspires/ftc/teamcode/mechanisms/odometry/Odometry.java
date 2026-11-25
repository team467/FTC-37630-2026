
package org.firstinspires.ftc.teamcode.mechanisms.odometry;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.HardwareMap;

import com.seattlesolvers.solverslib.geometry.Pose2d;
import com.seattlesolvers.solverslib.geometry.Rotation2d;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.robotcore.external.navigation.UnnormalizedAngleUnit;

public class Odometry {

    private final GoBildaPinpointDriver pinpoint;

    // Pod configuration
    private static final double X_OFFSET_MM = 0.0;
    private static final double Y_OFFSET_MM = 0.0;

    // Change this if using swingarm pods.
    private static final GoBildaPinpointDriver.GoBildaOdometryPods POD_TYPE =
            GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD;

    public Odometry(HardwareMap hardwareMap) {

        pinpoint = hardwareMap.get(
                GoBildaPinpointDriver.class,
                "pinpoint"
        );

        configure();
    }

    private void configure() {

        // Configure pod mounting positions
        pinpoint.setOffsets(
                X_OFFSET_MM,
                Y_OFFSET_MM,
                DistanceUnit.MM
        );

        // Configure pod encoder resolution
        pinpoint.setEncoderResolution(POD_TYPE);

        // Configure encoder directions
        pinpoint.setEncoderDirections(
                GoBildaPinpointDriver.EncoderDirection.FORWARD,
                GoBildaPinpointDriver.EncoderDirection.FORWARD
        );
    }

    public void update() {
        pinpoint.update();
    }

    // Reset position and recalibrate internal IMU.
    // Robot must remain stationary.
    public void reset() {
        pinpoint.resetPosAndIMU();
    }

    // Set an absolute position in inches and radians.
    public void setPose(double x, double y, double heading) {

        pinpoint.setPosition(
                new Pose2D(
                        DistanceUnit.INCH,
                        x,
                        y,
                        AngleUnit.RADIANS,
                        heading
                )
        );
    }

    public double getX() {
        return pinpoint.getPosition().getX(DistanceUnit.INCH);
    }

    public double getY() {
        return pinpoint.getPosition().getY(DistanceUnit.INCH);
    }

    public double getHeading() {
        return pinpoint.getPosition().getHeading(AngleUnit.RADIANS);
    }

    public Pose2d getPose() {

        return new Pose2d(
                getX(),
                getY(),
                new Rotation2d(getHeading())
        );
    }

    // Velocity in inches per second
    public double getVelocityX() {
        return pinpoint.getVelX(DistanceUnit.INCH);
    }

    public double getVelocityY() {
        return pinpoint.getVelY(DistanceUnit.INCH);
    }

    // Angular velocity in radians per second
    public double getHeadingVelocity() {
        return pinpoint.getHeadingVelocity(
                UnnormalizedAngleUnit.RADIANS
        );
    }

    public int getEncoderX() {
        return pinpoint.getEncoderX();
    }

    public int getEncoderY() {
        return pinpoint.getEncoderY();
    }

    public String getStatus() {
        return pinpoint.getDeviceStatus().toString();
    }

    public double getFrequency() {
        return pinpoint.getFrequency();
    }
}
