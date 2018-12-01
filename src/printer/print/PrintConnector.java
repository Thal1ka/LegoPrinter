package printer.print;

import java.util.ArrayList;
import java.util.List;

import printer.old_printable.Coordinate;
import printer.print.printable.Printable;

public class PrintConnector extends Printable {

	private List<PositionedPrintable> connectedPrintables = new ArrayList<>();

	private Vector translationVector = new Vector(0, 0, Unit.DEGREE);
	private float rotationAnkle = 0;
	private float scaling = 1;

	private Coordinate startCoordinate;
	private Coordinate endCoordinate;

	public PrintConnector(Printable printable) {

		connectedPrintables.add(new PositionedPrintable(printable, translationVector, rotationAnkle, scaling));

		this.startCoordinate = printable.getStartCoordinate();
		this.endCoordinate = printable.getEndCoordinate();
	}

	public void add(Printable printable) {

		connectedPrintables.add(new PositionedPrintable(printable, translationVector, rotationAnkle, scaling));
		this.endCoordinate = printable.getEndCoordinate();
	}

	public void translate(Vector translation) {
		translationVector.plus(translation);
	}

	public void translate(float dx, float dy, Unit unit) {
		translate(new Vector(dx, dy, unit));
	}

	public void setTranslation(Vector translation) {
		this.translationVector = translation;
	}

	public void rotate(float ankle) {
		rotationAnkle += ankle;
	}

	public void setRotation(float ankle) {
		this.rotationAnkle = ankle;
	}

	@Override
	public Coordinate getStartCoordinate() {
		return startCoordinate;
	}

	@Override
	public Coordinate getEndCoordinate() {
		return endCoordinate.addVector(translationVector);
	}

	@Override
	public List<Vector> getVectors() {

		List<Vector> vectors = new ArrayList<>();
		Coordinate currentPosition = null;

		for (PositionedPrintable positionedPrintable : connectedPrintables) {

			Printable printable = positionedPrintable.printable;
			Vector translation = positionedPrintable.translation;
			float rotation = positionedPrintable.rotation;
			float scaling = positionedPrintable.scaling;

			// TODO Check if copy of vectors necessary
			if (currentPosition == null) {
				vectors.add(translation.copy());
			} else {
				Vector differenceVector = currentPosition.getVectorTo(printable.getStartCoordinate());
				vectors.add(differenceVector.plus(translation));
			}

			vectors.addAll(rotateVectors(rotation, scaling, printable.getVectors()));
			currentPosition = printable.getEndCoordinate();
		}

		return vectors;
	}

	private List<Vector> rotateVectors(float ankle, float scaling, List<Vector> vectors) {

		float sin = (float) Math.sin(Math.toRadians(ankle)) * scaling;
		float cos = (float) Math.cos(Math.toRadians(ankle)) * scaling;

		for (Vector vector : vectors) {
			vector.mult(cos, -sin, sin, cos);
		}

		return vectors;
	}

	private class PositionedPrintable {

		private final Printable printable;
		private final Vector translation;
		private final float rotation;
		private final float scaling;

		public PositionedPrintable(Printable printable, Vector translation, float rotation, float scaling) {

			this.printable = printable;
			this.translation = translation.copy();
			this.rotation = rotation;
			this.scaling = scaling;
		}
	}
}
