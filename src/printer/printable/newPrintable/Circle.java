package printer.printable.newPrintable;

import java.util.ArrayList;
import java.util.List;

import printer.printable.Coordinate;
import printer.printable.Unit;
import printer.printable.newPrintable.Function.FunctionValueProvider;

public class Circle extends Printable {

	private final float radius;
	private final Coordinate startCoordinate, endCoordinate;
	private final List<Intervall<Float>> circleIntervalls;
	private final List<Function> circleProviders;

	public Circle(Coordinate middle, float radius, Unit unit, float startPercent, float endPercent) {

		this.radius = radius = Unit.xToDegree(radius, unit);

		circleIntervalls = calculateCircleIntervalls(startPercent, endPercent);
		circleProviders = getCircleProviders(radius, circleIntervalls, unit);

		startCoordinate = circleProviders.get(0).getStartCoordinate();
		endCoordinate = circleProviders.get(circleProviders.size() - 1).getEndCoordinate();
	}

	private List<Intervall<Float>> calculateCircleIntervalls(float startPercent, float endPercent) {

		List<Intervall<Float>> circleIntervalls = new ArrayList<>();

		float trueStart = Math.min(startPercent, endPercent);
		float trueEnd = Math.max(startPercent, endPercent);

		float current = trueStart;
		final int circleDivider = 180;

		while (current < trueEnd) {

			int factor = (int) (trueStart / circleDivider);

			if (factor >= 0) {
				factor++;
			}

			float end = Math.min(factor * circleDivider, trueEnd);

			circleIntervalls.add(new Intervall<>(current, end));
			current = end;
		}

		if (startPercent > endPercent) {
			// Swap
			List<Intervall<Float>> mirroredIntervals = new ArrayList<>(circleIntervalls.size());

			for (Intervall<Float> intervall : circleIntervalls) {
				mirroredIntervals.add(0, intervall.mirror());
			}
			circleIntervalls = mirroredIntervals;
		}

		return circleIntervalls;
	}

	private List<Function> getCircleProviders(float radius, List<Intervall<Float>> circleIntervalls, Unit unit) {

		List<Function> circleProviders = new ArrayList<>();

		final float r = radius;

		final FunctionValueProvider topProvider = new FunctionValueProvider() {

			@Override
			public float getFunctionValue(float x) {
				return (float) Math.sqrt(r * r - x * x);
			}
		};

		final FunctionValueProvider bottomProvider = new FunctionValueProvider() {

			@Override
			public float getFunctionValue(float x) {
				return -1 * topProvider.getFunctionValue(x);
			}
		};

		boolean isTop = ((circleIntervalls.get(0).getStart() / 180) % 2 == 0);

		for (Intervall<Float> intervall : circleIntervalls) {

			FunctionValueProvider valueProvider = isTop ? topProvider : bottomProvider;
			circleProviders.add(new Function(valueProvider, getXPositionForPercent(intervall.getStart()), intervall.getEnd(), unit));
			isTop = !isTop;
		}

		return circleProviders;
	}

	@Override
	public Coordinate getStartCoordinate() {
		return startCoordinate;
	}

	@Override
	public Coordinate getEndCoordinate() {
		return endCoordinate;
	}

	@Override
	public List<Vector> getVectors() {

		List<Vector> vectors = new ArrayList<>();

		for (Function provider : circleProviders) {
			vectors.addAll(provider.getVectors());
		}

		return vectors;
	}

	private float getXPositionForPercent(float percent) {
		return (float) (radius - Math.cos(Math.toRadians(percent)) * radius);
	}
}
