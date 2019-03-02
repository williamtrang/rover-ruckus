/**
 * This autonomous program is used on the depot side of the
 * lander. This program deploys from the lander, samples,
 * drives back to claim the depot, and parks on the
 * opposing alliance's crater. We park on the opposing
 * alliance's crater to be sure to stay out of the way of
 * our alliance partner. This is our main autonomous program
 * on the depot side because it scores the full 80 points
 * in autonomous, and is our most preferred in general because
 * of its consistency.
 *
 * @author  William Trang
 * @version 3.1
 * @since   2019-1-4
 * @see     autoMethods
 * @see     blueMarker
 * @see     goldFoundTest3
 */

package org.firstinspires.ftc.teamcode;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "depot slow", group = "pikaReal")
public class DepotSlow extends autoMethods {
    private GoldAlignDetector detector = new GoldAlignDetector();

    @Override
    public void runOpMode(){
        String POSITION; //variable to store position of gold mineral
        robot.init(hardwareMap);

        // Set up detector
        detector.init(hardwareMap.appContext, CameraViewDisplay.getInstance()); // Initialize it with the app context and camera
        detector.useDefaults(); // Set detector to use default settings

        // Optional tuning
        detector.alignSize = 100; // How wide (in pixels) is the range in which the gold object will be aligned. (Represented by green bars in the preview)
        detector.alignPosOffset = 0; // How far from center frame to offset this alignment zone.
        detector.downscale = 0.4; // How much to downscale the input frames

        detector.areaScoringMethod = DogeCV.AreaScoringMethod.MAX_AREA; // Can also be PERFECT_AREA
        detector.maxAreaScorer.weight = 0.005; //

        detector.ratioScorer.weight = 5; //
        detector.ratioScorer.perfectRatio = 1.0; // Ratio adjustment

        detector.enable();
        setMotorModes();
        robot.phone.setPosition(0.38);

        waitForStart();

        //if middle if gold, set position to middle
        sleep(500);
        if(detector.isFound()){
            POSITION = "MIDDLE";
        }
        else{
            //if middle isn't gold, rotate left and check
            robot.phone.setPosition(0.6);
            sleep(1000);
            if(detector.isFound()){
                POSITION = "LEFT";
            }
            else{
                POSITION = "RIGHT";
            }
        }

        //lower from lander
        lowerRobot();
        encoderDrive(0.6,4,4);
        sleep(300);

        //drive differently with different position of mineral
        switch(POSITION){
            case "LEFT":{
                //turn and drive to knock off gold mineral
                turnRight(20,0.3);
                encoderDrive(0.6,50,50);

                //turn and drive to depot
                turnLeft(35,0.3);
                encoderDrive(0.6,22,22);

                //drop team marker and turn and drive to crater
                openTail();
                sleep(700);
                closeTail();
                turnRight(60,0.3);
                encoderDrive(0.6,-85,-85);
                break;
            }
            case "MIDDLE":{
                //drive to depot
                encoderDrive(0.6,62,62);
                sleep(1000);

                //drop team marker in depot and turn toward crater
                openTail();
                sleep(1000);
                turnRight(60,0.3);
                closeTail();
                sleep(300);

                //drive towards crater
                encoderDrive(0.6,-84,-84);
                break;
            }
            case "RIGHT":{
                //turn and drive to knock off gold mineral
                turnLeft(23,0.3);
                encoderDrive(0.6,40,40);

                //turn and drive to depot
                turnRight(32,0.3);
                encoderDrive(0.6,25,25);

                //drop team marker and drive to opponent crater
                openTail();
                sleep(700);
                closeTail();
                turnRight(50,0.3);
                encoderDrive(0.6,-75,-75);
                break;
            }
        }

        detector.disable();
    }
}
