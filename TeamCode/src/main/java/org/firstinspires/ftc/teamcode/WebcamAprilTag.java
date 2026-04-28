package org.firstinspires.ftc.teamcode;

import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

@TeleOp
public class WebcamAprilTag extends LinearOpMode {
    VisionPortal  visionPortal;

    @Override
    public void runOpMode(){
        AprilTagProcessor myAprilTagProcessor;
        // Create the AprilTag processor and assign it to a variable.
        myAprilTagProcessor = AprilTagProcessor.easyCreateWithDefaults();

        // Enable or disable the AprilTag processor.
        visionPortal.setProcessorEnabled(myAprilTagProcessor, true);

        visionPortal= VisionPortal.easyCreateWithDefaults(hardwareMap.get(WebcamName.class, "Webcam"), myAprilTagProcessor);

        visionPortal = new VisionPortal.Builder()
                .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))
                .addProcessor(myAprilTagProcessor)
                .setCameraResolution(new Size(640, 480))
                .setStreamFormat(VisionPortal.StreamFormat.YUY2)
                .enable(true)
                .setAutoStopLiveView(true)
                .build();

        waitForStart();

        while(opModeIsActive()){

        }

    }


}
