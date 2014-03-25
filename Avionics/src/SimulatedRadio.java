
public class SimulatedRadio {
	/*"You will need to implement a simulated radio to generate the intercepted radial(0-359)
	a station identification in morse code,and an indication if the radio signal is Good or Bad, 
	i.e., the plane is over the station or too far away to receive signal
	 
	*/
	int radial;
	int stationID;
	
	final private boolean GOOD = true;
	final private boolean BAD = false;
	
	boolean signal;
	public SimulatedRadio(int radial, int stationID, boolean signal){
		this.radial = radial;
		this.stationID = stationID;
		this.signal = signal;
	}
	
	//accessor methods for VOR
	public int getRadial(){
		return radial;
	}
	public int getStationID(){
		return stationID;
	}
	public boolean getSignalStrength(){
		return signal;
	}
	
	//mutator methods
	public void updateRadial(int change){
		/*if radial = 350, and change was 25 degrees. 
		then radial = 15 degrees*/
		radial = (radial + change + 360) % 360;
	}
	
}