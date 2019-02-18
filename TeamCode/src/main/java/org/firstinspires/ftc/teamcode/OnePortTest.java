package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp (name="collector test",group="test2")
@Disabled
public class OnePortTest extends OpMode {
    public DcMotor leftServo;
    public void init(){
        leftServo = hardwareMap.get(DcMotor.class,"motor");
        //rightServo = hardwareMap.get(CRServo.class,"servo2");
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
            leftServo.setPower(0.5);
            //rightServo.setPower(-1);
        }
        else if(gamepad1.b){
            leftServo.setPower(-0.5);
            //rightServo.setPower(1);
        }
        else{
            leftServo.setPower(0);
            //rightServo.setPower(0);
        }
    }
}
