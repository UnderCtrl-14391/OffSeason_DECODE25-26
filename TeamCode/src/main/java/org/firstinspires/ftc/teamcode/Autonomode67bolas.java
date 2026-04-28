package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous
public class Autonomode67bolas {
    public DcMotor FrenteD, FrenteE, TrasE, TrasD, Intake, indexer, Catapulta1, Catapulta2;

    public double CATAPULTA_UP_POWER = 1.0;
    public double CATAPULTA_DOWN_POWER = -1.0;
    public double CATAPULTA_HOLD_POWER = -0.15;

    public enum CatapultaModes {UP, DOWN, HOLD};
        public void HardwareMap(com.qualcomm.robotcore.hardware.HardwareMap HardwareMap) {
        FrenteE = HardwareMap.get (DcMotor.class, "FrenteE");
        FrenteD = HardwareMap.get (DcMotor.class, "FrenteD");
        TrasE = HardwareMap.get (DcMotor.class, "TrasE");
        TrasD = HardwareMap.get (DcMotor.class, "TrasD");
        indexer = HardwareMap.get (DcMotor.class, "indexer");
        Intake = HardwareMap.get (DcMotor.class, "Intake");
        Catapulta1 = HardwareMap.get (DcMotor.class, "Catapulta1");
        Catapulta2 = HardwareMap.get (DcMotor.class, "Catapulta2");

        FrenteE.setDirection(DcMotorSimple.Direction.FORWARD);
        FrenteD.setDirection(DcMotorSimple.Direction.FORWARD);
        TrasE.setDirection(DcMotorSimple.Direction.FORWARD);
        TrasD.setDirection(DcMotorSimple.Direction.FORWARD);
        Intake.setDirection(DcMotorSimple.Direction.FORWARD);
        indexer.setDirection(DcMotorSimple.Direction.FORWARD);
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
        switch (mode){
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

        public void autonomous(int DFrente, int EFrente, int ETras, int DTras, double Dpower, double Epower, int indexer, int IntakeP, int CatapultaModes) {

            FrenteD.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            FrenteE.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }


}
