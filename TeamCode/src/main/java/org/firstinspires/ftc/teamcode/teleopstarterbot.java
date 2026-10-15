package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;




    @TeleOp(name="strterbot")
    public class teleopstarterbot extends LinearOpMode{

        private DcMotor frontLeftMotor, BackLeftMotor, frontRightMotor, BackRightMotor;
        private DcMotor intake, flywheel, flywheel2;
        private Servo crServo;
        private IMU imu;
        private int targetVelocity = 1160;
        private final ElapsedTime runtime = new ElapsedTime();

        public double INTAKE_IN_POWER = 0.7;
        public double INTAKE_OUT_POWER = -0.9;
        public double INTAKE_OFF_POWER = 0.0;
        public double SLOW_DRIVETRAIN = 0.3;

        private void flywheelControls() {
            if (gamepad1.y) {
                ((DcMotorEx) flywheel).setVelocity(targetVelocity);
                ((DcMotorEx) flywheel2).setVelocity(targetVelocity);
            } else if (gamepad1.x) {
                flywheel.setPower(0.55);
                flywheel2.setPower(-0.55);
            } else if (gamepad1.b) {
                ((DcMotorEx) flywheel).setVelocity(targetVelocity);
                ((DcMotorEx) flywheel2).setVelocity(targetVelocity);

                double v1 = ((DcMotorEx) flywheel).getVelocity();
                double v2 = ((DcMotorEx) flywheel2).getVelocity();

                crServo.setPosition(
                        v1 >= targetVelocity - 20 && v1 <= targetVelocity + 20 &&
                                v2 >= targetVelocity - 20 && v2 <= targetVelocity + 20 ? 1 : 0
                );
            } else if (gamepad1.dpad_up) {
                crServo.setPosition(-1);
            } else {
                flywheel.setPower(0);
                flywheel2.setPower(0);
                crServo.setPosition(0);
            }
        }

        public void drive(double forward, double strafe, double rotate) {
            double fl = forward + strafe + rotate;
            double bl = forward - strafe + rotate;
            double fr = forward + strafe - rotate;
            double br = forward - strafe - rotate;
            double max = Math.max(1.0, Math.max(
                    Math.max(Math.abs(fl), Math.abs(bl)),
                    Math.max(Math.abs(fr), Math.abs(br))
            ));

            frontLeftMotor.setPower(fl / max);
            BackLeftMotor.setPower(bl / max);
            frontRightMotor.setPower(fr / max);
            BackRightMotor.setPower(br / max);
        }

        public void driveFieldRelative(double forward, double strafe, double rotate) {
            double h = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
            double x = strafe * Math.cos(h) - forward * Math.sin(h);
            double y = strafe * Math.sin(h) + forward * Math.cos(h);
            drive(y, x, rotate);
        }

        @Override
        public void runOpMode() {

            frontLeftMotor = hardwareMap.get(DcMotor.class, "esquerdaTras");
            BackLeftMotor = hardwareMap.get(DcMotor.class, "esquerda");
            frontRightMotor = hardwareMap.get(DcMotor.class, "direitaTras");
            BackRightMotor = hardwareMap.get(DcMotor.class, "direita");
            intake = hardwareMap.get(DcMotor.class, "intake");
            flywheel = hardwareMap.get(DcMotor.class, "shooter");
            flywheel2 = hardwareMap.get(DcMotor.class, "shooter2");
            crServo = hardwareMap.get(Servo.class, "crServo");

            //frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
            //BackLeftMotor.setDirection(DcMotor.Direction.REVERSE);
            intake.setDirection(DcMotor.Direction.REVERSE);
            flywheel.setDirection(DcMotor.Direction.REVERSE);
            flywheel2.setDirection(DcMotor.Direction.FORWARD);
            crServo.setDirection(Servo.Direction.REVERSE);

            flywheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            flywheel2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            BackLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            BackRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            flywheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            flywheel2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

            imu = hardwareMap.get(IMU.class, "imu");

            imu.initialize(new IMU.Parameters(new RevHubOrientationOnRobot(
                    RevHubOrientationOnRobot.LogoFacingDirection.BACKWARD,
                    RevHubOrientationOnRobot.UsbFacingDirection.UP
            )));

            telemetry.addData("Status", "Initialized");
            telemetry.update();

            waitForStart();
            runtime.reset();

            while (opModeIsActive()) {

                double forward = gamepad1.left_stick_y;
                double strafe = -gamepad1.left_stick_x;
                double rotate = gamepad1.right_stick_x;

                if (gamepad1.right_trigger >= 0.05) {
                    forward *= SLOW_DRIVETRAIN;
                    strafe *= SLOW_DRIVETRAIN;
                    rotate *= SLOW_DRIVETRAIN;
                }

                driveFieldRelative(forward, strafe, rotate);

                if (gamepad1.right_bumper)
                    intake.setPower(INTAKE_IN_POWER);
                else if (gamepad1.left_bumper)
                    intake.setPower(INTAKE_OUT_POWER);
                else
                    intake.setPower(INTAKE_OFF_POWER);

                flywheelControls();

                telemetry.addData("Time", runtime.toString());
                telemetry.addData("Flywheel 1", ((DcMotorEx) flywheel).getVelocity());
                telemetry.addData("Flywheel 2", ((DcMotorEx) flywheel2).getVelocity());
                telemetry.addData("Target", targetVelocity);
                telemetry.addData("Heading",
                        imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));
                telemetry.update();
            }
        }
    }

