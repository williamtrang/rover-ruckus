package org.firstinspires.ftc.teamcode;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "gold found test auto", group = "test")
public class goldFoundTest3 extends autoMethods{
    @Override
    public void runOpMode(){
        robot.init(hardwareMap);
        setMotorModes();
        robot.detector.enable();

        waitForStart();

        if(robot.detector.isFound()){
            //do something
        }
        else{
            //do something else
        }

        robot.detector.disable();

    }
}
