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
        encoderDrive(0.2,1.55,1.55);
        lowerSlide();
        //drive into crater
        encoderDrive(0.2,5,5);

    }
}
