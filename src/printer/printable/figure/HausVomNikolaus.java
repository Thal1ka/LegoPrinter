package printer.printable.figure;

import printer.printable.Coordinate;
import printer.printable.PrintSequence;

public class HausVomNikolaus extends PrintSequence {

	public HausVomNikolaus(Coordinate bottomLeft, Coordinate topRight, Coordinate peak) {

		add(bottomLeft, new Coordinate(bottomLeft.x, topRight.y));
		to(topRight).to(bottomLeft).to(new Coordinate(topRight.x, bottomLeft.y));
		to(new Coordinate(bottomLeft.x, topRight.y)).to(peak).to(topRight);
		to(new Coordinate(topRight.x, bottomLeft.y));
	}

}
