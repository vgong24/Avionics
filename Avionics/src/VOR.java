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

		System.out.println("----------------------------------------");

		Compass myCompass = new Compass();
	        JFrame frame = new JFrame();
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        JPanel panel = new JPanel();
	        panel.add(myCompass);
	        panel.setVisible(true);
	        frame.add(panel);
	        frame.pack();
	        frame.setVisible(true);

		int obs = 50;
		int radial = 90;
		SimulatedRadio sr = new SimulatedRadio(radial,45, true);
		VOR vor = new VOR(obs,sr);
		obs = vor.getOBS();
		SimulatedRadio radio = vor.getRadio();
		radial = radio.getRadial();
		System.out.println("VOR OBS pointing at: "+ obs + ", plane located at "+ radial+" degrees");
		System.out.println("Direction: "+vor.direction(obs, radial));
		VOR test = new VOR();
		System.out.println(test.direction(200, 20));
		System.out.print((90-90+360)%360);
	}
	
	//VOR ELEMENTS
	/* Sets the direction of where the VOR is pointing to
	 * Gets degrees from the OBS
	 */
	int obs;
	
	/* Where the object is relative to VOR
	 */
	String direction = "TO";
	//radio
	SimulatedRadio radio;
	/**
	 * Constructor
	 */
	public VOR(){
		
	}
	public VOR(int OBSsetting, SimulatedRadio radio){
		obs = OBSsetting;
		direction = direction(obs,radio.getRadial());
		this.radio = radio;
		
	}
	//accessor
	public int getOBS(){
		return obs;
	}
	
	public String getDirection(){
		return direction;
	}
	public SimulatedRadio getRadio(){
		return radio;
	}
	/**
	 * Get the radio signal/dial from the plane
	 * input is stored in VOR
	 * @param degree
	 */
	public void setOBS(int degree){
		obs = degree;
	}
	
	
	/**
	 * Checks whether the airplane radio is 'going to' or 'coming from' the VOR
	 * returns 'true' if it is going to the VOR, 'false' if it is coming from the VOR
	 */
	/*private static boolean isTo(int obs, int radial){
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
	public String direction(int obs,int radial){
		if (isTo(obs, radial)){
			return "To";
		}
		return "From";
	}*/
	//Edit direction method 
	public String direction(int obs, int radial){
		direction = "TO";
		if(obs < 90){
			//If radial equals the edge points of obs (+/- 90)
			if((radial >= 0 && radial == obs+90) || (radial == ((obs - 90 + 360)%360))){
				direction = "OFF";
			}
			//if between 0-obs + 90 or after obs-90 to 0
			else if((radial >= 0 && radial < obs+90) || (radial > ((obs - 90 + 360)%360))){
				direction = "FROM";
			}
		}// if obs is greater than 270
		else if(obs>=270){
			if((radial == obs-90) || (radial == (obs + 90)%360)){
				direction = "OFF";
			}
			//if radial is 0 or greater than obs-90 and less than 360 or between 1 and obs +90
			else if(((radial == 0 || radial >= obs-90) && radial <= 360) || (radial <= (obs + 90)%360)){
				direction = "FROM";
			}
			
		}else{
			if(radial>obs-90 && radial< obs+90)
				direction = "FROM";
			else if(radial == obs-90 || radial == obs+90){
				direction = "OFF";
			}
		}
		return direction;
	}
	
}
