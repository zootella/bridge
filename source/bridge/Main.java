package bridge;

import javax.swing.SwingUtilities;

public class Main {

	// When the program runs, Java calls this main() method
    public static void main(String[] args) {

    	// Have the normal Swing thread call this run() method
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {

            	// Make the Program object which represents the whole program, and save it
            	program = new Program();
            }
        });
    }

    /** The Program object that represents the whole program. */
    public static Program program;
}
