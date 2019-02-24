/**
 * This Hardware class is exclusively used as an object. It
 * contains all of our motors, servos, and sensors on our
 * robot, which makes it so our additions to the robot
 * are simple in the code.
 *
 * @author  William Trang
 * @version 3.3
 * @since   2018-9-29
 * @see     autoMethods
 * @see     slideTest
 */

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
    public DcMotor arm = null;                    //motor to control side intake
    public DcMotor extend = null;                 //motor to extend scoring mechanism
    public Servo marker = null;                   //servo to release marker
    public Servo phone = null;                    //servo to rotate phone
    public CRServo collect = null;                //motor to run intake system
    BNO055IMU imu;                                //rev inertial measurement unit for turns
    Orientation angles;
    HardwareMap hwMap = null;                     //hardware map to map devices

    public void init(HardwareMap ahwMap){
        hwMap = ahwMap;
        left = hwMap.get(DcMotor.class, "left");
        right = hwMap.get(DcMotor.class, "right");
        slide = hwMap.get(DcMotor.class,"slide");
        arm = hwMap.get(DcMotor.class, "arm");
        extend = hwMap.get(DcMotor.class,"dunk");
        marker = hwMap.get(Servo.class,"marker");
        phone = hwMap.get(Servo.class,"phone");
        collect = hwMap.get(CRServo.class,"collect");
        imu = hwMap.get(BNO055IMU.class, "imu");

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        imu.initialize(parameters);

        right.setDirection(DcMotorSimple.Direction.REVERSE);
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        extend.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        stopMotors();
    }

    /**
     * Stops all movement of motors and continuous rotation servos.
     */
    public void stopMotors(){
        left.setPower(0);
        right.setPower(0);
        slide.setPower(0);
        extend.setPower(0);
        arm.setPower(0);
        collect.setPower(0);
    }
}
