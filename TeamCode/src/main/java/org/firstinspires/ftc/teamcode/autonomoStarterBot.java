package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous (name = "autonomoStarterBot", group = "Autonomous" )
public class autonomoStarterBot extends LinearOpMode {

    private final ElapsedTime runtime = new ElapsedTime();
    DcMotor esquerdaTras;
    DcMotor direitaTras;
    DcMotor esquerda;
    DcMotor direita;
    DcMotor intake;
    DcMotor shooter;
    Servo crServo;

    public void HardwareMap(com.qualcomm.robotcore.hardware.HardwareMap hardwareMap) {

        esquerdaTras = hardwareMap.get(DcMotor.class, "esquerdaTras");
        direitaTras = hardwareMap.get(DcMotor.class, "direitaTras");
        esquerda = hardwareMap.get(DcMotor.class, "esquerda");
        direita = hardwareMap.get(DcMotor.class, "direita");
        intake = hardwareMap.get(DcMotor.class, "intake");
        shooter = hardwareMap.get(DcMotor.class, "shooter");
        crServo = hardwareMap.get(Servo.class, "crServo");

        crServo.setDirection(Servo.Direction.FORWARD);
        esquerdaTras.setDirection(DcMotor.Direction.FORWARD);
        direitaTras.setDirection(DcMotor.Direction.FORWARD);
        esquerda.setDirection(DcMotor.Direction.FORWARD);
        direita.setDirection(DcMotor.Direction.FORWARD);
        intake.setDirection(DcMotor.Direction.FORWARD);
        shooter.setDirection(DcMotor.Direction.FORWARD);

        esquerdaTras.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        esquerda.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        direita.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        direitaTras.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    public void stopMotors() {
        direita.setPower(0);
        direitaTras.setPower(0);
        esquerda.setPower(0);
        esquerdaTras.setPower(0);
    }

    public void stopResetMotors() {
        direita.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        direitaTras.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        esquerdaTras.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        esquerda.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void autonomous(int direitaFrente, int direitaTrass, int esquerdaTrass, int esquerdaFrente, int crservo, double direitaP, double esquerdaP, double timerLimit) {
        direita.setTargetPosition(direitaFrente);
        esquerda.setTargetPosition(esquerdaFrente);
        esquerdaTras.setTargetPosition(esquerdaTrass);
        esquerda.setTargetPosition(esquerdaFrente);

        direita.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        direitaTras.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        esquerda.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        esquerdaTras.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        direita.setPower(direitaP);
        direitaTras.setPower(direitaP);
        esquerda.setPower(esquerdaP);
        esquerdaTras.setPower(esquerdaP);

        runtime.reset();

        while (opModeIsActive() && runtime.seconds() < timerLimit && (direita.isBusy() || direitaTras.isBusy() || esquerda.isBusy() || esquerdaTras.isBusy())) {
            telemetry.addData("direita", direita.getCurrentPosition());
            telemetry.update();
        }
        stopMotors();

    }

    @Override
    public void runOpMode(){
        HardwareMap(hardwareMap);
        waitForStart();
    }
}
