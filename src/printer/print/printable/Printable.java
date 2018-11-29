package printer.print.printable;

import java.util.List;

import printer.old_printable.Coordinate;
import printer.print.Vector;

public abstract class Printable {

	public abstract Coordinate getStartCoordinate();

	public abstract Coordinate getEndCoordinate();

	public abstract List<Vector> getVectors();

	public Coordinate getPositioningCoordinate() {
		return getStartCoordinate();
	}
}
