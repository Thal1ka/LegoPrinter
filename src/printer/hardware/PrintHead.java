package printer.hardware;

import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.RegulatedMotorListener;
import printer.Printer;
import printer.hardware.motors.AdvancedMotor;
import printer.hardware.motors.Motors;
import printer.hardware.sensors.CheckedSensor;
import printer.hardware.sensors.SensorConstants;
import printer.hardware.sensors.SensorListener;

public class PrintHead implements Worker {

	public static final PrintHead instance = new PrintHead();

	private final AdvancedMotor xMotor;
	private final AdvancedMotor zMotor;

	private boolean isWorking = false;

	private boolean isHeadUp = true;

	private PrintHead() {
		this.xMotor = new AdvancedMotor(Motors.X_MOTOR);

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
		xMotor.addListener(listener);
		this.zMotor = new AdvancedMotor(Motors.Z_MOTOR);
	}

	public void headUp() {
		if (!isHeadUp) {
			this.zMotor.rotate(180);
			isHeadUp = true;
		}
	}

	public void headDown() {
		if (isHeadUp) {
			this.zMotor.rotate(180);
			isHeadUp = false;
		}
	}

	public void move(int dx) {
		xMotor.rotate(dx);
	}

	public void resetHead() {

		isWorking = true;
		final CheckedSensor checkedSensor = new CheckedSensor(new EV3TouchSensor(SensorConstants.TOUCHSENSOR_PORT));
		SensorListener listener = new SensorListener() {

			@Override
			public void notifyOnChange(float[] value, CheckedSensor source) {

				if (value[0] != 0) {
					xMotor.stop();
					checkedSensor.close();
					isWorking = false;
				}
			}
		};

		xMotor.setSpeed(500);
		xMotor.forward();
		checkedSensor.addListener(listener);
		Printer.waitForWorker(this);
	}

	protected AdvancedMotor getMotor() {
		return xMotor;
	}

	public void synchronizeWith(PaperController paperController) {

		xMotor.synchronizeWith(new RegulatedMotor[] { paperController.getMotor() });
		xMotor.setSpeed(200);
		paperController.getMotor().setSpeed(600);
	}

	@Override
	public boolean hasToWait() {
		return isWorking;
	}
}
