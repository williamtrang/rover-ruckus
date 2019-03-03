/**
 * This autonomous program is used when starting on the crater
 * side of the lander. The robot lowers, samples, and parks
 * on the crater. This program is used when our alliance
 * partner believes they need the rest of the field
 * during autonomous.
 *
 * @author  William Trang
 * @version 2.1
 * @since   2019-1-5
 * @see     craterDepot
 * @see     autoMethods
 * @see     blueCrater
 */

package org.firstinspires.ftc.teamcode;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldDetector;
import com.disnodeteam.dogecv.detectors.roverrukus.SamplingOrderDetector;
import com.disnodeteam.dogecv.filters.HSVColorFilter;
import com.disnodeteam.dogecv.filters.LeviColorFilter;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp(name="webcam test 2", group="DogeCV")
@Disabled
public class WebcamTest extends OpMode {
    // Detector object
    private SamplingOrderDetector detector;
    Hardware robot = new Hardware();


    @Override
    public void init() {
        robot.init(hardwareMap);

        // Setup detector
        detector = new SamplingOrderDetector(); // Create the detector
        detector.VUFORIA_KEY = "Ae+/B53/////AAABmUQdCdJ9PkHis/D0Ru+/LklFwpxt5Ti/MlN6RaRRhXrXvkoGW2KbGF//RXsHKReTewf/QW2D8oKoOUuv6yBlgbaygAK1OEkeEoj3ruvjTsLaBilBzofOfuPwuMnKWrePqN/fRIhRV2/OgYJ1d11uJedohdv0iEQ4PG+oHX0KpBDnO4nZOueHtRn3AcQuFduvD2fsIu2W5Dj0avmkc0I049VJysTrBMnHUHwrmpNI2p6mhcw0nxLKXZCCylHSZaC6XcNWBafYlCUh5xykNvwo2C3PQPtR5+HMbR/Q59T+7GErNmewRne4ERzzWpnEy0RjrcW7DrCIURS4YskUG2yyEyj/LIQCB4StdQl3qWHALWwC";
        //detector.init(hardwareMap.appContext, CameraViewDisplay.getInstance(), DogeCV.CameraMode.WEBCAM, false, robot.camera);
        detector.useDefaults();

        detector.downscale = 0.4;
        detector.whiteFilter = new LeviColorFilter(LeviColorFilter.ColorPreset.WHITE);
        // Optional tuning
        detector.areaScoringMethod = DogeCV.AreaScoringMethod.MAX_AREA;
        detector.maxAreaScorer.weight = 0.001;

        detector.ratioScorer.weight = 15;
        detector.ratioScorer.perfectRatio = 1.0;

        detector.enable();
    }

    /*
     * Code to run REPEATEDLY when the driver hits INIT
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {

    }


    /*
     * Code to run REPEATEDLY when the driver hits PLAY
     */
    @Override
    public void loop() {
        telemetry.addData("Current Order" , detector.getCurrentOrder().toString()); // The current result for the frame
        telemetry.addData("Last Order" , detector.getLastOrder().toString()); // The last known result
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
        if(detector != null) detector.disable();
    }

}