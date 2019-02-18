package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;


public class Hardware{
    public DcMotor left,right = null;             //left and right drivetrain motors
    public DcMotor slide = null;                  //linear slide motor
    public DcMotor arm = null;                 //motors on intake system to move up and down
    public DcMotor dunk = null;
    //public CRServo extLeft, extRight = null;    //servos to extend intake
    public Servo marker = null;                   //servo to release marker
    public Servo phone = null;                    //servo to rotate phone
    public CRServo collect = null;                //servo to run intake system
    BNO055IMU imu;                                //rev inertial measurement unit for turns
    Orientation angles;
    HardwareMap hwMap = null;                     //hardware map to map devices

    public void init(HardwareMap ahwMap){
        hwMap = ahwMap;
        left = hwMap.get(DcMotor.class, "left");
        right = hwMap.get(DcMotor.class, "right");
        slide = hwMap.get(DcMotor.class,"slide");
        arm = hwMap.get(DcMotor.class, "arm");
        dunk = hwMap.get(DcMotor.class,"dunk");
        /*intakeRight = hwMap.get(DcMotor.class, "intakeRight");
        extLeft = hwMap.get(CRServo.class, "extLeft");
        extRight = hwMap.get(CRServo.class, "extRight");*/
        marker = hwMap.get(Servo.class,"marker");
        phone = hwMap.get(Servo.class,"phone");
        collect = hwMap.get(CRServo.class,"collect");
        imu = hwMap.get(BNO055IMU.class, "imu");

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        imu.initialize(parameters);

        right.setDirection(DcMotorSimple.Direction.REVERSE);
        dunk.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        stopMotors();
    }

    /**
     *
     */
    public void stopMotors(){
        left.setPower(0);
        right.setPower(0);
        slide.setPower(0);
        dunk.setPower(0);
        arm.setPower(0);
        collect.setPower(0);
    }
}
