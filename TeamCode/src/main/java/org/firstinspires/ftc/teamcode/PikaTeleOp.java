/**
 * This program is our main teleop. The drivetrain is controlled
 * by the left and right joystick on gamepad 1, as well as the
 * linear slide by the triggers of gamepad 1. Gamepad 2 controls
 * the intake system with the joysticks and triggers as well.
 *
 * @author  William Trang
 * @version 1.1
 * @since   2019-3-2
 * @see     Hardware
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="PikaOp",group="pikaReal")
public class PikaTeleOp extends OpMode{
    Hardware robot = new Hardware();
    public void init() {
        robot.init(hardwareMap);
    }
    @Override
    public void init_loop(){}
    @Override
    public void start(){}
    @Override
    public void loop(){
        final double LEFT_POWER = gamepad1.left_stick_y*.8;
        final double RIGHT_POWER = gamepad1.right_stick_y*.8;
        robot.left.setPower(LEFT_POWER);
        robot.right.setPower(RIGHT_POWER);

        //open
        if(gamepad1.a){
            robot.marker.setPosition(0);
        }
        //closed
        else if(gamepad1.x){
            robot.marker.setPosition(0.99);
        }

        if(gamepad1.right_bumper){ //up
            robot.slide.setPower(0.8);
        }
        else if(gamepad1.right_trigger != 0){ //down
            robot.slide.setPower(-0.8);
        }
        else{
            robot.slide.setPower(0);
        }

        //up and down motor controlled by left joystick
        robot.arm.setPower(-gamepad2.left_stick_y*0.5);
        robot.extend.setPower(gamepad2.right_stick_y*0.8);

        //control intake
        if(gamepad2.left_bumper){ //out
            robot.collect.setPower(1);
        }
        else if(gamepad2.left_trigger!=0){ //in
            robot.collect.setPower(-1);
        }
        else{
            robot.collect.setPower(0);
        }
    }
    @Override
    public void stop() {
        robot.stopMotors();
    }
}
