package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp (name="Intake Test",group="test2")
public class IntakeTest extends OpMode {
    public DcMotor left1,right1;
    public Servo extleft,extright;
    public void init(){
        left1 = hardwareMap.get(DcMotor.class, "left1");
        right1 = hardwareMap.get(DcMotor.class, "right1");
        extleft = hardwareMap.get(Servo.class, "extleft");
        extright = hardwareMap.get(Servo.class, "extright");
        //collect = hardwareMap.get(Servo.class,"collect");
        right1.setDirection(DcMotorSimple.Direction.REVERSE);

        left1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    @Override
    public void init_loop(){

    }
    @Override
    public void start(){

    }
    @Override
    public void loop(){
        left1.setPower(gamepad1.left_stick_y*0.4);
        right1.setPower(gamepad1.left_stick_y*0.4);
        if(gamepad1.a){
            extleft.setPosition(extleft.getPosition()-0.1);
            extright.setPosition(extright.getPosition()+0.1);
        }
        else if(gamepad1.b){
            extleft.setPosition(extleft.getPosition()+0.1);
            extright.setPosition(extright.getPosition()-0.1);
        }
        else{
            extleft.setPosition(0.5);
            extright.setPosition(0.5);
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
    public void stop(){

    }
}
