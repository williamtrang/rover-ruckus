package org.firstinspires.ftc.teamcode;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "crater no depot", group = "pikaReal")
public class craterNoDepot extends autoMethods {
    private GoldAlignDetector detector = new GoldAlignDetector();
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

        lowerRobot();
        encoderDrive(1,4,4);
        lowerSlide();
        sleep(300);

        switch(POSITION){
            case "LEFT":{
                turnRight(20,0.5);
                encoderDrive(1,50,50);
                turnLeft(35,0.5);
                encoderDrive(0.2,20,20);
                break;
            }
            case "MIDDLE":{
                encoderDrive(0.2,40,40);
                break;
            }
            case "RIGHT":{
                turnLeft(25,0.5);
                encoderDrive(1,50,50);
                turnRight(35,0.5);
                encoderDrive(0.2,25,25);
                break;
            }
        }

        detector.disable();
    }
}
