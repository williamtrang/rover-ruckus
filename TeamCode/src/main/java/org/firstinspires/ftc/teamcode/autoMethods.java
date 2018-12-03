package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

@Autonomous(name = "never", group = "test")
@Disabled //don't change ever
public abstract class autoMethods extends LinearOpMode {
    Hardware robot = new Hardware();
    static final double     COUNTS_PER_MOTOR_REV    = 1120 ; //neverest 40
    static final double     DRIVE_GEAR_REDUCTION    = 1.0 ; //1:1
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ; //may change
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION)/
            (WHEEL_DIAMETER_INCHES * Math.PI);
    final double OPEN = 0;
    final double CLOSED = 0.99;

    public void runOpMode(){
    }

    //turns left to desired angle
    public void turnLeft(final float TARGET_ANGLE, double power){
        while(opModeIsActive()){
            float currentAngle = robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
            if(currentAngle<=TARGET_ANGLE){
                robot.left.setPower(power);
                robot.right.setPower(-power);
            }
            else{
                robot.stopMotors();
                break;
            }
        }
    }

    //turns right to desired angle
    public void turnRight(final float TARGET_ANGLE, double power){
        while(opModeIsActive()){
            float currentAngle = robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
            if(currentAngle>=-TARGET_ANGLE){
                robot.left.setPower(-power);
                robot.right.setPower(power);
            }
            else{
                robot.stopMotors();
                break;
            }
        }
    }

    //lowers robot from lander
    public void lowerRobot(){
        int TARGET;
        if(opModeIsActive()){
            //slide motor up
            TARGET=robot.slide.getCurrentPosition()+4838; //9 revolutions to lower
            robot.slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.slide.setTargetPosition(TARGET);
            robot.slide.setPower(0.5);
            while(opModeIsActive()&&robot.slide.isBusy()){
            }

            robot.stopMotors();
            sleep(1000);
            robot.slide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }
    
    //lowers slide
    public void lowerSlide(){
        int TARGET;
        if(opModeIsActive()){
            //slide motor down
            robot.slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            sleep(1000);
            TARGET=robot.slide.getCurrentPosition()-4301; //8 revolutions down
            robot.slide.setTargetPosition(TARGET);
            robot.slide.setPower(-0.5);
            while(opModeIsActive()&&robot.slide.isBusy()){
            }

            robot.stopMotors();
            robot.slide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    //drive using encoders
    public void encoderDrive(double speed, double leftInches, double rightInches){
        int LEFT_TARGET,RIGHT_TARGET;
        if(opModeIsActive()){
            //converts target inches to counts
            LEFT_TARGET=robot.left.getCurrentPosition()+(int)(leftInches*COUNTS_PER_INCH);
            RIGHT_TARGET=robot.right.getCurrentPosition()+(int)(rightInches*COUNTS_PER_INCH);

            //change motor mode to run to position
            robot.left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.right.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            //set motor targets
            robot.left.setTargetPosition(LEFT_TARGET);
            robot.right.setTargetPosition(RIGHT_TARGET);

            //turn on motor while target hasn't been reached
            robot.left.setPower(Math.abs(speed));
            robot.right.setPower(Math.abs(speed));

            while(opModeIsActive() &&
                    (robot.left.isBusy() && robot.right.isBusy())){

            }

            robot.stopMotors();
            robot.left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    public void sample(){
        //TODO: create sampling method with color sensor
    }

    //TODO: test sampling method with cv
}
