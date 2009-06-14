package bridge;

import base.exception.Mistake;
import base.state.Close;

public class Program extends Close {

	public Program() {
		window = new Window(this);
	}

	public final Window window;

	@Override public void close() {
		if (already()) return;

		close(window);

		// Make sure every object with a close() method ran
		try { Close.checkAll(); } catch (Exception e) { Mistake.grab(e); }
	}
}
