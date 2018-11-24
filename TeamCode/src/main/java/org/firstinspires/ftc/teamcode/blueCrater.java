package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "blue crater tests", group = "test")
public class blueCrater extends autoMethods {
    @Override
    public void runOpMode(){
        robot.init(hardwareMap);

        robot.left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.slide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        lowerRobot();
        //drive into crater
        turnLeft(45,0.3);
        encoderDrive(0.2,-36,-36);
        turnLeft(80,0.3);
        encoderDrive(1, -75, -75);
        robot.marker.setPosition(0);
        sleep(1500);
        robot.marker.setPosition(0.99);
        turnLeft(10,0.2);
        encoderDrive(1,80,80);


    }
}
