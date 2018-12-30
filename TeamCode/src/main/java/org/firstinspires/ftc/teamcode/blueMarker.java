package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "blue marker test", group = "test")
public class blueMarker extends autoMethods {
    @Override
    public void runOpMode(){
        robot.init(hardwareMap);
        setMotorModes();

        waitForStart();

        //lower from lander
        lowerRobot();
        encoderDrive(1,4,4);
        lowerSlide();

        //drive to corner
        encoderDrive(1,62,62);
        sleep(1000);

        //drop team marker in depot and turn toward crater
        robot.marker.setPosition(OPEN);
        sleep(1000);
        turnLeft(45,0.5);
        sleep(1000);
        robot.marker.setPosition(CLOSED);
        sleep(1000);

        //drive towards crater
        robot.left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.left.setPower(-1);
        robot.right.setPower(-1);
        sleep(4000);
    }
}
