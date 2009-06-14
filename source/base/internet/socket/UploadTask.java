package base.internet.socket;


import base.data.Bin;
import base.size.Move;
import base.size.Range;
import base.state.Task;
import base.state.TaskBody;
import base.state.TaskClose;
import base.state.Update;

public class UploadTask extends TaskClose {
	
	// Make

	/** Upload 1 or more bytes from bin to socket, don't look at bin until this is closed. */
	public UploadTask(Update update, Socket socket, Range range, Bin bin) {
		this.update = update; // We'll tell above when we're done
		
		// Save the input
		this.socket = socket;
		this.range = range;
		this.bin = bin;

		task = new Task(new MyTask()); // Make a separate thread call thread() below now
	}

	/** The socket we upload to. */
	private final Socket socket;
	/** Limit how much we upload. */
	private final Range range;
	/** The Bin we take the data from. */
	private final Bin bin;

	// Result
	
	/** How much data we uploaded and how long it took, or throws the exception that made us give up. */
	public Move result() throws Exception { return (Move)check(move); }
	private Move move;
	
	// Task

	/** Our Task with a thread that runs our code that blocks. */
	private class MyTask implements TaskBody {
		private Move taskMove; // References thread() can safely set

		// A separate thread will call this method
		public void thread() throws Exception {
				
			// Upload 1 or more bytes from bin to socket
			taskMove = bin.upload(socket, range);
		}

		// Once thread() above returns, the normal event thread calls this done() method
		public void done(Exception e) {
			if (closed()) return; // Don't let anything change if we're already closed
			exception = e;        // Get the exception our code above threw
			if (e == null) {      // No exception, save what thread() did
				
				move = taskMove;
			}
			close();       // We're done
			update.send(); // Tell update we've changed
		}
	}
}
