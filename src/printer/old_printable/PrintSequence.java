package printer.old_printable;

import java.util.ArrayList;
import java.util.List;

import printer.print.Unit;

public class PrintSequence {

	private Line current, last;
	private Coordinate lastAddedCoordinate;
	private int index = 0;

	private List<Line> sequence = new ArrayList<>();

	public PrintSequence add(Line line) {
		sequence.add(line);
		lastAddedCoordinate = line.end;
		return this;
	}

	public PrintSequence add(Coordinate start, Coordinate end) {
		return add(new Line(start, end));
	}

	public PrintSequence add(PrintSequence othersequence) {
		sequence.addAll(othersequence.sequence);
		lastAddedCoordinate = othersequence.lastAddedCoordinate;
		return this;
	}

	public PrintSequence to(Coordinate target) {

		if (lastAddedCoordinate != null) {
			add(lastAddedCoordinate, target);
		} else {
			add(Coordinate.NULL_POINT, target);
		}
		return this;
	}

	public PrintSequence moveDirection(float dx, float dy, Unit unit) {

		if (lastAddedCoordinate != null) {
			add(lastAddedCoordinate, new Coordinate(dx, dy, unit, lastAddedCoordinate));
		} else {
			add(Coordinate.NULL_POINT, new Coordinate(dx, dy, unit, Coordinate.NULL_POINT));
		}
		return this;
	}

	public Line next() {
		last = current;
		current = sequence.get(index);
		index++;
		return current;
	}

	public boolean hasNext() {
		return index < sequence.size();
	}

	public boolean hasToLift() {
		if (last == null || current == null)
			return true;
		return !last.end.equals(current.start);
	}

	public PrintSequence scale(float ratio) {

		float minX = Integer.MAX_VALUE;
		float minY = Integer.MAX_VALUE;

		List<Line> lines = new ArrayList<>();

		for (Line line : sequence) {

			Coordinate lineStart = line.start;
			Coordinate start = new Coordinate((int) (lineStart.x * ratio + 0.5), (int) (lineStart.y * ratio + 0.5),
					Unit.DEGREE);

			minX = Math.min(minX, lineStart.x);
			minY = Math.min(minY, lineStart.y);

			Coordinate lineEnd = line.end;
			Coordinate end = new Coordinate((int) (lineEnd.x * ratio + 0.5), (int) (lineEnd.y * ratio + 0.5),
					Unit.DEGREE);

			minX = Math.min(minX, lineEnd.x);
			minY = Math.min(minY, lineEnd.y);

			lines.add(new Line(start, end));
		}

		sequence = lines;

		return move(-minX, -minY, Unit.DEGREE);
	}

	public PrintSequence move(float dx, float dy, Unit unit) {

		List<Line> lines = new ArrayList<>();

		for (Line line : sequence) {

			Coordinate start = new Coordinate(dx, dy, unit, line.start);
			Coordinate end = new Coordinate(dx, dy, unit, line.end);
			lines.add(new Line(start, end));
		}

		sequence = lines;
		return this;
	}
}
