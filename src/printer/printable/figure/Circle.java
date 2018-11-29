package printer.printable.figure;

import printer.old_printable.AdvancedPrintSequence;
import printer.old_printable.Coordinate;
import printer.old_printable.Curve;
import printer.print.Unit;

public class Circle extends AdvancedPrintSequence {

	public Circle(Coordinate middle, float radius, Unit unit) {
		add(new Curve(middle, radius, unit, 0, 360));
	}
}
