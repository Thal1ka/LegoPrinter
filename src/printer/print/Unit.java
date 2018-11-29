package printer.print;

import printer.hardware.motors.Motors;

public enum Unit {

	METRIC, DEGREE, PERCENT;

	private static final int xTransmissionRatio = Motors.X_MOTOR.transmission_ratio;
	private static final int yTransmissionRatio = Motors.Y_MOTOR.transmission_ratio;

	private static final int xMaxDegree = Motors.X_MOTOR.max_degree;
	private static final int yMaxDegree = Motors.Y_MOTOR.max_degree;

	public static float xToDegree(float coordinateValue, Unit unit) {
		switch (unit) {
		case METRIC:
			return Unit.xMetricToDegree(coordinateValue);
		case DEGREE:
			return coordinateValue;
		case PERCENT:
			return Unit.xPercentToDegree(coordinateValue);
		default:
			return coordinateValue;
		}
	}

	public static float yToDegree(float coordinateValue, Unit unit) {
		switch (unit) {
		case METRIC:
			return Unit.yMetricToDegree(coordinateValue);
		case DEGREE:
			return coordinateValue;
		case PERCENT:
			return Unit.yPercentToDegree(coordinateValue);
		default:
			return coordinateValue;
		}
	}

	private static float xMetricToDegree(float coordinateValue) {
		return coordinateValue / 100.0F * xTransmissionRatio;
	}

	private static float yMetricToDegree(float coordinateValue) {
		return coordinateValue / 100.0F * yTransmissionRatio;
	}

	private static float xPercentToDegree(float coordinateValue) {
		return coordinateValue / 100.0F * xMaxDegree;
	}

	private static float yPercentToDegree(float coordinateValue) {
		return coordinateValue / 100.0F * yMaxDegree;
	}
}
