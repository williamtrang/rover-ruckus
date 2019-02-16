package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="slideTest",group="test2")
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

        /*robot.intakeLeft.setPower(gamepad2.left_stick_y*0.4);
        robot.intakeRight.setPower(gamepad2.left_stick_y*0.4);
        if(gamepad2.a){
            robot.extLeft.setPosition(robot.extLeft.getPosition()-0.1);
            robot.extRight.setPosition(robot.extRight.getPosition()+0.1);
        }
        else if(gamepad2.b){
            robot.extLeft.setPosition(robot.extLeft.getPosition()+0.1);
            robot.extRight.setPosition(robot.extRight.getPosition()-0.1);
        }
        else{
            robot.extLeft.setPosition(0.5);
            robot.extRight.setPosition(0.5);
        }

        /*if(gamepad1.x){
            collect.setPosition(collect.getPosition()+0.1);
        }
        else if(gamepad1.y){
            collect.setPosition(collect.getPosition()-0.1);
        }
        else{
            collect.setPosition(0.5);
        }(*/
    }
    @Override
    public void stop() {
        robot.stopMotors();
    }
}
