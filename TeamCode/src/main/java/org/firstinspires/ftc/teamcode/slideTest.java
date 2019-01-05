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

        final double LEFT_POWER_REVERSE = gamepad2.left_stick_y*.8;
        final double RIGHT_POWER_REVERSE = gamepad2.right_stick_y*.8;
        robot.right.setPower(-LEFT_POWER_REVERSE);
        robot.left.setPower(-RIGHT_POWER_REVERSE);

        //open
        if(gamepad1.a){
            robot.marker.setPosition(0);
        }
        //closed
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
