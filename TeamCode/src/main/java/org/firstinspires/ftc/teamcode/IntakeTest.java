package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp (name="Intake Test",group="test2")
@Disabled
public class IntakeTest extends OpMode {
    public DcMotor left1,right1;
    public CRServo extleft,extright;
    public DcMotor collect;
    public void init(){
        left1 = hardwareMap.get(DcMotor.class, "left1");
        right1 = hardwareMap.get(DcMotor.class, "right1");
        extleft = hardwareMap.get(CRServo.class, "extleft");
        extright = hardwareMap.get(CRServo.class, "extright");
        collect = hardwareMap.get(DcMotor.class,"collect");
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
            extleft.setPower(0.5);
            extright.setPower(-0.5);
        }
        else if(gamepad1.b){
            extleft.setPower(-0.5);
            extright.setPower(0.5);
        }
        else{
            extleft.setPower(0);
            extright.setPower(0);
        }

        if(gamepad1.x){
            collect.setPower(0.5);
        }
        else if(gamepad1.y){
            collect.setPower(-0.5);
        }
        else{
            collect.setPower(0);
        }
    }
    @Override
    public void stop(){

    }
}
