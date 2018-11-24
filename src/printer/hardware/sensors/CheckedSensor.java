package printer.hardware.sensors;

import java.util.ArrayList;
import java.util.List;

import lejos.hardware.sensor.BaseSensor;
import lejos.hardware.sensor.SensorMode;

public class CheckedSensor implements AutoCloseable {

	private final BaseSensor sensor;
	private final SensorMode sensorMode;
	private float[] lastScanValue;
	private List<SensorListener> listeners = new ArrayList<>();
	private List<SensorListener> newListeners = new ArrayList<>();

	public CheckedSensor(BaseSensor sensor) {
		this(sensor, null);
	}

	public CheckedSensor(BaseSensor sensor, SensorMode sensorMode) {
		this.sensor = sensor;
		this.sensorMode = sensorMode;
		this.lastScanValue = new float[sensor.sampleSize()];
		SensorManager.instance.registerSensor(this);
	}

	public void update() {

		float[] currentScan;

		if (sensorMode == null) {
			currentScan = new float[sensor.sampleSize()];
			sensor.fetchSample(currentScan, 0);
		} else {
			currentScan = new float[sensorMode.sampleSize()];
			sensorMode.fetchSample(currentScan, 0);
		}

		if (!scanValuesEqual(currentScan, lastScanValue)) {

			for (SensorListener listener : listeners) {
				listener.notifyOnChange(currentScan, this);
			}

			lastScanValue = currentScan;
		}

		for (SensorListener listener : newListeners) {
			listener.notifyOnChange(currentScan, this);
		}

		listeners.addAll(newListeners);
		newListeners.clear();
	}

	private boolean scanValuesEqual(float[] scan1, float[] scan2) {

		if (scan1.length != scan2.length)
			return false;
		for (int i = 0; i < scan1.length; i++) {
			if (scan1[i] != scan2[i])
				return false;
		}
		return true;
	}

	public void addListener(SensorListener listener) {
		newListeners.add(listener);
	}

	public boolean removeListener(SensorListener listener) {
		return listeners.remove(listener);
	}

	@Override
	public void close() {
		SensorManager.instance.deregisterSensor(this);
		sensor.close();
	}
}
