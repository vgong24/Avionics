import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.*;
import java.awt.*;
/*
 * Test Driver
 * 
 */
public class VOR{
	public static void main(String args[]){
		
		System.out.println("Hello World");
		System.out.println("Welcome Aboard.");
		System.out.println("Thank you for flying Juneau Airlines! :)");
		//remoted test line
		/*Compass myCompass = new Compass();
	    JFrame frame = new JFrame();
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    JPanel panel = new JPanel();
	    panel.add(myCompass);
	    panel.setVisible(true);
	    frame.add(panel);
	    frame.pack();
	    frame.setVisible(true);
	    myCompass.paintComponent(g);
	    */
		System.out.println(isTo(299,22));
	}
	
	//VOR ELEMENTS
	/* Sets the direction of where the VOR is pointing to
	 * Gets degrees from the dial
	 */
	int degrees;
	
	/* Where the object is relative to VOR
	 * If the plane is on the opposite direction of the perpendicular sides of the degrees
	 * Then it is 'to' else 'from'
	 */
	boolean isTo;
	
	//Coordinates of VOR
	int[] coord;
	
	/**
	 * Constructor
	 */
	public VOR(int OBSsetting, SimulatedRadio radio){
		degrees = OBSsetting;
		isTo = radio.getSignalStrength();
		
	}
	/**
	 * Get the radio signal/dial from the plane
	 * input is stored in VOR
	 * @param degree
	 */
	public void radioDegree(int degree){
		degrees = degree;
	}
	/**
	 * To check whether the radio is going to or from the VOR
	 * returns true if it is going to the VOR, false if it is from
	 */
	public static boolean isTo(int obs, int radial){
		if(obs <= 90){
			//if between 0-obs + 90 or after obs-90 to 0
			if((radial >= 0 && radial <= obs+90) || (radial >= ((obs - 90 + 360)%360))){
				return false;
			}
		}// if obs is greater than 270
		else if(obs>=270){
			//if radial is 0 or greater than obs and less than 360 or between 1 and obs +90
			if(((radial == 0 || radial >= obs) && radial <= 360) || (radial <= (obs + 90)%360)){
				return false;
			}
			
		}else{
			if(radial>=obs-90 && radial<= obs+90)
				return false;
		}
		return true;
	}
}
