package printer;

import printer.hardware.Worker;
import printer.printable.AdvancedPrintSequence;
import printer.printable.Coordinate;
import printer.printable.Unit;
import printer.printable.figure.Alphabet;

public class TextPrinter implements Worker {

	private final Coordinate DOCUMENT_START = new Coordinate(0, 80, Unit.PERCENT);
	private final float DOCUMENT_MAX_WIDTH = Unit.xToDegree(100, Unit.PERCENT);
	private final float DOCUMENT_MIN_HEIGHT = Unit.yToDegree(0, Unit.PERCENT);

	private final PrintController printController;

	private float fontSize = Unit.xToDegree(10, Unit.PERCENT);
	private final float widthRatio = 0.6F;
	private float margin = Unit.xToDegree(2, Unit.PERCENT);

	public TextPrinter(PrintController printController) {
		this.printController = printController;
	}

	public void setFontSize(float fontSize, Unit unit) {
		this.fontSize = Unit.xToDegree(fontSize, unit);
	}

	public void setMargin(float margin, Unit unit) {
		this.margin = Unit.xToDegree(margin, unit);
	}

	public void printString(String text, Coordinate start) {
		text = text.toUpperCase();

		final float letterHeight = fontSize;
		final float letterWidth = fontSize * widthRatio;
		Coordinate cursor = new Coordinate(0, -letterHeight, Unit.DEGREE, start);

		AdvancedPrintSequence sequence = new AdvancedPrintSequence();

		for (char c : text.toCharArray()) {

			sequence.add(printChar(c, cursor, letterHeight, letterWidth));
			cursor = new Coordinate(letterWidth + margin, 0, Unit.DEGREE, cursor);

			if (cursor.x + letterWidth > DOCUMENT_MAX_WIDTH || c == '\n') {
				cursor = new Coordinate(0, cursor.y - margin - letterHeight); // From Top to Bottom

				if (cursor.y < DOCUMENT_MIN_HEIGHT) {
					break;
				}
			}
		}

		printController.print(sequence);
	}

	public void printString(String text) {
		printString(text, DOCUMENT_START);
	}

	private AdvancedPrintSequence printChar(char c, Coordinate position, float height, float width) {
		Unit unit = Unit.DEGREE;

		switch (c) {
		case 'A':
			return new Alphabet.A(position, width, height, unit);
		case 'B':
			return new Alphabet.B(position, height, unit);
		case 'C':
			return new Alphabet.C(position, width, unit);
		case 'D':
			return new Alphabet.D(position, height, unit);
		case 'E':
			return new Alphabet.E(position, width, height, unit);
		case 'F':
			return new Alphabet.F(position, width, height, unit);
		case 'G':
			return new Alphabet.G(position, width, unit);
		case 'H':
			return new Alphabet.H(position, width, height, unit);
		case 'I':
			return new Alphabet.I(position, height, unit);
		case 'J':
			return new Alphabet.J(position, width, height, unit);
		case 'K':
			return new Alphabet.K(position, width, height, unit);
		case 'N':
			return new Alphabet.N(position, width, height, unit);
		default:
			return new AdvancedPrintSequence();
		}
	}

	@Override
	public boolean hasToWait() {
		return printController.hasToWait();
	}
}
