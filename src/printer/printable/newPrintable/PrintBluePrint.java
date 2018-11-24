package printer.printable.newPrintable;

import java.util.ArrayList;
import java.util.List;

import printer.PrintController;
import printer.printable.Coordinate;

public class PrintBluePrint {

	private List<Printable> printables = new ArrayList<>();
	private float rotation = 0F;
	private Coordinate lastCoordinate = Coordinate.NULL_POINT;

	public PrintBluePrint add(Printable printable) {

		printables.add(printable);
		lastCoordinate = printable.getEndCoordinate();
		return this;
	}

	public PrintBluePrint addLine(Coordinate target) {
		return add(new Line(lastCoordinate, target));
	}

	public PrintBluePrint addVector(Vector vector) {
		return addLine(lastCoordinate.addVector(vector));
	}

	public void translate(Coordinate newRoot) {
		lastCoordinate = newRoot;
	}

	public void rotate(float ankle) {

		this.rotation += ankle;
	}

	public PrintSequence getPrintSequence() {

		List<DrawVector> drawVectors = new ArrayList<>();
		Coordinate startCoordinate = null;
		Coordinate printPosition = PrintController.instance.getCurrentPosition();

		for (Printable printable : printables) {

			Coordinate printableStart = printable.getStartCoordinate();

			if (startCoordinate == null) {
				startCoordinate = printableStart;
			}

			if (!printPosition.equals(printableStart)) {
				drawVectors.add(new DrawVector(printPosition.getVectorTo(printableStart), false));
			}

			for (Vector vector : printable.getVectors()) {
				drawVectors.add(new DrawVector(vector, true));
			}

			printPosition = printable.getEndCoordinate();
		}

		return new PrintSequence(startCoordinate, drawVectors, rotation);
	}
}
