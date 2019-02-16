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
    static final double     COUNTS_PER_MOTOR_REV    = 1120 ; //Neverest 40
    static final double     DRIVE_GEAR_REDUCTION    = 1.0 ;  //1:1
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION)/
            (WHEEL_DIAMETER_INCHES * Math.PI);
    final double OPEN = 0;      //open position for marker servo
    final double CLOSED = 0.99; //closed position for marker servo

    public void runOpMode(){
    }

    /**
     *
     */
    public void setMotorModes(){
        robot.left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.slide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    /**
     *
     * @param TARGET_ANGLE
     * @param power
     */
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

    /**
     *
     * @param TARGET_ANGLE
     * @param power
     */
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

    /**
     *
     */
    public void lowerRobot(){
        int TARGET;
        if(opModeIsActive()){
            //slide motor up
            TARGET=robot.slide.getCurrentPosition()+14514;
            robot.slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.slide.setTargetPosition(TARGET);
            robot.slide.setPower(0.8);
            while(opModeIsActive()&&robot.slide.isBusy()){
            }

            robot.stopMotors();
            sleep(1000);
            robot.slide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    /**
     *
     */
    public void lowerSlide(){
        int TARGET;
        if(opModeIsActive()){
            robot.slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            sleep(1000);
            TARGET=robot.slide.getCurrentPosition()-12903;
            robot.slide.setTargetPosition(TARGET);
            robot.slide.setPower(-0.8);
            while(opModeIsActive()&&robot.slide.isBusy()){
            }

            robot.stopMotors();
            robot.slide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    /**
     *
     * @param speed
     * @param leftInches
     * @param rightInches
     */
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

    /**
     *
     * @param POWER
     * @param TIME
     */
    public void timeDrive(final double POWER, final long TIME){
        robot.left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.left.setPower(POWER);
        robot.right.setPower(POWER);
        sleep(TIME);

        robot.left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    /**
     *
     */
    public void openTail(){
        robot.marker.setPosition(OPEN);
    }

    /**
     *
     */
    public void closeTail(){
        robot.marker.setPosition(CLOSED);
    }

    class LowerSlide extends Thread{
        public void run(){
            lowerSlide();
        }
    }
}
