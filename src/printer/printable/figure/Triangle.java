package printer.printable.figure;

import printer.printable.Coordinate;
import printer.printable.PrintSequence;

public class Triangle extends PrintSequence {
	public Triangle(Coordinate leftBottom, Coordinate top, Coordinate rightBottom) {

		add(leftBottom, top).to(rightBottom).to(leftBottom);
	}

}
