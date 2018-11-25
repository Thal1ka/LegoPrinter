package printer.printable.figure;

import printer.printable.AdvancedPrintSequence;
import printer.printable.Coordinate;
import printer.printable.Curve;
import printer.printable.Line;
import printer.printable.Unit;

public class Alphabet {
	public static class A extends AdvancedPrintSequence {
		public A(Coordinate left, float width, float height, Unit unit) {

			width = Unit.xToDegree(width, unit);
			height = Unit.yToDegree(height, unit);

			Coordinate top = new Coordinate(width / 2, height, Unit.DEGREE, left);
			Coordinate right = new Coordinate(width, 0, Unit.DEGREE, left);
			add(new Line(left, top)).add(new Line(top, right));

			float xAvgLeft = (left.x + top.x) / 2;
			float xAvgRight = (right.x + top.x) / 2;
			float yAvgLeft = (left.y + top.y) / 2;
			float yAvgRight = (right.y + top.y) / 2;

			add(new Line(new Coordinate(xAvgLeft, yAvgLeft), new Coordinate(xAvgRight, yAvgRight)));
		}
	}

	public static class B extends AdvancedPrintSequence {
		public B(Coordinate bottom, float height, Unit unit) {

			height = Unit.yToDegree(height, unit);

			Coordinate top = new Coordinate(0, height, Unit.DEGREE, bottom);
			Coordinate mid = new Coordinate(top.x, bottom.y + height / 2, Unit.DEGREE);

			float radius = height / 4;
			float vertLines = height / 4;

			Coordinate topMiddle = new Coordinate(vertLines, -radius, Unit.DEGREE, top);
			Coordinate bottomMiddle = new Coordinate(vertLines, radius, Unit.DEGREE, bottom);

			Line topLine = new Line(top, new Coordinate(vertLines, 0, Unit.DEGREE, top));
			Line midLine = new Line(new Coordinate(vertLines, 0, Unit.DEGREE, mid), mid);
			Line bottomLine = new Line(new Coordinate(vertLines, 0, Unit.DEGREE, bottom), bottom);

			add(new Line(bottom, top)).add(topLine).add(new Curve(topMiddle, radius, Unit.DEGREE, 90, 270)).add(midLine).add(new Curve(bottomMiddle, radius, Unit.DEGREE, 90, 270)).add(bottomLine);
		}
	}

	public static class C extends AdvancedPrintSequence {
		public C(Coordinate left, float radius, Unit unit) {

			radius = Unit.xToDegree(radius, unit);

			Coordinate mid = new Coordinate(radius, radius, Unit.DEGREE, left);
			add(new Curve(mid, radius, Unit.DEGREE, -90, 90));
		}
	}

	public static class D extends AdvancedPrintSequence {
		public D(Coordinate bottom, float height, Unit unit) {

			height = Unit.yToDegree(height, unit);
			float radius = height / 2F;
			float vertLines = height * 0.1F;

			Coordinate top = new Coordinate(0, height, Unit.DEGREE, bottom);
			Coordinate mid = new Coordinate(vertLines, radius, Unit.DEGREE, bottom);

			Line topLine = new Line(top, new Coordinate(vertLines, 0, Unit.DEGREE, top));
			Line bottomLine = new Line(new Coordinate(vertLines, 0, Unit.DEGREE, bottom), bottom);

			add(new Line(bottom, top)).add(topLine).add(new Curve(mid, radius, Unit.DEGREE, 90, 270)).add(bottomLine);
		}
	}

	public static class E extends AdvancedPrintSequence {
		public E(Coordinate bottom, float width, float height, Unit unit) {

			width = Unit.xToDegree(width, unit);
			height = Unit.yToDegree(height, unit);

			Coordinate top = new Coordinate(0, height, Unit.DEGREE, bottom);
			Coordinate mid = new Coordinate(0, height / 2, Unit.DEGREE, bottom);

			Line topLine = new Line(top, new Coordinate(width, 0, Unit.DEGREE, top));
			Line midLine = new Line(new Coordinate(width * 0.75F, 0, Unit.DEGREE, mid), mid);
			Line bottomLine = new Line(bottom, new Coordinate(width, 0, Unit.DEGREE, bottom));

			add(new Line(bottom, top)).add(topLine).add(midLine).add(bottomLine);

		}
	}

	public static class F extends AdvancedPrintSequence {
		public F(Coordinate bottom, float width, float height, Unit unit) {

			width = Unit.xToDegree(width, unit);
			height = Unit.yToDegree(height, unit);

			Coordinate top = new Coordinate(0, height, Unit.DEGREE, bottom);
			Coordinate mid = new Coordinate(0, height / 2, Unit.DEGREE, bottom);

			Line topLine = new Line(top, new Coordinate(width, 0, Unit.DEGREE, top));
			Line midLine = new Line(mid, new Coordinate(width * 0.75F, 0, Unit.DEGREE, mid));

			add(new Line(bottom, top)).add(topLine).add(midLine);
		}
	}

