package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
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

    static final double     COUNTS_PER_MOTOR_REV    = 537.6 ; //orbital 20
    static final double     DRIVE_GEAR_REDUCTION    = 1.0 ; //1:1
    static final double     WHEEL_DIAMETER_INCHES   = 3.0 ; //may change
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION)/
            (WHEEL_DIAMETER_INCHES * 3.1415);

    static final double     DRIVE_SPEED = 0.6;
    static final double     TURN_SPEED = 0.5;

    HardwareMap hwMap = null;
    ElapsedTime period  = new ElapsedTime();

    public void init(HardwareMap ahwMap){
        hwMap = ahwMap;
        left = hwMap.get(DcMotor.class, "left");
        right = hwMap.get(DcMotor.class, "right");
        slide = hwMap.get(DcMotor.class,"slide");
        marker = hwMap.get(Servo.class,"marker");
        right.setDirection(DcMotorSimple.Direction.REVERSE);

        stopMotors();
    }

    public void stopMotors(){
        left.setPower(0);
        right.setPower(0);
        slide.setPower(0);
    }
}