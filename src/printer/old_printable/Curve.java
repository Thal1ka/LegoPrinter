package printer.old_printable;

import java.util.ArrayList;
import java.util.List;

import printer.print.Unit;

public class Curve extends Printable {

	private final float pointsPerDegree = 0.5F;
	private final int sequencePoints;

	private final float startPercent, endPercent;

	private final float radius;

	private Coordinate middle;

	public Curve(Coordinate middle, float radius, Unit unit, float startPercent, float endPercent) {
		super(new Coordinate(-radius, 0, unit, middle), new Coordinate(radius, 0, unit, middle));

		this.middle = middle;

		this.radius = Unit.xToDegree(radius, unit);

		double sP = this.radius * pointsPerDegree + 0.5;
		if (this.radius < 50) {
			sP *= 4;
		}
		this.sequencePoints = (int) (sP + 0.5);

		this.startPercent = startPercent;
		this.endPercent = endPercent;
	}

	public Curve(Coordinate middle, float radius, Unit unit) {
		this(middle, radius, unit, 0, 180);

	}

	public List<Line> toSequence() {
		List<Line> lines = new ArrayList<>();

		final float xPart = 2 * radius / sequencePoints;

		float startPercent = this.startPercent;

		// Erster negativer Teil
		if (startPercent < 0) {
			float endPercent = Math.min(this.endPercent, 0);
			getNegativeSequence(lines, startPercent, endPercent, xPart);
			startPercent = endPercent;
			if (startPercent == this.endPercent)
				return lines;
		}

		// Positiver Teil
		float endPercent = Math.min(this.endPercent, 180);
		getPositiveSequence(lines, startPercent, endPercent, xPart);
		startPercent = endPercent;
		if (startPercent == this.endPercent)
			return lines;

		// Zweiter negativer Teil
		getNegativeSequence(lines, startPercent, this.endPercent, xPart);

		return lines;
	}

	private void getNegativeSequence(List<Line> lines, float startPercent, float endPercent, float xPart) {

		Coordinate last, current;

		int startPoint = Math.round(getXPositionForPercent(startPercent) / xPart);
		int endPoint = Math.round(getXPositionForPercent(endPercent) / xPart);
		float startXPoint = startPoint * xPart - radius;
		last = new Coordinate(startXPoint, -getYForX(startXPoint), Unit.DEGREE, middle);

		for (int i = startPoint - 1; i >= endPoint; i--) {
			float x = xPart * i - radius;
			float y = -getYForX(x);

			current = new Coordinate(x, y, Unit.DEGREE, middle);
			lines.add(new Line(last, current));
			last = current;
		}
	}

	private void getPositiveSequence(List<Line> lines, float startPercent, float endPercent, float xPart) {

		Coordinate last, current;

		int startPoint = Math.round(getXPositionForPercent(startPercent) / xPart);
		int endPoint = Math.round(getXPositionForPercent(endPercent) / xPart);
		float startXPoint = startPoint * xPart - radius;
		last = new Coordinate(startXPoint, getYForX(startXPoint), Unit.DEGREE, middle);

		for (int i = startPoint + 1; i <= endPoint; i++) {
			float x = xPart * i - radius;
			float y = getYForX(x);

			current = new Coordinate(x, y, Unit.DEGREE, middle);
			lines.add(new Line(last, current));
			last = current;
		}
	}

	private float getYForX(float x) {
		return (float) Math.sqrt(radius * radius - x * x);
	}

	private float getXPositionForPercent(float percent) {
		return (float) (radius - Math.cos(Math.toRadians(percent)) * radius);
	}
}
