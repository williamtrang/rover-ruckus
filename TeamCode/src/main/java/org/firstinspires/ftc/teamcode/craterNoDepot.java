package org.firstinspires.ftc.teamcode;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "crater no depot", group = "pikaReal")
public class craterNoDepot extends autoMethods {
    private GoldAlignDetector detector = new GoldAlignDetector();
    private lowerSlide lower = new lowerSlide();

    @Override
    public void runOpMode(){
        String POSITION;
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

        //lower from lander and lower linear slide
        lowerRobot();
        encoderDrive(1,4,4);
        lowerSlide();

        //drive differently with different position of mineral
        switch(POSITION){
            case "LEFT":{
                //turn and drive to knock off gold mineral
                turnRight(20,0.5);
                encoderDrive(1,50,50);

                //turn and drive to park on crater
                turnLeft(30,0.5);
                encoderDrive(0.2,8,8);
                break;
            }
            case "MIDDLE":{
                //drive to knock off gold mineral and park on crater
                encoderDrive(0.2,65,65);
                break;
            }
            case "RIGHT":{
                //turn and drive to knock off gold mineral
                turnLeft(24,0.5);
                encoderDrive(1,50,50);

                //turn and drive to park on crater
                turnRight(30,0.5);
                encoderDrive(0.2,10,10);
                break;
            }
        }

        detector.disable();

    }

}
