/**
 *
 *
 * @author  William Trang
 * @version 1.1
 * @since   2019-2-24
 * @see     autoMethods
 * @see     blueCrater
 * @see     craterDepot
 */

package org.firstinspires.ftc.teamcode;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "crater slow", group = "pikaReal")
public class CraterSlow extends autoMethods {
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
                encoderDrive(0.6,30,30);
                sleep(500);
                encoderDrive(0.6,-11,-11);

                //turn and drive to depot to release team marker
                turnLeft(83,0.3);
                encoderDrive(0.6,95,95);
                openTail();
                sleep(700);
                closeTail();

                //turn and drive to crater
                turnLeft(129,0.3);
                timeDrive(-1,1500);
                encoderDrive(0.6,-17,-17);
                lowerSlide();
                break;
            }
            case "MIDDLE":{
                //drive to knock off gold mineral
                encoderDrive(0.6,26,26);
                sleep(500);
                encoderDrive(0.6,-10,-10);

                //turn and drive to depot to release team marker
                turnLeft(74,0.3);
                encoderDrive(0.6,50,50);
                turnLeft(95,0.3);
                encoderDrive(0.6,50,50);
                openTail();
                sleep(700);
                closeTail();

                //turn and drive to crater
                turnLeft(135,0.3);
                timeDrive(-1,1250);
                encoderDrive(0.6,-31.5,-31.5);
                lowerSlide();
                break;
            }
            case "RIGHT":{
                //turn and drive to knock off gold mineral
                turnLeft(23,0.3);
                encoderDrive(0.6,32,32);
                sleep(200);
                encoderDrive(0.6,-4,-4);

                //turn and drive to depot to release team marker
                turnLeft(100,0.3);
                encoderDrive(0.6,75,75);
                openTail();
                sleep(700);
                closeTail();

                //turn and drive to crater
                turnLeft(130,0.3);
                timeDrive(-1,1000);
                encoderDrive(0.6,-32.5,-32.5);
                lowerSlide();
                break;
            }
        }

        detector.disable();
    }
}

