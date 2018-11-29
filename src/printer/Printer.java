package printer;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.Color;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.RegulatedMotorListener;
import printer.hardware.PaperController;
import printer.hardware.PrintHead;
import printer.hardware.Worker;
import printer.hardware.motors.AdvancedMotor;
import printer.hardware.motors.Motors;
import printer.hardware.sensors.CheckedSensor;
import printer.hardware.sensors.SensorListener;

public class Printer implements Worker {

	private boolean isWorking = false;

	public static void main(String[] args) {

		new Printer();
		System.exit(0);
	}

	public Printer() {
		PrintHead.instance.resetHead();
		System.out.println("Calibrated");
		waitForPaper();
		System.out.println("Paper Ready!");
		feedPaper();

		PrintController pc = PrintController.instance;

		// pc.print(new HausVomNikolaus(Coordinate.NULL_POINT, new Coordinate(100, 100,
		// Unit.METRIC),
		// new Coordinate(50, 150, Unit.METRIC)));

		// pc.print(new Triangle(Coordinate.NULL_POINT, new Coordinate(100, 100,
		// Unit.METRIC),
		// new Coordinate(150, 50, Unit.METRIC)).scale(1.0F));

		// pc.print(new Alphabet.A(Coordinate.NULL_POINT, new Coordinate(20, 60,
		// Unit.METRIC),
		// new Coordinate(40, 0, Unit.METRIC)));

		// pc.printLine(Coordinate.NULL_POINT, new Coordinate(20, 80, Unit.METRIC));
		// pc.printLine(new Coordinate(20, 80, Unit.METRIC), new Coordinate(40, 0,
		// Unit.METRIC));

		// pc.printLine(new Coordinate(50, 50, Unit.PERCENT), new Coordinate(20, 50,
		// Unit.PERCENT));
		// pc.printLine(new Coordinate(50, 50, Unit.PERCENT), new Coordinate(70, 20,
		// Unit.PERCENT));

		// pc.print(new Circle(new Coordinate(20, 20, Unit.PERCENT), 20F,
		// Unit.PERCENT));

		// pc.print(new Alphabet.B(new Coordinate(60, 20, Unit.PERCENT), 30,
		// Unit.PERCENT));

		// pc.print(new Alphabet.C(new Coordinate(10, 10, Unit.PERCENT), 10,
		// Unit.PERCENT));

		// pc.print(new Alphabet.D(new Coordinate(0, 0, Unit.PERCENT), 20,
		// Unit.PERCENT));

		// pc.print(new Alphabet.E(new Coordinate(40, 0, Unit.PERCENT), 20, 20,
		// Unit.PERCENT));

		// TextPrinter tp = new TextPrinter(pc);
		// tp.setFontSize(15, Unit.PERCENT);
		// tp.setMargin(4, Unit.PERCENT);
		// tp.printString("Banane\nGanga");

		PaperController.instance.ejectPaper();
	}

	public static void waitForWorker(Worker worker) {

		do {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (worker.hasToWait());

	}

	private void waitForPaper() {

		isWorking = true;

		EV3ColorSensor csensor = new EV3ColorSensor(SensorPort.S1);
		SensorMode mode = csensor.getColorIDMode();

		final CheckedSensor checkedSensor = new CheckedSensor(csensor, mode);
		SensorListener listener = new SensorListener() {

			@Override
			public void notifyOnChange(float[] value, CheckedSensor source) {

				if (value[0] != Color.WHITE) return;
				try {

					Thread.sleep(2000);
					isWorking = false;
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					checkedSensor.close();
				}
			}
		};

		checkedSensor.addListener(listener);
		waitForWorker(this);
	}

	private void feedPaper() {
		isWorking = true;
		final AdvancedMotor yMotor = new AdvancedMotor(Motors.Y_MOTOR);
		yMotor.setSpeed(500);
		RegulatedMotorListener listener = new RegulatedMotorListener() {

			@Override
			public void rotationStopped(RegulatedMotor motor, int tachoCount, boolean stalled, long timeStamp) {
				isWorking = false;
				yMotor.close();
			}

			@Override
			public void rotationStarted(RegulatedMotor motor, int tachoCount, boolean stalled, long timeStamp) {
			}
		};
		yMotor.addListener(listener);

		yMotor.rotate(Motors.Y_MOTOR.max_degree);
		waitForWorker(this);
	}

	@Override
	public boolean hasToWait() {
		return isWorking;
	}
}
