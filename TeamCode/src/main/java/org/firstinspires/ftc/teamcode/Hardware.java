package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;


public class Hardware{
    public DcMotor left,right,slide=null;
    public Servo marker,phone=null;
    BNO055IMU imu;
    Orientation angles;
    HardwareMap hwMap = null;

    public void init(HardwareMap ahwMap){
        hwMap = ahwMap;
        left = hwMap.get(DcMotor.class, "left");
        right = hwMap.get(DcMotor.class, "right");
        slide = hwMap.get(DcMotor.class,"slide");
        marker = hwMap.get(Servo.class,"marker");
        phone = hwMap.get(Servo.class,"phone");
        imu = hwMap.get(BNO055IMU.class, "imu");

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        imu.initialize(parameters);
        right.setDirection(DcMotorSimple.Direction.REVERSE);
        stopMotors();
    }

    public void stopMotors(){
        left.setPower(0);
        right.setPower(0);
        slide.setPower(0);
    }
}
