package org.firstinspires.ftc.teamcode;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "crater depot", group = "pikaReal")
public class craterDepot extends autoMethods {
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

        waitForStart();

        if(detector.isFound()){
            POSITION = "MIDDLE";
        }
        else{
            robot.phone.setPosition(0.25);
            if(detector.isFound()){
                POSITION = "RIGHT";
            }
            else{
                POSITION = "LEFT";
            }
        }

        lowerRobot();
        switch(POSITION){
            case "LEFT":{

                break;
            }
            case "MIDDLE":{

                break;
            }
            case "RIGHT":{

                break;
            }
        }

        detector.disable();
    }
}

