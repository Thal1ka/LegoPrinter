package printer.print.printable;

public class Intervall<T> {

	private final T start, end;

	public Intervall(T start, T end) {
		this.start = start;
		this.end = end;
	}

	public T getStart() {
		return start;
	}

	public T getEnd() {
		return end;
	}

	public Intervall<T> mirror() {
		return new Intervall<>(end, start);
	}
}
