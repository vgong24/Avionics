import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.*;
import java.awt.*;

public class Simulation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
<<<<<<< HEAD
		SimulatedRadio radio = new SimulatedRadio(0, true);
		VOR vor = new VOR(180, radio);


=======
		SimulatedRadio radio = new SimulatedRadio(359, true);
		VOR vor = new VOR(8, radio);
>>>>>>> b32a355d443fddcbeedb301b64c7f8589e3c6d34
		Compass compass = new Compass(vor);
		JFrame f = new JFrame();
		f.getContentPane().add(compass);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setVisible(true);
	}
	
}
