import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.*;
import java.awt.*;

public class Simulation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		SimulatedRadio radio = new SimulatedRadio(358, true);
		VOR vor = new VOR(4, radio);

		Compass compass = new Compass(vor);
		JFrame f = new JFrame();
		f.getContentPane().add(compass);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setVisible(true);
	}
	
}
