package printer.printable.figure;

import printer.printable.AdvancedPrintSequence;
import printer.printable.Coordinate;
import printer.printable.Curve;
import printer.printable.Unit;

public class Circle extends AdvancedPrintSequence {

	public Circle(Coordinate middle, float radius, Unit unit) {
		add(new Curve(middle, radius, unit, 0, 360));
	}
}
