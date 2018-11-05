package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="slideTest",group="test2")
public class slideTest extends OpMode{
    Hardware robot = new Hardware();
    int count=0;
    public void init() {
        robot.init(hardwareMap);
    }
    @Override
    public void init_loop(){}
    @Override
    public void start(){}
    @Override
    public void loop(){
        final double LEFT_POWER = gamepad1.left_stick_y/2.5;
        final double RIGHT_POWER = gamepad1.right_stick_y/2.5;
        robot.left.setPower(LEFT_POWER);
        robot.right.setPower(RIGHT_POWER);

        if(gamepad1.a&&count==0){
            robot.marker.setPosition(0);
        }
        else if(gamepad1.x){
            robot.marker.setPosition(1);
        }

        if(gamepad1.right_bumper){ //up
            robot.slide.setPower(0.6);
        }
        else if(gamepad1.right_trigger != 0){ //down
            robot.slide.setPower(-0.4);
        }
        else{
            robot.slide.setPower(0);
        }
    }
    @Override
    public void stop() {
        robot.stopMotors();
    }
}
