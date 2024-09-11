package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@TeleOp(name="Basic: TeleOpMode", group="TeleOp")//Adds the Basic TeleOpMode to the TeleOp group
public class BasicTeleOp extends LinearOpMode {
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private BasicHardware robot = new BasicHardware();

    // Setup a variable for each drive wheel to save power level for telemetry
    private double backLeftPower;
    private double backRightPower;
    private double frontLeftPower;
    private double frontRightPower;



    //Defines left stick and right stick drive inputs
    private double drive;
    private double strafe;
    private double turn;
    private double total;

    private final double MAXSPEED = 0.75;

    private final ElapsedTime decelerationTimer = new ElapsedTime();
    private double[] previousPower = new double[4];

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        robot.initialize(this.hardwareMap);
        //boolean clawPress = false;
        //robot.intakeHolder.setPosition(-1);
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            // POV Mode uses left stick to go forward, and right stick to turn.
            // - This uses basic math to combine motions and is easier to drive straight.
            drive = gamepad1.left_stick_y; //Checks the left stick on the controller for vertical offset
            strafe = gamepad1.left_stick_x; //Checks the left stick on the controller for horizontal offset
            turn = gamepad1.right_stick_x; //Checks the right stick on the controller for horizontal offset

            //Calculate required movement based on given inputs
            frontLeftPower = drive - strafe - turn;
            frontRightPower = drive + strafe + turn;
            backLeftPower = drive + strafe - turn;
            backRightPower = drive - strafe + turn;

            double max = Math.max(
                    1,
                    Math.max(
                            Math.max(
                                    Math.abs(backLeftPower),
                                    Math.abs(backRightPower)),
                            Math.max(
                                    Math.abs(frontLeftPower),
                                    Math.abs(frontRightPower)
                            )
                    )
            );

            backLeftPower /= max;
            backRightPower /= max;
            frontLeftPower /= max;
            frontRightPower /= max;

            // Send calculated power to wheels
            robot.backLeft.setPower(backLeftPower);
            robot.frontLeft.setPower(frontLeftPower);
            robot.backRight.setPower(backRightPower);
            robot.frontRight.setPower(frontRightPower);

            robot.theArm.setPower(gamepad2.left_stick_y / 1.3);
            robot.theArm2.setPower(-gamepad2.left_stick_y / 1.3);

            if (gamepad2.a) { //closes
                robot.leftHatch.setPosition(1);
                robot.rightHatch.setPosition(1);
            }

            if (gamepad2.b) { //opens
                robot.leftHatch.setPosition(0.1);
                robot.rightHatch.setPosition(0);
            }

            //CRservo |faulty?|
            if (gamepad2.x) {
                robot.leftStar.setPower(-1);
                robot.rightStar.setPower(-1);
            } else if (gamepad2.y) {
                robot.leftStar.setPower(1);
                robot.rightStar.setPower(1);
            } else {
                robot.leftStar.setPower(0);
                robot.rightStar.setPower(0);
            }

            robot.pulley.setPower(((gamepad2.right_trigger > 0.9)?1:0) - ((gamepad2.left_trigger > 0.9)?1:0d));

            //airplane |unfinished I guess|
            if(gamepad2.right_bumper) {
                robot.airplaneServo.setPosition(1);
            }

            if(gamepad2.left_bumper) {
                robot.airplaneServo.setPosition(0.75);
            }


            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            //telemetry.addData("Intended Motors", "back left (%.2f), back right (%.2f), front left (%.2f), front right (%.2f)", backLeftPower, backRightPower, frontLeftPower, frontRightPower);
            //telemetry.addData("Motors", "back left (%.2f), back right (%.2f), front left (%.2f), front right (%.2f)", robot.backLeft.getPower(), robot.backRight.getPower(), robot.frontLeft.getPower(), robot.frontRight.getPower());
            telemetry.addData("Launcher", "gamepad2 rb" , gamepad2.right_bumper);
            telemetry.addData("Arms","Arm 1 Power (%.2f), Arm 2 Power (%.2f)", robot.theArm.getPower(), robot.theArm2.getPower());
            telemetry.addData("Star Servos", "gamepad2 x (%b), left star power (%.2f), right star power (%.2f)",gamepad2.x, robot.leftStar.getPower(), robot.rightStar.getPower());
            telemetry.addData("Hatch Servos", "gamepad2 a (%b), left hatch position (%.2f), right hatch position (%.2f)",gamepad2.a, robot.leftHatch.getPosition(), robot.rightHatch.getPosition());
            telemetry.update();
        }
    }
}