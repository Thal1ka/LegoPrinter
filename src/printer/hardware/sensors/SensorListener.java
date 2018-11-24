package printer.hardware.sensors;

public interface SensorListener {
	void notifyOnChange(float[] value, CheckedSensor source);
}
