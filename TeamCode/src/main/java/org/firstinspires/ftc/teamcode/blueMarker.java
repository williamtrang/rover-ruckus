package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "blue marker test", group = "test")
public class blueMarker extends autoMethods {
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
        encoderDrive(1,4,4);
        lowerSlide();
        //drive to corner
        encoderDrive(1,62,62);
        sleep(1000);
        robot.marker.setPosition(0);
        sleep(1000);
        turnLeft(48,0.8);
        sleep(1000);
        robot.marker.setPosition(0.9);
        sleep(1000);
        encoderDrive(1,-90,-90);
    }
}