	public static class G extends AdvancedPrintSequence {
		public G(Coordinate left, float radius, Unit unit) {

			radius = Unit.xToDegree(radius, unit);

			Coordinate mid = new Coordinate(radius, radius, Unit.DEGREE, left);
			Coordinate midLeft = new Coordinate(-radius * 0.5F, 0, Unit.DEGREE, mid);
			Coordinate rightBottom = new Coordinate(radius, 0, Unit.DEGREE, left);

			add(new Curve(mid, radius, Unit.DEGREE, -90, 90)).add(new Line(midLeft, mid)).add(new Line(mid, rightBottom));
		}
	}

	public static class H extends AdvancedPrintSequence {
		public H(Coordinate leftBottom, float width, float height, Unit unit) {

			width = Unit.xToDegree(width, unit);
			height = Unit.yToDegree(height, unit);

			float midHeight = height / 2;

			Coordinate leftTop = new Coordinate(0, height, Unit.DEGREE, leftBottom);
			Coordinate rightBottom = new Coordinate(width, 0, Unit.DEGREE, leftBottom);
			Coordinate rightTop = new Coordinate(width, height, Unit.DEGREE, leftBottom);
			Coordinate leftMid = new Coordinate(0, midHeight, Unit.DEGREE, leftBottom);
			Coordinate rightMid = new Coordinate(0, midHeight, Unit.DEGREE, rightBottom);

			add(new Line(leftBottom, leftTop)).add(new Line(rightTop, rightBottom)).add(new Line(leftMid, rightMid));
		}
	}

	public static class I extends AdvancedPrintSequence {
		public I(Coordinate bottomLeft, float width, float height, Unit unit) {

			width = Unit.xToDegree(width, unit);
			height = Unit.yToDegree(height, unit);

			Coordinate bottom = new Coordinate(width / 2, 0, Unit.DEGREE, bottomLeft);
			Coordinate top = new Coordinate(0, height, Unit.DEGREE, bottom);

			add(new Line(bottom, top));
		}
	}

	public static class J extends AdvancedPrintSequence {
		public J(Coordinate leftBottom, float width, float height, Unit unit) {

			width = Unit.xToDegree(width, unit);
			height = Unit.yToDegree(height, unit);

			float radius = width / 2;

			Coordinate topRight = new Coordinate(width, height, Unit.DEGREE, leftBottom);
			Coordinate curveMid = new Coordinate(radius, radius, Unit.DEGREE, leftBottom);
			Coordinate bottomRight = new Coordinate(width, radius, Unit.DEGREE, leftBottom);

			add(new Curve(curveMid, radius, Unit.DEGREE, -180F, 0F)).add(new Line(bottomRight, topRight));
		}
	}

	public static class K extends AdvancedPrintSequence {
		public K(Coordinate leftBottom, float width, float height, Unit unit) {

			width = Unit.xToDegree(width, unit);
			height = Unit.yToDegree(height, unit);

			float midHeight = height / 2;

			Coordinate leftTop = new Coordinate(0, height, Unit.DEGREE, leftBottom);
			Coordinate rightTop = new Coordinate(width, height, Unit.DEGREE, leftBottom);
			Coordinate mid = new Coordinate(0, midHeight, Unit.DEGREE, leftBottom);
			Coordinate rightBottom = new Coordinate(width, 0, Unit.DEGREE, leftBottom);

			add(new Line(leftBottom, leftTop)).add(new Line(rightTop, mid)).add(new Line(mid, rightBottom));
		}
	}

	public static class L extends AdvancedPrintSequence {
		public L(Coordinate leftBottom, float width, float height, Unit unit) {

			width = Unit.xToDegree(width, unit);
			height = Unit.yToDegree(height, unit);

			Coordinate leftTop = new Coordinate(0, height, Unit.DEGREE, leftBottom);
			Coordinate rightBottom = new Coordinate(width, 0, Unit.DEGREE, leftBottom);

			add(new Line(leftTop, leftBottom)).add(new Line(leftBottom, rightBottom));
		}
	}

	public static class M extends AdvancedPrintSequence {
		public M(Coordinate leftBottom, float width, float height, Unit unit) {

			width = Unit.xToDegree(width, unit);
			height = Unit.yToDegree(height, unit);

			Coordinate leftTop = new Coordinate(0, height, Unit.DEGREE, leftBottom);
			Coordinate midBottom = new Coordinate(width / 2, height * 0.3F, Unit.DEGREE, leftBottom);
			Coordinate rightTop = new Coordinate(width, height, Unit.DEGREE, leftBottom);
			Coordinate rightBottom = new Coordinate(width, 0, Unit.DEGREE, leftBottom);

			add(new Line(leftBottom, leftTop)).lineTo(midBottom).lineTo(rightTop).lineTo(rightBottom);
		}
	}

