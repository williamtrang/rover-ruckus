package org.firstinspires.ftc.teamcode;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;

@Disabled
@Autonomous(name = "gold found test auto", group = "test")
public class goldFoundTest3 extends autoMethods{
    private GoldAlignDetector detector = new GoldAlignDetector();
    @Override
    public void runOpMode(){
        robot.init(hardwareMap);

        // Set up detector
        detector.init(hardwareMap.appContext, CameraViewDisplay.getInstance()); // Initialize it with the app context and camera
        detector.useDefaults(); // Set detector to use default settings

        // Optional tuning
        detector.alignSize = 100; // How wide (in pixels) is the range in which the gold object will be aligned. (Represented by green bars in the preview)
        detector.alignPosOffset = 0; // How far from center frame to offset this alignment zone.
        detector.downscale = 0.4; // How much to downscale the input frames

        detector.areaScoringMethod = DogeCV.AreaScoringMethod.MAX_AREA; // Can also be PERFECT_AREA
        //detector.perfectAreaScorer.perfectArea = 10000; // if using PERFECT_AREA scoring
        detector.maxAreaScorer.weight = 0.005; //

        detector.ratioScorer.weight = 5; //
        detector.ratioScorer.perfectRatio = 1.0; // Ratio adjustment

        detector.enable();
        setMotorModes();

        //position 1 is left, 2 is middle, 3 is right
        int POSITION = 0;

        waitForStart();

        telemetry.addData("POSITION: ",POSITION);
        //gold is right if gold is not seen
        if(!detector.isFound()){
            POSITION = 3;
        }

        lowerRobot();
        encoderDrive(0.6,20,20);
        //read middle particle to see if it is gold or not
        sleep(1000);
        if(detector.isFound() && !(POSITION==3)){
            POSITION = 2;
        }
        else if(!(POSITION==3)){
            POSITION = 1;
        }


        lowerSlide();
        //drive differently based on position of mineral
        switch(POSITION){
            case 1:{ //left
                turnRight(45,0.5);
                encoderDrive(0.6,20,20);
                break;
            }
            case 2:{ //middle
                encoderDrive(0.6,43,43);
                //drop team marker in depot and turn toward crater
                robot.marker.setPosition(OPEN);
                sleep(1000);
                turnLeft(45,0.5);
                sleep(1000);
                robot.marker.setPosition(CLOSED);
                sleep(1000);

                //drive towards crater
                robot.left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                robot.right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                robot.left.setPower(-1);
                robot.right.setPower(-1);
                sleep(4000);
                break;
            }
            case 3:{ //right
                turnLeft(45,0.5);
                encoderDrive(0.6,20,20);
                break;
            }
        }

        detector.disable();

    }
}
