package printer.printable;

public abstract class Printable {
	public final Coordinate start;
	public final Coordinate end;

	public Printable(Coordinate start, Coordinate end) {
		this.start = start;
		this.end = end;
	}
}
