package printer.printable.newPrintable;

public class DrawVector {

	public final float dx, dy;
	public final boolean isPrinted;

	public DrawVector(Vector vector, boolean isPrinted) {

		this(vector.getDx(), vector.getDy(), isPrinted);
	}

	public DrawVector(float dx, float dy, boolean isPrinted) {

		this.dx = dx;
		this.dy = dy;
		this.isPrinted = isPrinted;
	}
}
