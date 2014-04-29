
public class SimulatedRadio {
	/*"You will need to implement a simulated radio to generate the intercepted radial(0-359)
	a station identification in morse code,and an indication if the radio signal is Good or Bad, 
	i.e., the plane is over the station or too far away to receive signal
	 
	*/
	/**************************************************************************** Instance Variables
	 * 
	 */
	//radial is the degrees the plane is in relation to the VOR
	int radial;
	int stationID;
	
	final private boolean GOOD = true;
	final private boolean BAD = false;
	
	boolean signal;
	
	/**************************************************************************** Constructor
	 * 
	 * @param radial
	 * @param stationID
	 * @param signal
	 */
	public SimulatedRadio(int radial, int stationID, boolean signal){
		this.radial = radial;
		this.stationID = stationID;
		this.signal = signal;
	}
	public SimulatedRadio(int radial, boolean signal){
		this.radial = radial;
		this.signal = signal;
	}
	
	/**************************************************************************** accessor methods
	 * 
	 * @return
	 */
	public int getRadial(){
		return radial;
	}
	public int getStationID(){
		return stationID;
	}
	public boolean getSignalStrength(){
		return signal;
	}
	
	/**************************************************************************** mutator methods
	 * 
	 * @param change
	 */
	public void updateRadial(int change){
		/*if radial = 350, and change was 25 degrees. 
		then radial = 15 degrees*/
		radial = change;
	}
	public void setRadial(int value){
		radial = value;
	}
}
