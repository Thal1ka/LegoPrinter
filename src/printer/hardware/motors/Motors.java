package printer.hardware.motors;

import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;

public enum Motors {

	X_MOTOR(MotorPort.B, 980, 1470), Y_MOTOR(MotorPort.C, 720, 2400), Z_MOTOR(MotorPort.A, 1, Integer.MAX_VALUE);

	public final Port port;
	public final int transmission_ratio;
	public final int max_degree;

	Motors(Port port, int transmission_ratio, int max_degree) {
		this.port = port;
		this.transmission_ratio = transmission_ratio;
		this.max_degree = max_degree;
	}
}
