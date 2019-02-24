/**
 * This program is the original autonomous program we used on
 * the depot side of the lander. It deploys from the lander,
 * claims the depot, and parks on our alliance's crater.
 * Similar to the blueCrater autonomous, we decided to make
 * a completely new program with the implementation of sampling
 * to clean up everything, and thus this program became lost
 * to the test of time.
 *
 * @author William Trang
 * @version 2.0
 * @since 2018-11-4
 * @see depotOppCrater
 * @see autoMethods
 * @see blueCrater
 * @deprecated made a new depot autonomous with sampling
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Disabled
@Autonomous(name = "blue marker test", group = "test")
public class blueMarker extends autoMethods {
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

        //drop team marker in depot and turn toward crater
        openTail();
        sleep(1000);
        turnLeft(45,0.5);
        sleep(1000);
        closeTail();
        sleep(1000);

        //drive towards crater
        timeDrive(-1,4000);
    }
}
