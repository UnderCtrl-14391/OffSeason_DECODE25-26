package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "Testes", group = "TeleOp")
public class teleop extends LinearOpMode {

    DcMotorEx Intake;
    //DcMotorEx motorEsquerdo;

    DcMotor FrenteD;

    DcMotor FrenteE;

    DcMotor TrasE;

    DcMotor TrasD;
    final double TICKS_POR_VOLTA = 28.0;

    private final ElapsedTime runtime = new ElapsedTime();

    public double INTAKE_IN_POWER = 0.9;
    public double INTAKE_OUT_POWER = -0.9;


    @Override
    public void runOpMode() {

        TrasD = hardwareMap.get(DcMotor.class, "TrasD");
        TrasE = hardwareMap.get(DcMotor.class, "TrasE");
        FrenteE = hardwareMap.get(DcMotor.class, "FrenteE");
        FrenteD = hardwareMap.get(DcMotor.class,"FrenteD");
        Intake = hardwareMap.get(DcMotorEx.class, "motorEsquerdo");
       // motorEsquerdo = hardwareMap.get(DcMotorEx.class, "motorDireito");

        Intake.setDirection(DcMotorEx.Direction.REVERSE);
        //motorEsquerdo.setDirection(DcMotorSimple.Direction.REVERSE);
        TrasE.setDirection(DcMotorSimple.Direction.FORWARD);
        TrasD.setDirection(DcMotorSimple.Direction.FORWARD);
        FrenteE.setDirection(DcMotorSimple.Direction.FORWARD);
        FrenteD.setDirection(DcMotorSimple.Direction.FORWARD);

        TrasE.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        TrasD.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FrenteD.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FrenteE.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //motorEsquerdo.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Intake.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        FrenteE.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FrenteD.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        TrasE.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        TrasD.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //motorEsquerdo.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();

        while (opModeIsActive()) {

            double potencia = -gamepad1.right_stick_y;

            double forward = -gamepad1.left_stick_y;
            double strafe = -gamepad1.left_stick_x;
            double rotate = -gamepad1.right_stick_x;

            FrenteD.setPower(forward);
            FrenteE.setPower(forward);
            TrasD.setPower(forward);
            TrasE.setPower(forward);
            //motorEsquerdo.setPower(potencia);
            Intake.setPower(potencia);


            FrenteD.setPower(strafe);
            FrenteE.setPower(strafe);
            TrasD.setPower(strafe);
            TrasE.setPower(strafe);

            FrenteD.setPower(rotate);
            FrenteE.setPower(rotate);
            TrasD.setPower(rotate);
            TrasE.setPower(rotate);


            // RPM dos motores
            double rpmEsquerdo =
                    Intake.getVelocity() * 60.0 / TICKS_POR_VOLTA;

            double rpmDireito =
                    Intake.getVelocity() * 60.0 / TICKS_POR_VOLTA;

            // Mostra no Driver Station
            telemetry.addData("Potencia", "%.2f", potencia);

            telemetry.addData("RPM Esquerdo", "%.1f", rpmEsquerdo);
            telemetry.addData("RPM Direito", "%.1f", rpmDireito);

            telemetry.addData("Encoder Esquerdo",
                    Intake.getCurrentPosition());

            telemetry.addData("Encoder Direito",
                    Intake.getCurrentPosition());

            telemetry.update();
        }
    }
}