	public static class N extends AdvancedPrintSequence {
		public N(Coordinate leftBottom, float width, float height, Unit unit) {

			width = Unit.xToDegree(width, unit);
			height = Unit.yToDegree(height, unit);

			Coordinate leftTop = new Coordinate(0, height, Unit.DEGREE, leftBottom);
			Coordinate rightBottom = new Coordinate(width, 0, Unit.DEGREE, leftBottom);
			Coordinate rightTop = new Coordinate(width, height, Unit.DEGREE, leftBottom);

			add(new Line(leftBottom, leftTop)).add(new Line(leftTop, rightBottom)).add(new Line(rightBottom, rightTop));
		}
	}

	public static class O extends AdvancedPrintSequence {
		public O(Coordinate leftBottom, float radius, Unit unit) {

			radius = Unit.xToDegree(radius, unit);
			Coordinate middle = new Coordinate(radius, radius, Unit.DEGREE, leftBottom);

			add(new Curve(middle, radius, unit, 0, 360));
		}
	}

	public static class P extends AdvancedPrintSequence {
		public P(Coordinate leftBottom, float height, Unit unit) {

			height = Unit.yToDegree(height, unit);
			float vertLines = height / 5;
			float radius = height / 4;

			Coordinate leftTop = new Coordinate(0, height, Unit.DEGREE, leftBottom);
			Coordinate circleStart = new Coordinate(vertLines, 0, Unit.DEGREE, leftTop);
			Coordinate circleEnd = new Coordinate(vertLines, height / 2, Unit.DEGREE, leftBottom);
			Coordinate circleMid = new Coordinate(0, radius, Unit.DEGREE, circleEnd);
			Coordinate leftMid = new Coordinate(0, height / 2, Unit.DEGREE, leftBottom);

			add(new Line(leftBottom, leftTop)).lineTo(circleStart).add(new Curve(circleMid, radius, Unit.DEGREE, 90, 270)).add(new Line(circleEnd, leftMid));
		}
	}

	public static class Q extends AdvancedPrintSequence {

		public Q(Coordinate leftBottom, float radius, Unit unit) {

			radius = Unit.xToDegree(radius, unit);
			float halfRadius = radius / 2;
			Coordinate middle = new Coordinate(radius, radius, Unit.DEGREE, leftBottom);
			Coordinate lineStart = new Coordinate(halfRadius, -halfRadius, Unit.DEGREE, middle);
			Coordinate lineEnd = new Coordinate(2 * radius, 0, Unit.DEGREE, leftBottom);

			add(new Curve(middle, radius, unit, 0, 360)).add(new Line(lineStart, lineEnd));
		}
	}

	public static class R extends AdvancedPrintSequence {

		public R(Coordinate leftBottom, float height, Unit unit) {

			height = Unit.yToDegree(height, unit);
			float vertLines = height / 5;
			float radius = height / 4;

			Coordinate leftTop = new Coordinate(0, height, Unit.DEGREE, leftBottom);
			Coordinate circleStart = new Coordinate(vertLines, 0, Unit.DEGREE, leftTop);
			Coordinate circleEnd = new Coordinate(vertLines, height / 2, Unit.DEGREE, leftBottom);
			Coordinate circleMid = new Coordinate(0, radius, Unit.DEGREE, circleEnd);
			Coordinate leftMid = new Coordinate(0, height / 2, Unit.DEGREE, leftBottom);
			Coordinate rightBottom = new Coordinate(radius + vertLines, 0, Unit.DEGREE, leftBottom);

			add(new Line(leftBottom, leftTop)).lineTo(circleStart).add(new Curve(circleMid, radius, Unit.DEGREE, 90, 270)).add(new Line(leftMid, circleEnd)).lineTo(rightBottom);
		}
	}

	public static class S extends AdvancedPrintSequence {

		public S(Coordinate leftBottom, float height, Unit unit) {

			height = Unit.yToDegree(height, unit);
			float radius = height / 4;

			Coordinate topMiddle = new Coordinate(radius, 3 * radius, Unit.DEGREE, leftBottom);
			Coordinate botMiddle = new Coordinate(radius, radius, Unit.DEGREE, leftBottom);

			add(new Curve(topMiddle, radius, Unit.DEGREE, -90, 130)).add(new Curve(botMiddle, radius, unit, 90, 310));
		}
	}

	public static class T extends AdvancedPrintSequence {

		public T(Coordinate leftBottom, float width, float height, Unit unit) {

			width = Unit.xToDegree(width, unit);
			height = Unit.yToDegree(height, unit);

			Coordinate leftTop = new Coordinate(0, height, Unit.DEGREE, leftBottom);
			Coordinate rightTop = new Coordinate(width, 0, Unit.DEGREE, leftTop);
			Coordinate midTop = new Coordinate(width / 2, height, Unit.DEGREE, leftBottom);
			Coordinate midBottom = new Coordinate(width / 2, 0, Unit.DEGREE, leftBottom);

			add(new Line(leftTop, rightTop)).add(new Line(midTop, midBottom));
		}
	}
}
