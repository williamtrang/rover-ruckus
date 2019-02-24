/**
 * The autoMethods abstract class exists to be extended by our autonomous
 * programs. It contains all of the methods we use during the autonomous
 * period, which helps clean up our code and facilitates the process
 * of creating our autonomous programs.
 *
 * @author  William Trang
 * @version 3.1
 * @since   2018-9-29
 * @see     Hardware
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

@Autonomous(name = "never", group = "test")
@Disabled
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
     * Resets encoders on motors and sets runmode to run using encoders.
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
     * Turns robot left until a desired angle is reached. The angle
     * parameter specifies the angle to turn to. This is done using
     * the heading of the REV's integrated inertial measurement unit,
     * and turning until the right heading is reached.
     *
     * @param TARGET_ANGLE the angle to turn left to
     * @param power        the power(speed) of the turn
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
     * Turns robot left until a desired angle is reached. The angle
     * parameter specifies the angle to turn to. This is done using
     * the heading of the REV's integrated inertial measurement unit,
     * and turning until the right heading is reached.
     *
     * @param TARGET_ANGLE the angle to turn right to
     * @param power        the power(speed) of the turn
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
     * Lowers robot from the lander. This uses encocders on the linear slide
     * motors to move an exact distance from the lander to the ground.
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
     * Lowers the robot's linear slide. This uses encocders on the linear slide
     * motors to move an exact distance from the lander to the ground.
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
     * Drives using encoders on the drivetrain motors. These encoders are used
     * to move precise distances during the autonomous period. The inputted
     * parameters, leftInches and rightInches, are converted to counts by
     * multiplying them by the counts per inch. These counts are set as the
     * target position of the motors, then the motors will run until the
     * target is reached.
     *
     * @param speed       speed to run motors until taret position is reached
     * @param leftInches  inches to drive left motor
     * @param rightInches inches to drive right motor
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
     * Drive using time instead of encoders. This exists because encoders have
     * a capped speed at around 65% of the motor's power. This method is used
     * in autonomous programs where running out of time is a possible factor.
     *
     * @param POWER power to drive motors at until time is reached
     * @param TIME  time to run motors until they turn off
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
     * Opens back tail to release team marker.
     */
    public void openTail(){
        robot.marker.setPosition(OPEN);
    }

    /**
     * Closes back tail to return tail to original position.
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
