/**
 * This program was one of the original programs we
 * made for the depot side of the lander. It deployed
 * from the lander and dropped off our team marker to
 * claim the depot. We created this program in hopes
 * that we would be able to stay out of the way of
 * our alliance partner. However, this program
 * has no sampling, and we realized we could just
 * park on the opposing alliance's crater, so this
 * program also became lost.
 *
 * @author William Trang
 * @version 1.0
 * @since 2018-11-4
 * @see blueMarker
 * @see autoMethods
 * @see depotOppCrater
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;

@Disabled
@Autonomous(name = "blue marker no crater", group = "test")
public class blueMarkNoCrater extends autoMethods {
    @Override
    public void runOpMode(){
        robot.init(hardwareMap);
        setMotorModes();

        waitForStart();

        //lower from lander
        lowerRobot();
        encoderDrive(1,4,4);
        lowerSlide();

        //drive to corner
        encoderDrive(1,62,62);
        sleep(1000);

        //drop team marker
        robot.marker.setPosition(OPEN);
        sleep(1000);
        robot.marker.setPosition(CLOSED);
        sleep(1000);
    }
}
