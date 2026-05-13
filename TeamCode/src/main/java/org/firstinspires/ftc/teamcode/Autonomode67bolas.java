package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.Telemetry;

@Autonomous
public class Autonomode67bolas extends LinearOpMode {

    private final ElapsedTime runtime = new ElapsedTime();
    public DcMotor FrenteD, FrenteE, TrasE, TrasD, IntakeE, IntakeD, Catapulta1, Catapulta2;

    public double CATAPULTA_UP_POWER = 1.0;
    public double CATAPULTA_DOWN_POWER = -1.0;
    public double CATAPULTA_HOLD_POWER = -0.15;

    public enum CatapultaModes {UP, DOWN, HOLD};
        public Autonomode67bolas.CatapultaModes pivotMode = Autonomode67bolas.CatapultaModes.HOLD;
        public void initHardware(com.qualcomm.robotcore.hardware.HardwareMap HardwareMap) {
        FrenteE = HardwareMap.get (DcMotor.class, "FrenteE");
        FrenteD = HardwareMap.get (DcMotor.class, "FrenteD");
        TrasE = HardwareMap.get (DcMotor.class, "TrasE");
        TrasD = HardwareMap.get (DcMotor.class, "TrasD");
        IntakeE = HardwareMap.get (DcMotor.class, "indexer");
        IntakeD = HardwareMap.get (DcMotor.class, "Intake");
        Catapulta1 = HardwareMap.get (DcMotor.class, "Catapulta1");
        Catapulta2 = HardwareMap.get (DcMotor.class, "Catapulta2");

        FrenteE.setDirection(DcMotorSimple.Direction.FORWARD);
        FrenteD.setDirection(DcMotorSimple.Direction.FORWARD);
        TrasE.setDirection(DcMotorSimple.Direction.FORWARD);
        TrasD.setDirection(DcMotorSimple.Direction.FORWARD);
        IntakeE.setDirection(DcMotorSimple.Direction.FORWARD);
        IntakeD.setDirection(DcMotorSimple.Direction.FORWARD);
        Catapulta1.setDirection(DcMotorSimple.Direction.FORWARD);
        Catapulta2.setDirection(DcMotorSimple.Direction.REVERSE);

        FrenteE.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FrenteD.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        TrasE.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        TrasD.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Catapulta1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Catapulta2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void setCatapultaModes(CatapultaModes mode) {
        switch (mode) {
            case UP:
                Catapulta1.setPower(1.0);
                Catapulta2.setPower(1.0);
                break;

            case DOWN:
                Catapulta1.setPower(-1.0);
                Catapulta2.setPower(-1.0);
                break;

            case HOLD:
                Catapulta1.setPower(0.15);
                Catapulta2.setPower(0.15);
                break;

        }
    }
            public void stopMotors() {
                FrenteD.setPower(0);
                FrenteE.setPower(0);
                TrasD.setPower(0);
                TrasE.setPower(0);
        }

            public void stopResetMotors() {

                TrasD.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                FrenteD.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                FrenteE.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                TrasE.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            }


        public void autonomous(int DFrente, int EFrente, int ETras, int DTras, double Dpower, double Epower, int EIntake, int DIntake, int IntakeP, CatapultaModes CatapultaPower, double timerLimit) {

            FrenteD.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            FrenteE.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            TrasD.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            TrasE.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


            FrenteD.setTargetPosition(DFrente);
            FrenteE.setTargetPosition(EFrente);
            TrasE.setTargetPosition(ETras);
            TrasD.setTargetPosition(DTras);

            FrenteD.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            FrenteE.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            TrasE.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            TrasD.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            FrenteD.setPower(DFrente);
            FrenteE.setPower(EFrente);
            TrasE.setPower(ETras);
            TrasD.setPower(DTras);

            IntakeD.setPower(DIntake);
            IntakeE.setPower(EIntake);

            setCatapultaModes(CatapultaPower);

            resetRuntime();

            stopMotors();

        }

        public void CatapultaModes(){

            setCatapultaModes(CatapultaModes.DOWN);
            sleep(100);

            setCatapultaModes(CatapultaModes.UP);
            sleep(100);

        }
        @Override
        public void runOpMode() {

            initHardware(hardwareMap);
            waitForStart();

            autonomous(1000,1000,1000,1000,1.0,1.0,1,1,1, CatapultaModes.UP,1000);
        }
        }


