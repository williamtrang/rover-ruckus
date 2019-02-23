package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous (name = "lower robot only", group = "pikaReal")
@Disabled
public class lowerOnly extends autoMethods{
    @Override
    public void runOpMode(){
        robot.init(hardwareMap);
        setMotorModes();

        waitForStart();

        //lower from lander
        lowerRobot();
        encoderDrive(1,4,4);
        lowerSlide();
    }
}
