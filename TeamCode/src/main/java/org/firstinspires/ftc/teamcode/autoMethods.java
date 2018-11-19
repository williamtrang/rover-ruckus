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
    public void runOpMode(){
    }

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
    //TODO: create turn right method
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

            //move
            encoderDrive(0.2,1.68,1.55);
            robot.stopMotors();

            //slide motor down
            sleep(1000);
            TARGET=robot.slide.getCurrentPosition()-(int)(robot.COUNTS_PER_MOTOR_REV*8); //9 revolutions down
            robot.slide.setTargetPosition(TARGET);
            robot.slide.setPower(-0.5);
            while(opModeIsActive()&&robot.slide.isBusy()){
            }

            robot.stopMotors();
            robot.slide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }

    }

    public void encoderDrive(double speed, double leftInches, double rightInches){
        //TODO: fix this method
        int LEFT_TARGET,RIGHT_TARGET;
        if(opModeIsActive()){
            LEFT_TARGET=robot.left.getCurrentPosition()+(int)(leftInches*robot.COUNTS_PER_INCH);
            RIGHT_TARGET=robot.right.getCurrentPosition()+(int)(rightInches*robot.COUNTS_PER_INCH);

            robot.left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.right.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            robot.left.setTargetPosition(LEFT_TARGET);
            robot.right.setTargetPosition(RIGHT_TARGET);

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

    //TODO: create sampling method

}
