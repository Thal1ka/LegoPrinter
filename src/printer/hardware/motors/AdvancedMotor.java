package printer.hardware.motors;

import lejos.hardware.motor.EV3LargeRegulatedMotor;

public class AdvancedMotor extends EV3LargeRegulatedMotor {

	/* Degrees for 10cm distance */
	private final int transmissonRatio;

	public AdvancedMotor(Motors motor) {
		super(motor.port);
		this.transmissonRatio = motor.transmission_ratio;
	}

	public int getRotationForDistance(int distanceMM) {
		return (int) (transmissonRatio / 100.0 * distanceMM + 0.5);
	}

}
