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
import com.disnodeteam.dogecv.detectors.roverrukus.GoldDetector;
import com.disnodeteam.dogecv.detectors.roverrukus.SamplingOrderDetector;
import com.disnodeteam.dogecv.filters.LeviColorFilter;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Autonomous(name = "depot webcame test", group = "pikaReal")
@Disabled
public class DepotWebcamTest extends autoMethods {
    private SamplingOrderDetector detector;

    @Override
    public void runOpMode(){
        String POSITION; //variable to store position of gold mineral
        robot.init(hardwareMap);

        detector = new SamplingOrderDetector();

        //Sets the Vuforia license key. ALWAYS SET BEFORE INIT!
        detector.VUFORIA_KEY = "Ae+/B53/////AAABmUQdCdJ9PkHis/D0Ru+/LklFwpxt5Ti/MlN6RaRRhXrXvkoGW2KbGF//RXsHKReTewf/QW2D8oKoOUuv6yBlgbaygAK1OEkeEoj3ruvjTsLaBilBzofOfuPwuMnKWrePqN/fRIhRV2/OgYJ1d11uJedohdv0iEQ4PG+oHX0KpBDnO4nZOueHtRn3AcQuFduvD2fsIu2W5Dj0avmkc0I049VJysTrBMnHUHwrmpNI2p6mhcw0nxLKXZCCylHSZaC6XcNWBafYlCUh5xykNvwo2C3PQPtR5+HMbR/Q59T+7GErNmewRne4ERzzWpnEy0RjrcW7DrCIURS4YskUG2yyEyj/LIQCB4StdQl3qWHALWwC";

        //Inits the detector. Choose which camera to use, and whether to detect VuMarks here
        //detector.init(hardwareMap.appContext, CameraViewDisplay.getInstance(), DogeCV.CameraMode.WEBCAM, false, robot.camera);

        //Sets basic detector settings
        detector.whiteFilter = new LeviColorFilter(LeviColorFilter.ColorPreset.WHITE);
        detector.useDefaults(); // Use default settings
        detector.areaScoringMethod = DogeCV.AreaScoringMethod.MAX_AREA;
        detector.maxAreaScorer.weight = 0.001;

        detector.ratioScorer.weight = 15;
        detector.ratioScorer.perfectRatio = 1.0;

        detector.enable();
        setMotorModes();
        robot.phone.setPosition(0.38);

        waitForStart();

        sleep(1000);
        POSITION = detector.getCurrentOrder().toString().toLowerCase();

        //lower from lander
        lowerRobot();
        encoderDrive(1,4,4);
        sleep(300);

        //drive differently with different position of mineral
        switch(POSITION){
            case "left":{
                //turn and drive to knock off gold mineral
                turnRight(20,0.6);
                encoderDrive(1,50,50);

                //turn and drive to depot
                turnLeft(35,0.6);
                encoderDrive(1,22,22);

                //drop team marker and turn and drive to crater
                openTail();
                sleep(700);
                closeTail();
                turnRight(60,0.7);
                encoderDrive(1,-85,-85);
                break;
            }
            case "center":{
                //drive to depot
                encoderDrive(1,62,62);
                sleep(1000);

                //drop team marker in depot and turn toward crater
                openTail();
                sleep(1000);
                turnRight(60,0.5);
                closeTail();
                sleep(300);

                //drive towards crater
                encoderDrive(1,-84,-84);
                break;
            }
            case "right":{
                //turn and drive to knock off gold mineral
                turnLeft(24,0.5);
                encoderDrive(1,40,40);

                //turn and drive to depot
                turnRight(32,0.5);
                encoderDrive(1,25,25);

                //drop team marker and drive to opponent crater
                openTail();
                sleep(700);
                closeTail();
                turnRight(45,0.6);
                encoderDrive(1,-75,-75);
                break;
            }
            default:{
                //turn and drive to knock off gold mineral
                turnLeft(24,0.5);
                encoderDrive(1,40,40);

                //turn and drive to depot
                turnRight(32,0.5);
                encoderDrive(1,25,25);

                //drop team marker and drive to opponent crater
                openTail();
                sleep(700);
                closeTail();
                turnRight(45,0.6);
                encoderDrive(1,-75,-75);
                break;
            }
        }

        detector.disable();
    }
}
