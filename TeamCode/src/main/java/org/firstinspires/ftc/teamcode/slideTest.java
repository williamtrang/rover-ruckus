package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="arm test",group="pikaReal")
public class slideTest extends OpMode{
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
        robot.arm.setPower(gamepad2.left_stick_y*0.5);
        robot.extend.setPower(gamepad2.right_stick_y*0.5);

        //control intake
        if(gamepad2.left_bumper){ //in
            robot.collect.setPower(0.6);
        }
        else if(gamepad2.left_trigger!=0){ //out
            robot.collect.setPower(-0.6);
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
