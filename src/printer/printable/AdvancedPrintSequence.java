package printer.printable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AdvancedPrintSequence implements Iterable<Printable> {

	private List<Printable> printables = new ArrayList<>();

	private Coordinate lastAddedCoordinate = Coordinate.NULL_POINT;

	@Override
	public Iterator<Printable> iterator() {
		List<Printable> sequence = new ArrayList<>();

		for (Printable printable : printables) {
			if (printable instanceof Line) {
				sequence.add(printable);
			} else if (printable instanceof Curve) {
				sequence.addAll(((Curve) printable).toSequence());
			}
		}

		return sequence.iterator();
	}

	public AdvancedPrintSequence add(Printable printable) {
		printables.add(printable);
		lastAddedCoordinate = printable.end;
		return this;
	}

	public AdvancedPrintSequence add(AdvancedPrintSequence othersequence) {
		printables.addAll(othersequence.printables);
		lastAddedCoordinate = othersequence.lastAddedCoordinate;
		return this;
	}

	public AdvancedPrintSequence lineTo(Coordinate target) {

		add(new Line(lastAddedCoordinate, target));

		return this;
	}

}
