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
	public static int mod(int a, int b){
		int ret = a % b;
		if(ret < 0){
			ret+=b;
		}
		return ret;
	}
	

	/************************************************************************* VOR ELEMENTS
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

	/************************************************************************** Axssors
	 * 
	 * @return
	 */
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
	public double getNeedle(){
		return needle;
	}
	
	/************************************************************************* Mutators
	 * 
	 */
	/**
	 * Get the radio signal/dial from the plane, input is stored in VOR
	 * 
	 * @param degree
	 */
	public void setOBS(int degree) {
		obs = degree;
	}
	public void setRadioRadial(int value){
		radio.setRadial(value);
		radial = value;
	}
	//for testing purposes, set obs And radial
	protected void setOR(int degree, int rad){
		setOBS(degree);
		setRadioRadial(rad);
		needleDirection();
	}

	// Change the radial from the radio, then update the radial for this class
	public void updateRadial(int degrees) {
		radio.updateRadial(degrees);
		radial = radio.getRadial();
	}
	// If the obs changes, update it . There is a different method in Compass
	// class, take note
	public void rotateOBS(int degrees) {
		obs = obs + degrees;
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
		int opposite = mod(obs + 180, 360);
		int obs_left_diff = radial - obs;
		int obs_right_diff = mod(obs_left_diff, 360);
		int opp_left_diff = (opposite - radial);
		int opp_right_diff = mod(opp_left_diff, 360);
		String point = "Left";
		if (radial == obs || radial == opposite) {
			// point needle to middle
			point = "Middle";
			needle = 0;
			//Adding || (obs_left_diff - 360) >=10
		}else if(obs <= 10 && radial >=350 && mod(obs - radial,360) <= 10){ //Base case for numbers around 350 - 10
			needle = mod(obs-radial,360) * NEEDLE_RPD;
			point = "Right";
		}
		else if(obs_left_diff >= -10 && obs_left_diff <= 0){
			needle = obs_left_diff * NEEDLE_RPD * (-1);
			point = "Right";
		}else if(obs_right_diff <= 10 && obs_right_diff >= 0){
			needle = obs_right_diff * NEEDLE_RPD * (-1);
			point = "Left"; 

		}else if(opp_left_diff >= -10 && opp_left_diff <= 0){//flip since we're doing the opposite side
			needle = opp_left_diff * NEEDLE_RPD * (-1);
			point = "Right";
		}else if(opp_right_diff <= 10 && opp_right_diff >= 0){
			needle = opp_right_diff * NEEDLE_RPD * (-1);
			point = "Left";
		}else if(obs < 180){
			if (radial < obs || radial > opposite) {
				// point to the right
				point = "Right";
				//if radial is greater than obs -10 degrees
				needle = NEEDLE_MAX_DISTANCE * NEEDLE_RPD;
			}else{
				point = "Left";
				needle = NEEDLE_MAX_DISTANCE * NEEDLE_RPD * (-1);
			}
			
		}else{
			//if radial is on left side of obs
			if(radial > opposite && radial < obs){
				point = "Right";
				needle = NEEDLE_MAX_DISTANCE * NEEDLE_RPD;
			}else{
				point = "Left";
				needle = NEEDLE_MAX_DISTANCE * NEEDLE_RPD * (-1);
			}
		}
		return point;
	}
}
