package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;

@Disabled
@Autonomous(name = "blue marker no crater", group = "test")
public class blueMarkNoCrater extends autoMethods {
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

        //drop team marker
        robot.marker.setPosition(OPEN);
        sleep(1000);
        robot.marker.setPosition(CLOSED);
        sleep(1000);
    }
}
