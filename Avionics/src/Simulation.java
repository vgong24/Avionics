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
      double random = Math.random();
      random*=26;
      random += 97;
      char char1 = (char) random;
      String string1 = "";
      string1 += char1;
      random = Math.random();
      random*=26;
      random += 97;
      char char2 = (char) random;
      String string2 = "";
      string2 += char2;
      random = Math.random();
      random*=26;
      random += 97;
      char char3 = (char) random;
      String string3 = "";
      string3 += char3;
		String morseCode = radio.setStationID(string1, string2, string3);
		System.out.println("VOR Station ID: " + morseCode);
		
		
		Compass compass = new Compass(vor);
		JFrame f = new JFrame();
		f.getContentPane().add(compass);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setVisible(true);
	}
	
}
