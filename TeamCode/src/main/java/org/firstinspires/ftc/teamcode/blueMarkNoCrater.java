package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "blue marker no crater", group = "test")
public class blueMarkNoCrater extends autoMethods {
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

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;

        robot.imu = hardwareMap.get(BNO055IMU.class, "imu");
        robot.imu.initialize(parameters);

        lowerRobot();
        //drive to corner
        robot.marker.setPosition(0);
        sleep(1000);
        turnLeft(45,0.2);
        sleep(1000);
        robot.marker.setPosition(1);
        //encoderDrive(0.1,-20,-20);

    }
}
