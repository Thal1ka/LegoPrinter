package printer.printable;

import printer.printable.newPrintable.Vector;

public class Coordinate {

	public static final Coordinate NULL_POINT = new Coordinate(0, 0, Unit.DEGREE);

	public float x, y;

	public Coordinate(float x, float y, Unit unit, Coordinate addCoordinate) {

		this.x = Unit.xToDegree(x, unit) + addCoordinate.x;
		this.y = Unit.yToDegree(y, unit) + addCoordinate.y;
	}

	public Coordinate(float x, float y, Unit unit) {

		this.x = Unit.xToDegree(x, unit);
		this.y = Unit.yToDegree(y, unit);
	}

	public Coordinate(float x, float y) {
		this(x, y, Unit.DEGREE);
	}

	@Override
	public boolean equals(Object obj) {

		if (obj instanceof Coordinate) return equals((Coordinate) obj);

		return super.equals(obj);
	}

	public boolean equals(Coordinate compare) {
		if (compare == null) return false;
		return x == compare.x && y == compare.y;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public Vector getVectorTo(Coordinate target) {

		float dx = target.x - this.x;
		float dy = target.y - this.y;

		return new Vector(dx, dy, Unit.DEGREE);
	}

	public Coordinate addVector(Vector vector) {

		float x = this.x + vector.getDx();
		float y = this.y + vector.getDy();

		return new Coordinate(x, y);
	}
}
