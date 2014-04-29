import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.*;
import java.awt.*;

/*
 * Test Driver
 * 
 */
public class VOR {
	
	/**************************************************************************** Savior of time code
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	//loops around 360 instead of getting negative numbers
	public static double mod(double a, double b){
		double ret = a % b;
		if(ret < 0){
			ret+=b;
		}
		return ret;
	}
	private double mod(double degree){
		return mod(degree, 360);
	}
	

	/**************** ********************************************************* VOR ELEMENTS
	/*
	 * Sets the direction of where the VOR is pointing to Gets degrees from the
	 * OBS
	 */
	double obs;
	boolean signal;
	final private boolean GOOD = true;
	final private boolean BAD = false;
	/*
	 * Where the object is relative to VOR
	 */
	String direction = "TO";
	// radio
	SimulatedRadio radio;
	double radial = 0;
	//needle direction in degrees
	double needle = 0;
	//The max number the plane can defer from the obs line until it locks 
	//In other words, the needle stops rotating after the plane is more than 10 of the obs line
	private final int NEEDLE_MAX_DISTANCE = 10;
	private final double NEEDLE_RPD = 4.5;
	
	/**
	 * Formula to find how much the needle should turn according to the angle 
	 * rotation per degree = 45 / 10
	 * if obs - radial > 10
	 * needle = obs - radial (basically)
	 */
	/*************************************************************************  Constructor
	 */
	
	public VOR() {
		obs = 0;
		radial = 0;
		direction = direction(obs, radial);
		needleDirection();
	}

	public VOR(int OBSsetting, SimulatedRadio radio) {
		//Store all the information in the VOR
		this.radio = radio;
		obs = OBSsetting;
		radial = radio.getRadial();
		direction = direction();
		//automatically sets the needle variable also the signal
		needleDirection();

	}

	/************************************************************************** Axssors
	 * 
	 * @return
	 */
	public double getOBS() {
		return obs;
	}

	public String getDirection() {
		return direction;
	}

	public SimulatedRadio getRadio() {
		return radio;
	}

	public double getRadial() {
		return radio.getRadial();
	}
	public double getNeedle(){
		return needle;
	}
	public String getSignal(){
		return (signal) ? "GOOD" : "BAD";
	}
	
	/************************************************************************* Mutators
	 * 
	 */
	/**
	 * Get the radio signal/dial from the plane, input is stored in VOR
	 * 
	 * @param degree
	 */
	public void setOBS(double degree) {
		obs = mod(degree);
	}
	public void setRadioRadial(double value){
		radio.setRadial(value);
		radial = value;
	}
	//for testing purposes, set obs And radial
	protected void setOR(double degree, double rad){
		setOBS(degree);
		setRadioRadial(rad);
		direction(degree, rad);
		needleDirection();
	}

	// Change the radial from the radio, then update the radial for this class
	public void setRadial(double degrees) {
		radio.updateRadial(degrees);
		radial = radio.getRadial();
	}
	// If the obs changes, update it . There is a different method in Compass
	// class, take note
	public void rotateOBS(double degrees) {
		obs = mod(obs + degrees);
	}
	public void rotateRadial(double degrees){
		radial = mod(radial + degrees);
		radio.updateRadial(radial);
	}

	/**************************************************************************** Back End Logic Stuff
	 * 
	 */
	/**
	 * Checks whether the airplane radio is 'going to' or 'coming from' the VOR
	 * returns 'true' if it is going to the VOR, 'false' if it is coming from
	 * the VOR
	 */

	// Edit direction method
	public String direction(double obs, double radial) {
		direction = "TO";
		double obsLeft = mod(obs-90, 360);
		double obsRight = mod(obs+90, 360);	
		if(radial == obsLeft || radial == obsRight){
			direction = "OFF";
			signal = BAD;
			return direction;
		}else if(obs < 90 || obs >= 270){
			if(radial >=0 && radial < obsRight){
				direction = "FROM";
			}else if(radial > obsLeft){
				direction = "FROM";
			}
		}else if(radial > obsLeft && radial < obsRight){
			direction = "FROM";
		}
		signal = GOOD;
		return direction;
	}
	public String direction(){
		return direction(obs, radial);
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
		double opposite = mod(obs + 180);
		double obs_left_diff = radial - obs;
		double obs_right_diff = mod(obs_left_diff, 360);
		double opp_left_diff = (opposite - radial);
		double opp_right_diff = mod(opp_left_diff, 360);
		String point = "Left";
		if (radial == obs || radial == opposite) {
			// point needle to middle
			point = "Middle";
			needle = 0;
			//Adding || (obs_left_diff - 360) >=10
		}else if(obs <= 10 && radial >=350 && mod(obs - radial,360) <= 10){ //Base case for numbers around 350 - 10
			needle = mod(obs-radial * NEEDLE_RPD);
			point = "Right";
		}
		else if(obs_left_diff >= -10 && obs_left_diff <= 0){
			needle = mod(obs_left_diff * NEEDLE_RPD * (-1));
			point = "Right";
		}else if(obs_right_diff <= 10 && obs_right_diff >= 0){
			needle = mod(obs_right_diff * NEEDLE_RPD * (-1));
			point = "Left"; 

		}else if(opp_left_diff >= -10 && opp_left_diff <= 0){//flip since we're doing the opposite side
			needle = mod(opp_left_diff * NEEDLE_RPD * (-1));
			point = "Right";
		}else if(opp_right_diff <= 10 && opp_right_diff >= 0){
			needle = mod(opp_right_diff * NEEDLE_RPD * (-1));
			point = "Left";
		}else if(obs < 180){
			if (radial < obs || radial > opposite) {
				// point to the right
				point = "Right";
				//if radial is greater than obs -10 degrees
				needle = mod(NEEDLE_MAX_DISTANCE * NEEDLE_RPD);
			}else{
				point = "Left";
				needle = mod(NEEDLE_MAX_DISTANCE * NEEDLE_RPD * (-1));
			}
			
		}else{
			//if radial is on left side of obs
			if(radial > opposite && radial < obs){
				point = "Right";
				needle = mod(NEEDLE_MAX_DISTANCE * NEEDLE_RPD);
			}else{
				point = "Left";
				needle = mod(NEEDLE_MAX_DISTANCE * NEEDLE_RPD * (-1));
			}
		}
		return point;
	}
}
