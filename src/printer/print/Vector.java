package printer.print;

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

	public Vector plus(Vector vector) {

		this.dx += vector.dx;
		this.dy += vector.dy;
		return this;
	}

	public Vector minus(Vector vector) {

		this.dx -= vector.dx;
		this.dy -= vector.dy;
		return this;
	}

	/**
	 *
	 * Multiplies the vector with the given matrix
	 * <code><br/><br/>aa ab <br/> ba bb</code>
	 */
	public Vector mult(float aa, float ab, float ba, float bb) {

		float multDx = aa * dx + ab * dy;
		float multDy = ba * dx + bb * dy;

		this.dx = multDx;
		this.dy = multDy;

		return this;
	}

	public Vector copy() {
		return new Vector(dx, dy, Unit.DEGREE);
	}

	public static Vector addVectors(Vector vector1, Vector vector2) {

		float dx = vector1.dx + vector2.dx;
		float dy = vector2.dy + vector2.dy;
		return new Vector(dx, dy, Unit.DEGREE);
	}

	public static Vector substractVectors(Vector vector1, Vector vector2) {

		float dx = vector1.dx - vector2.dx;
		float dy = vector2.dy - vector2.dy;
		return new Vector(dx, dy, Unit.DEGREE);
	}

	@Override
	public String toString() {
		return String.format("Vector(%s, %s", dx, dy);
	}
}
