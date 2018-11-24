package printer.hardware;

import lejos.robotics.RegulatedMotor;
import printer.hardware.motors.AdvancedMotor;

public class AdvancedMotorController {

	private final int MAX_MOTOR_SPEED = 400;

	private final AdvancedMotor xMotor;
	private final AdvancedMotor yMotor;

	public AdvancedMotorController(PrintHead printHead, PaperController paperController) {

		xMotor = printHead.getMotor();
		yMotor = paperController.getMotor();

		xMotor.synchronizeWith(new RegulatedMotor[] { yMotor });
	}

	public void startExecution() {
		xMotor.endSynchronization();
	}

	public void prepare() {
		xMotor.startSynchronization();
	}

	public void setSpeedRatio(float ratio) {

		ratio = Math.abs(ratio);

		if (ratio > 1) {
			xMotor.setSpeed(MAX_MOTOR_SPEED);
			yMotor.setSpeed(MAX_MOTOR_SPEED / ratio);
		} else {
			yMotor.setSpeed(MAX_MOTOR_SPEED);
			xMotor.setSpeed(MAX_MOTOR_SPEED * ratio);
		}
	}

}
