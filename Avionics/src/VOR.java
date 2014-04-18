import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.*;
import java.awt.*;

/*
 * Test Driver
 * 
 */
public class VOR {
	public static void main(String args[]) {

		System.out.println("Hello World");
		System.out.println("Welcome Aboard.");
		System.out.println("Thank you for flying Juneau Airlines! :)");
		System.out.println("----------------------------------------");
		// remoted test line
		Compass myCompass = new Compass();

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.add(myCompass);
		panel.setVisible(true);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
		// Rotate the compass by 10 degrees
		myCompass.rotateOBS(10);
		panel.add(myCompass);
		frame.add(panel);

		// create simulated radio
		SimulatedRadio radio = new SimulatedRadio(45, true);
		VOR vor = new VOR(100, radio);
		// we would have an eventListner to change the OBS
		vor.rotateOBS(-10);
		myCompass.rotateOBS(vor.getOBS());
		
		panel.add(myCompass);
		frame.add(panel);
		System.out.println("Any number between " + vor.getOBS()+ " and " + (vor.getOBS() + 180)%360 +" should point left");
		System.out.println(vor.getRadial());
		System.out.println(vor.needleDirection());
		System.out.println(vor.mod(-5,360));
		
	}

	int mod(int a, int b){
		int ret = a % b;
		if(ret < 0){
			ret+=b;
		}
		return ret;
	}
	
	
	
	
	
	// VOR ELEMENTS
	/*
	 * Sets the direction of where the VOR is pointing to Gets degrees from the
	 * OBS
	 */
	int obs;

	/*
	 * Where the object is relative to VOR
	 */
	String direction = "TO";
	// radio
	SimulatedRadio radio;
	int radial = 0;
	//needle direction
	double needle = 0;
	/**
	 * Constructor
	 */
	//The max number the plane can defer from the obs line until it locks 
	//In other words, the needle stops rotating after the plane is more than 10 of the obs line
	private final int NEEDLE_DISTANCE = 10;
	private final double NEEDLE_RPD = 4.5;
	/**
	 * Formula to find how much the needle should turn according to the angle 
	 * rotation per degree = 45 / 10
	 * if obs - radial > 10
	 * needle = obs - radial
	 */
	public VOR() {
	}

	public VOR(int OBSsetting, SimulatedRadio radio) {
		//Store all the information in the VOR
		this.radio = radio;
		obs = OBSsetting;
		radial = radio.getRadial();
		direction = direction(obs, radial);
		//automatically sets the needle variable
		needleDirection();

	}

	// accessor
	public int getOBS() {
		return obs;
	}

	public String getDirection() {
		return direction;
	}

	public SimulatedRadio getRadio() {
		return radio;
	}

	public int getRadial() {
		return radio.getRadial();
	}

	/**
	 * Get the radio signal/dial from the plane input is stored in VOR
	 * 
	 * @param degree
	 */
	public void setOBS(int degree) {
		obs = degree;
	}
	public void setRadioRadial(int value){
		radio.setRadial(value);
	}
	//for testing purposes, set obs And radial
	public void setOR(int degree, int value){
		setOBS(degree);
		setRadioRadial(value);
	}

	// Mutator methods
	// Change the radial from the radio, then update the radial for this class
	public void updateRadial(int degrees) {
		radio.updateRadial(degrees);
		radial = radio.getRadial();
	}

	/**
	 * Checks whether the airplane radio is 'going to' or 'coming from' the VOR
	 * returns 'true' if it is going to the VOR, 'false' if it is coming from
	 * the VOR
	 */

	// Edit direction method
	public String direction(int obs, int radial) {
		direction = "TO";
		int obsLeft = mod(obs-90, 360);
		int obsRight = mod(obs+90, 360);
		
		if(radial == obsLeft || radial == obsRight){
			direction = "OFF";
		}else if(obs < 90 || obs >= 270){
			if(radial >=0 && radial < obsRight){
				direction = "FROM";
			}else if(radial > obsLeft){
				direction = "FROM";
			}
		}else if(radial > obsLeft && radial < obsRight){
			direction = "FROM";
		}
		return direction;
	}

	// If the obs changes, update it . There is a different method in Compass
	// class, take note
	public void rotateOBS(int degrees) {
		obs = obs + degrees;
	}

	/**
	 * Needle logic If plane is on left side of OBS line, point needle right
	 * example: obs points at 0 degrees if radio is anywhere between 359 down to
	 * 181, point right
	 * 
	 * Things needed: obs, radial
	 */

	/**
	 * See if I can reuse the direction code for the needle logic
	 * Sets the needle to the direction it should be pointing at based on where the plane is to VOR
	 
	 * According to the simulator, if the plane is 10 degrees from the obs line 
	 * the needle will reach max rotation at 45%
	 */
	public String needleDirection() {
		int opposite = (obs + 180) % 360;
		String point = "Left";
		if (radial == obs || radial == opposite) {
			// point needle to middle
			point = "Middle";
			needle = 0;
		}else
		if (obs < 180) {
			// if radial is between 0-obs or after opposite
			// In other words, if radial is on left side of obs
			if (radial < obs || radial > opposite) {
				// point to the right
				point = "Right";
				//if radial is greater than obs -10 degrees
				if(obs < 10 && radial > 0){
					needle = (obs - radial) * NEEDLE_RPD;
				}
				
			}
		} else {//if obs > 180
			if(radial<obs || radial > opposite){
				point = "Right";
			}
		}
		return point;
	}
}
