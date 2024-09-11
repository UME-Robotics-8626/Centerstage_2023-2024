package org.firstinspires.ftc.teamcode;

// @Note This lesson will explain what a Basic_Hardware is, how it functions, and how to structure it.
// @Note @Description is not essential to understanding what is going on but is useful it you lose track
//This contains the folder the program is found in within in the Android Studio project and must be inside program; typically it is above all the other code.

//We import LinearOpMode because it is essential for RunTime
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;

//We can create a class called Basic_Hardware and extend LinearOpMode.
//LinearOpMode contains a number of methods and variables that help structure and define the program for runtime.
public class BasicHardware {
    //A 'Basic_Hardware' is a structure that holds all of the .
    //You can imagine a hardware as a box of legos.
    //When you want to add something to your lego collection you must add something to your lego box.

    //Here is an example of how to define a motor.
    protected DcMotor backLeft;
    protected DcMotor frontLeft;
    protected DcMotor backRight;
    protected DcMotor frontRight;
    protected DcMotor theArm;
    protected DcMotor theArm2;
    protected DcMotor pulley;
    protected WebcamName Webcam; //so creative I know
    protected ColorSensor colorSensor; //don't judge me




    // We can do the same for Servos and CRServos
    protected Servo leftHatch, rightHatch, airplaneServo;
    protected CRServo leftStar, rightStar;

    protected void initialize(HardwareMap hMap) {
        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        backLeft  = hMap.get(DcMotor.class, "back_left_drive");
        backRight  = hMap.get(DcMotor.class, "back_right_drive");
        frontLeft = hMap.get(DcMotor.class, "front_left_drive");
        frontRight = hMap.get(DcMotor.class, "front_right_drive");
        theArm = hMap.get(DcMotor.class, "the_arm");
        theArm2 = hMap.get(DcMotor.class, "the_arm2");
        pulley = hMap.get(DcMotor.class, "pulley");
        rightStar = hMap.get(CRServo.class, "right_star");
        leftStar = hMap.get(CRServo.class, "left_star");
        rightHatch = hMap.get(Servo.class, "right_hatch");
        leftHatch = hMap.get(Servo.class, "left_hatch");
        airplaneServo = hMap.get(Servo.class, "airplane_servo");
        Webcam = hMap.get(WebcamName.class, "Webcam 1");
        colorSensor = hMap.get(ColorSensor.class, "sensor_color");

        // Set the motor directions. Two motors will n eed to be reversed
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.FORWARD);
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        theArm.setDirection(DcMotor.Direction.FORWARD);
        theArm2.setDirection(DcMotor.Direction.REVERSE);
        pulley.setDirection(DcMotor.Direction.FORWARD);

        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        theArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        theArm2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        pulley.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);



        //We can follow these same steps for the  rest of the objects with a few slight changes (mainly class reference name changes).


        leftHatch.setDirection(Servo.Direction.FORWARD);
        rightHatch.setDirection(Servo.Direction.REVERSE);
        leftStar.setDirection(CRServo.Direction.FORWARD);
        rightStar.setDirection(CRServo.Direction.REVERSE);
        airplaneServo.setDirection(Servo.Direction.FORWARD);
    }
}