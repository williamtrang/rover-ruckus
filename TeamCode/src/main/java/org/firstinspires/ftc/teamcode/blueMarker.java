package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;

@Disabled
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
        openTail();
        sleep(1000);
        turnLeft(45,0.5);
        sleep(1000);
        closeTail();
        sleep(1000);

        //drive towards crater
        timeDrive(-1,4000);
    }
}
