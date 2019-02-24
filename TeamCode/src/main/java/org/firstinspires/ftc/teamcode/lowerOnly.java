/**
 * This program is used to only lower our robot during autonomous.
 * If our alliance partner wants us to completely stay out of
 * their way, we can run this program.
 *
 * @author  William Trang
 * @version 1.0
 * @since   2018-10-28
 * @see     autoMethods
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Autonomous (name = "lower robot only", group = "pikaReal")
@Disabled
public class lowerOnly extends autoMethods{
    @Override
    public void runOpMode(){
        robot.init(hardwareMap);
        setMotorModes();

        waitForStart();

        //lower from lander
        lowerRobot();
        encoderDrive(1,4,4);
        lowerSlide();
    }
}
