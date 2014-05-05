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
		
		
		//Given an already-set input, the setStationID prints the morse code of the VOR station
		String morseCode = radio.setStationID("h", "n", "l");
		System.out.println("VOR Station ID: " + morseCode);
		String test = radio.setStationID("abz");
		System.out.println("test: "+ test);
		
		Compass compass = new Compass(vor);
		JFrame f = new JFrame();
		f.getContentPane().add(compass);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setVisible(true);
	}
	
}
