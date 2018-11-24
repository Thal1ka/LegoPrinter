package printer.printable.newPrintable;

import printer.printable.Unit;

public class Vector {

	private float dx, dy;

	public Vector(float dx, float dy, Unit unit) {

		this.dx = Unit.xToDegree(dx, unit);
		this.dy = Unit.yToDegree(dy, unit);
	}

	public float getDx() {
		return dx;
	}

	public float getDy() {
		return dy;
	}
}
