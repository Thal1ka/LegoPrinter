package printer.hardware;

import lejos.robotics.RegulatedMotor;
import lejos.robotics.RegulatedMotorListener;
import printer.hardware.motors.AdvancedMotor;
import printer.hardware.motors.Motors;

public class PaperController implements Worker {

	public static final PaperController instance = new PaperController();
	private boolean isWorking = false;

	private final AdvancedMotor yMotor;

	private PaperController() {
		this.yMotor = new AdvancedMotor(Motors.Y_MOTOR);
		RegulatedMotorListener listener = new RegulatedMotorListener() {

			@Override
			public void rotationStopped(RegulatedMotor motor, int tachoCount, boolean stalled, long timeStamp) {
				isWorking = false;

			}

			@Override
			public void rotationStarted(RegulatedMotor motor, int tachoCount, boolean stalled, long timeStamp) {
				isWorking = true;
			}
		};
		yMotor.addListener(listener);
	}

	public void move(int degrees) {

		yMotor.rotate(-degrees);
	}

	protected AdvancedMotor getMotor() {
		return yMotor;
	}

	public void ejectPaper() {
		yMotor.setSpeed(800);
		move(Motors.Y_MOTOR.max_degree);
	}

	@Override
	public boolean hasToWait() {

		return isWorking;
	}

}
