package printer.hardware.sensors;

import java.util.ArrayList;
import java.util.List;

class SensorManager implements Runnable {
	private final int updateInterval = 50;

	public final static SensorManager instance = new SensorManager();
	private List<CheckedSensor> sensors = new ArrayList<>();

	private SensorManager() {
		new Thread(this).start();
	}

	public void registerSensor(CheckedSensor sensor) {
		sensors.add(sensor);
	}

	public void deregisterSensor(CheckedSensor sensor) {
		sensors.remove(sensor);
	}

	@Override
	public void run() {
		while (true) {

			List<CheckedSensor> sensorsCopy = new ArrayList<>(sensors);

			for (CheckedSensor sensor : sensorsCopy) {
				sensor.update();
			}

			try {
				Thread.sleep(updateInterval);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
