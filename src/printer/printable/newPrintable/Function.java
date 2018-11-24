package printer.printable.newPrintable;

import java.util.ArrayList;
import java.util.List;

import printer.printable.Coordinate;
import printer.printable.Unit;

public class Function extends Printable {

	private final FunctionValueProvider valueProvider;
	private final float startX, endX;
	private final Unit unit;

	public Function(FunctionValueProvider valueProvider, float startX, float endX, Unit unit) {

		this.valueProvider = valueProvider;
		this.startX = startX;
		this.endX = endX;
		this.unit = unit;
	}

	@Override
	public Coordinate getStartCoordinate() {

		float y = valueProvider.getFunctionValue(startX);
		return new Coordinate(startX, y, unit);
	}

	@Override
	public Coordinate getEndCoordinate() {

		float y = valueProvider.getFunctionValue(endX);
		return new Coordinate(endX, y, unit);
	}

	@Override
	public List<Vector> getVectors() {

		List<Vector> vectors = new ArrayList<>();

		float start = Math.round(startX);
		float end = Math.round(endX);

		float lastX, lastY, currentX, currentY;

		lastX = start;
		lastY = valueProvider.getFunctionValue(lastX);

		int iterationStep = (start < end) ? 1 : -1;

		for (currentX = start + iterationStep; currentX != end; currentX += iterationStep) {
			currentY = valueProvider.getFunctionValue(currentX);

			vectors.add(new Vector(currentX - lastX, currentY - lastY, unit));
			lastX = currentX;
			lastY = currentY;
		}

		currentX = end;
		currentY = valueProvider.getFunctionValue(end);
		vectors.add(new Vector(currentX - lastX, currentY - lastY, unit));

		return vectors;
	}

	public interface FunctionValueProvider {

		float getFunctionValue(float x);
	}

}
