package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp (name="collector test",group="test2")
@Disabled
public class OnePortTest extends OpMode {
    public DcMotor arm;
    public void init(){
        arm = hardwareMap.get(DcMotor.class,"arm");
    }
    @Override
    public void init_loop(){

    }
    @Override
    public void start(){

    }
    @Override
    public void loop(){
        if(gamepad1.a){
            arm.setPower(0.5);
        }
        else if(gamepad1.b){
            arm.setPower(-0.5);
        }
        else{
            arm.setPower(0);
        }
    }
}
