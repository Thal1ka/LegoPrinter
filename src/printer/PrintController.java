package printer;

import printer.hardware.AdvancedMotorController;
import printer.hardware.PaperController;
import printer.hardware.PrintHead;
import printer.hardware.Worker;
import printer.hardware.motors.Motors;
import printer.old_printable.AdvancedPrintSequence;
import printer.old_printable.Coordinate;
import printer.old_printable.Line;
import printer.old_printable.PrintSequence;
import printer.old_printable.Printable;

public class PrintController implements Worker {

	public static final PrintController instance = new PrintController();

	private final PrintHead printHead = PrintHead.instance;
	private final PaperController paperController = PaperController.instance;
	private final AdvancedMotorController advancedMotorController = new AdvancedMotorController(printHead, paperController);

	private float currentX = Motors.X_MOTOR.max_degree;
	private float currentY = 0F;

	private final int xMaxDegree = Motors.X_MOTOR.max_degree;
	private final int yMaxDegree = Motors.Y_MOTOR.max_degree;

	private boolean isWorking = false;

	private PrintController() {

	}

	public void headUp() {
		printHead.headUp();
	}

	public void headDown() {
		printHead.headDown();
	}

	public void print(PrintSequence sequence) {

		while (sequence.hasNext()) {

			Line line = sequence.next();

			if (sequence.hasToLift()) {
				headUp();
			}

			moveTo(line.start);
			headDown();
			moveTo(line.end);

		}

		headUp();
	}

	public void print(AdvancedPrintSequence printSequence) {

		Coordinate last = null;

		for (Printable printable : printSequence) {

			if (!printable.start.equals(last)) {
				headUp();
			}

			moveTo(printable.start);
			headDown();
			moveTo(printable.end);
			last = printable.end;
		}

		headUp();
	}

	public void printLine(Coordinate start, Coordinate stop) {

		if (!(isPointValid(start.x, start.y) && isPointValid(stop.x, stop.y))) throw new IllegalArgumentException();

		headUp();

		moveTo(start);
		headDown();
		moveTo(stop);

		headUp();

	}

	public void move(float dx, float dy) {

		if (dx == 0 && dy == 0) return;

		if (dy == 0) {
			moveHorizontal(dx);
		} else if (dx == 0) {
			moveVertical(dy);
		} else {
			moveOrthagonal(dx, dy);
		}

		currentX += dx;
		currentY += dy;
	}

	private void moveTo(Coordinate target) {
		float dx = target.x - currentX;
		float dy = target.y - currentY;

		move(dx, dy);
	}

	private void moveHorizontal(float dx) {
		advancedMotorController.setSpeedRatio(1F);
		printHead.move(Math.round(dx));
	}

	private void moveVertical(float dy) {
		advancedMotorController.setSpeedRatio(1F);
		paperController.move(Math.round(dy));

	}

	private void moveOrthagonal(float dx, float dy) {
		advancedMotorController.setSpeedRatio(dx / dy);
		advancedMotorController.prepare();
		{
			printHead.move(Math.round(dx));
			paperController.move(Math.round(dy));
		}
		advancedMotorController.startExecution();
		Printer.waitForWorker(printHead);
		Printer.waitForWorker(paperController);
	}

	private boolean isPointValid(float x, float y) {
		return x >= 0 && x <= xMaxDegree && y >= 0 && y <= yMaxDegree;
	}

	public Coordinate getCurrentPosition() {
		return new Coordinate(currentX, currentY);
	}

	@Override
	public boolean hasToWait() {

		return isWorking;
	}
}
