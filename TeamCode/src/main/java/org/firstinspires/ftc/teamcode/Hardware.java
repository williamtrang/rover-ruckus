package org.firstinspires.ftc.teamcode;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;


public class Hardware{
    public DcMotor left,right,slide=null;
    public Servo marker=null;
    BNO055IMU imu;
    Orientation angles;
    public GoldAlignDetector detector;

    HardwareMap hwMap = null;

    public void init(HardwareMap ahwMap){
        hwMap = ahwMap;
        left = hwMap.get(DcMotor.class, "left");
        right = hwMap.get(DcMotor.class, "right");
        slide = hwMap.get(DcMotor.class,"slide");
        marker = hwMap.get(Servo.class,"marker");
        imu = hwMap.get(BNO055IMU.class, "imu");

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        imu.initialize(parameters);

        right.setDirection(DcMotorSimple.Direction.REVERSE);

        stopMotors();


        // Set up detector
        detector = new GoldAlignDetector(); // Create detector
        detector.init(hwMap.appContext, CameraViewDisplay.getInstance()); // Initialize it with the app context and camera
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
    }

    public void stopMotors(){
        left.setPower(0);
        right.setPower(0);
        slide.setPower(0);
    }
}
