/**
 * This autonomous program was our original autonomous to
 * be used on the crater side. It deploys from the lander,
 * claims the depot, and parks on our alliance's crater.
 * However, we realized that there was no need for a
 * distinction between red and blue crater, as
 * they were the same, and made a new autonomous entirely
 * with sampling, which has made the use of this program
 * lost to the test of time.
 *
 * @author     William Trang
 * @version    2.0
 * @since      2018-11-24
 * @see        autoMethods
 * @see        craterDepot
 * @see        blueMarker
 * @deprecated made a new crater autonomous with sampling
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Disabled
@Autonomous(name = "blue crater", group = "test")
public class blueCrater extends autoMethods {
    @Override
    public void runOpMode(){
        robot.init(hardwareMap);
        setMotorModes();

        waitForStart();

        //lower robot from lander and drive back
        lowerRobot();
        encoderDrive(1,12,12);
        //lowerSlide();
        sleep(500);

        //turn towards wall and drive to depot
        turnLeft(42,0.5);
        encoderDrive(1,36,36);
        turnLeft(80,0.5);
        encoderDrive(1, 75, 75);

        //drop team marker
        robot.marker.setPosition(OPEN);
        sleep(1500);
        robot.marker.setPosition(CLOSED);
        sleep(600);

        //turn and drive to crater
        turnLeft(130,0.5);
        encoderDrive(1,-84,-84);
    }
}
