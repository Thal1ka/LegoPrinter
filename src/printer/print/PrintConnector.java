package printer.print;

import java.util.ArrayList;
import java.util.List;

import printer.old_printable.Coordinate;
import printer.print.printable.Printable;

public class PrintConnector extends Printable {

	private List<PositionedPrintable> connectedPrintables = new ArrayList<>();

	private Vector translationVector = new Vector(0, 0, Unit.DEGREE);
	private Coordinate startCoordinate;
	private Coordinate endCoordinate;

	public PrintConnector(Printable printable) {

		connectedPrintables.add(new PositionedPrintable(printable, translationVector.copy()));

		this.startCoordinate = printable.getStartCoordinate();
		this.endCoordinate = printable.getEndCoordinate();
	}

	public void add(Printable printable) {

		connectedPrintables.add(new PositionedPrintable(printable, translationVector.copy()));
		this.endCoordinate = printable.getEndCoordinate();
	}

	public void translate(Vector translation) {
		translationVector.plus(translation);
	}

	public void translate(float dx, float dy, Unit unit) {
		translate(new Vector(dx, dy, unit));
	}

	@Override
	public Coordinate getStartCoordinate() {
		return startCoordinate;
	}

	@Override
	public Coordinate getEndCoordinate() {
		return endCoordinate;
	}

	@Override
	public List<Vector> getVectors() {

		List<Vector> vectors = new ArrayList<>();
		Vector currentPosition = new Vector(0, 0, Unit.DEGREE); // StartCoordinate to Vector?

		for (PositionedPrintable positionedPrintable : connectedPrintables) {

		}

		return null;
	}

	private class PositionedPrintable {

		private final Printable printable;
		private final Vector translation;

		public PositionedPrintable(Printable printable, Vector translation) {

			this.printable = printable;
			this.translation = translation;
		}
	}
}
