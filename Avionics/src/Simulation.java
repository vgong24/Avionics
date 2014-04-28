import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.*;
import java.awt.*;

public class Simulation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SimulatedRadio radio = new SimulatedRadio(5, true);
		VOR vor = new VOR(0, radio);
		Compass compass = new Compass(vor);
		JFrame f = new JFrame();
		f.getContentPane().add(compass);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setVisible(true);
		System.out.println("If radial is between " + (vor.getOBS() + 180) % 360
				+ " and " + vor.getOBS() + " should point right");
		System.out.println("Radial: " + vor.getRadial());
		System.out.println("Needle should point " + vor.needleDirection());
		System.out.println(vor.mod(-5, 360));
	}
	
}
