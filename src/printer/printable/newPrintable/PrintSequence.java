package printer.printable.newPrintable;

import java.util.ArrayList;
import java.util.List;

import printer.printable.Coordinate;

public class PrintSequence {

	private Coordinate startCoordinate;
	List<DrawVector> vectors = new ArrayList<>();

	protected PrintSequence(Coordinate startCoordinate, List<DrawVector> drawVectors, float rotation) {
		this.setStartCoordinate(startCoordinate);

		this.vectors = drawVectors;
		rotate(rotation);
	}

	private void rotate(float ankle) {

		float sin = (float) Math.sin(Math.toRadians(ankle));
		float cos = (float) Math.cos(Math.toRadians(ankle));

		List<DrawVector> rotatedVectors = new ArrayList<>();

		for (DrawVector vector : vectors) {
			// Rotate with the rotation matrix R2
			float rotatedX = vector.dx * cos - vector.dy * sin;
			float rotatedY = vector.dx * sin + vector.dy * cos;

			rotatedVectors.add(new DrawVector(rotatedX, rotatedY, vector.isPrinted)); // TODO Change instead of new Object
		}

		this.vectors = rotatedVectors;
	}

	public Coordinate getStartCoordinate() {
		return startCoordinate;
	}

	public void setStartCoordinate(Coordinate startCoordinate) {
		this.startCoordinate = startCoordinate;
	}
}
