package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;

@Disabled
@Autonomous(name = "blue crater", group = "test")
public class blueCrater extends autoMethods {
    @Override
    public void runOpMode(){
        robot.init(hardwareMap);
        setMotorModes();

        waitForStart();

        //lower robot from lander and drive back
        lowerRobot();
        encoderDrive(1,12,12);
        //lowerSlide();
        sleep(500);

        //turn towards wall and drive to depot
        turnLeft(42,0.5);
        encoderDrive(1,36,36);
        turnLeft(80,0.5);
        encoderDrive(1, 75, 75);

        //drop team marker
        robot.marker.setPosition(OPEN);
        sleep(1500);
        robot.marker.setPosition(CLOSED);
        sleep(600);

        //turn and drive to crater
        turnLeft(130,0.5);
        encoderDrive(1,-84,-84);
    }
}
