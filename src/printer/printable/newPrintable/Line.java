package printer.printable.newPrintable;

import java.util.ArrayList;
import java.util.List;

import printer.printable.Coordinate;

public class Line extends Printable {

	private final Coordinate start, end;

	public Line(Coordinate start, Coordinate end) {
		this.start = start;
		this.end = end;
	}

	@Override
	public Coordinate getStartCoordinate() {
		return start;
	}

	@Override
	public Coordinate getEndCoordinate() {
		return end;
	}

	@Override
	public List<Vector> getVectors() {
		List<Vector> vectors = new ArrayList<>();
		vectors.add(start.getVectorTo(end));
		return vectors;
	}

}
