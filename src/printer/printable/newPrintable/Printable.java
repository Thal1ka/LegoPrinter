package printer.printable.newPrintable;

import java.util.List;

import printer.printable.Coordinate;

public abstract class Printable {

	public abstract Coordinate getStartCoordinate();

	public abstract Coordinate getEndCoordinate();

	public abstract List<Vector> getVectors();
}
