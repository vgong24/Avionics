
public class SimulatedRadio {
	/*"You will need to implement a simulated radio to generate the intercepted radial(0-359)
	a station identification in morse code,and an indication if the radio signal is Good or Bad, 
	i.e., the plane is over the station or too far away to receive signal
	 
	*/
	/**************************************************************************** Instance Variables
	 * 
	 */
	//radial is the degrees the plane is in relation to the VOR
	double radial;
	double stationID;
	
	final private boolean GOOD = true;
	final private boolean BAD = false;
	
	boolean signal;
	int firstLetter = 97;
	
	final String[] alphaMorse = {". -", "- . . . ","- . - .", "- . .", ".", ". . - .","- - .",
			". . . .", ". .", ". - - -", "- . -", ". - . .", "- -", "- .", "- - -", ". - - .",
			"- - . -", ". - .", ". . .", "-", ". . -", ". . . -", ". - -", "- . . -", "- . - -",
			"- - . ."};
	
	//Morse code symbols (source: Wikipedia)
	String a = ". -";
	String b = "- . . . ";
	String c = "- . - ." ;
	String d = "- . .";
	String e = ".";
	String f = ". . - .";
	String g = "- - .";
	String h = ". . . .";
	String i = ". .";
	String j = ". - - -";
	String k = "- . -";
	String l = ". - . .";
	String m = "- -";
	String n = "- .";
	String o = "- - -";
	String p = ". - - .";
	String q = "- - . -";
	String r = ". - .";
	String s = ". . .";
	String t = "-";
	String u = ". . -";
	String v = ". . . -";
	String w = ". - -";
	String x = "- . . -";
	String y = "- . - -";
	String z = "- - . .";
	
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
	public double getRadial(){
		return radial;
	}
	public double getStationID(){
		return stationID;
	}
	public boolean getSignalStrength(){
		return signal;
	}
	
	/**************************************************************************** mutator methods
	 * 
	 * @param change
	 */
	public void updateRadial(double change){
		/*if radial = 350, and change was 25 degrees. 
		then radial = 15 degrees*/
		radial = change;
	}
	public void setRadial(double value){
		radial = value;
	}
	
	/**
	 * Assuming that you manually have to enter the stationID, the method
	 * converts the written letters to morse codes and returns it.
	 * 
	 * QUESTION:  DO WE HAVE TO MANUALLY TYPE THIS OR IS IT RANDOMLY GENERATED?
	 *            WE CAN REPLACE THE FUNCTION PARAMETERS WITH A RANDOM GENERATOR IF NECESSARY. - Jason
	 * 
	 * @param first
	 * @param second
	 * @param third
	 * @return string
	 */
	
	public String setStationID(String first, String second, String third){
		String morseCode = "";
		
		//transcribe first letter
				
		switch(first){
			case "a": morseCode = a; break;
			case "b": morseCode = b; break;
			case "c": morseCode = c; break;
			case "d": morseCode = d; break;
			case "e": morseCode = e; break;
			case "f": morseCode = f; break;
			case "g": morseCode = g; break;
			case "h": morseCode = h; break;
			case "i": morseCode = i; break;
			case "j": morseCode = j; break;
			case "k": morseCode = k; break;
			case "l": morseCode = l; break;
			case "m": morseCode = m; break;
			case "n": morseCode = n; break;
			case "o": morseCode = o; break;
			case "p": morseCode = p; break;
			case "q": morseCode = q; break;
			case "r": morseCode = r; break;
			case "s": morseCode = s; break;
			case "t": morseCode = t; break;
			case "u": morseCode = u; break;
			case "v": morseCode = v; break;
			case "w": morseCode = w; break;
			case "x": morseCode = x; break;
			case "y": morseCode = y; break;
			case "z": morseCode = z; break;
			default:  morseCode = "Destination Not Found; Try again!"; break;
		}
			
		//transcribe second letter
		switch(second){
			case "a": morseCode = morseCode + "   " + a; break;
			case "b": morseCode = morseCode + "   " + b; break;
			case "c": morseCode = morseCode + "   " + c; break;
			case "d": morseCode = morseCode + "   " + d; break;
			case "e": morseCode = morseCode + "   " + e; break;
			case "f": morseCode = morseCode + "   " + f; break;
			case "g": morseCode = morseCode + "   " + g; break;
			case "h": morseCode = morseCode + "   " + h; break;
			case "i": morseCode = morseCode + "   " + i; break;
			case "j": morseCode = morseCode + "   " + j; break;
			case "k": morseCode = morseCode + "   " + k; break;
			case "l": morseCode = morseCode + "   " + l; break;
			case "m": morseCode = morseCode + "   " + m; break;
			case "n": morseCode = morseCode + "   " + n; break;
			case "o": morseCode = morseCode + "   " + o; break;
			case "p": morseCode = morseCode + "   " + p; break;
			case "q": morseCode = morseCode + "   " + q; break;
			case "r": morseCode = morseCode + "   " + r; break;
			case "s": morseCode = morseCode + "   " + s; break;
			case "t": morseCode = morseCode + "   " + t; break;
			case "u": morseCode = morseCode + "   " + u; break;
			case "v": morseCode = morseCode + "   " + v; break;
			case "w": morseCode = morseCode + "   " + w; break;
			case "x": morseCode = morseCode + "   " + x; break;
			case "y": morseCode = morseCode + "   " + y; break;
			case "z": morseCode = morseCode + "   " + z; break;
			default:  morseCode = "Destination Not Found; Try again!\n"; break;
		}
			
		//transcribe third letter
				switch(third){
					case "a": morseCode = morseCode + "   " + a; break;
					case "b": morseCode = morseCode + "   " + b; break;
					case "c": morseCode = morseCode + "   " + c; break;
					case "d": morseCode = morseCode + "   " + d; break;
					case "e": morseCode = morseCode + "   " + e; break;
					case "f": morseCode = morseCode + "   " + f; break;
					case "g": morseCode = morseCode + "   " + g; break;
					case "h": morseCode = morseCode + "   " + h; break;
					case "i": morseCode = morseCode + "   " + i; break;
					case "j": morseCode = morseCode + "   " + j; break;
					case "k": morseCode = morseCode + "   " + k; break;
					case "l": morseCode = morseCode + "   " + l; break;
					case "m": morseCode = morseCode + "   " + m; break;
					case "n": morseCode = morseCode + "   " + n; break;
					case "o": morseCode = morseCode + "   " + o; break;
					case "p": morseCode = morseCode + "   " + p; break;
					case "q": morseCode = morseCode + "   " + q; break;
					case "r": morseCode = morseCode + "   " + r; break;
					case "s": morseCode = morseCode + "   " + s; break;
					case "t": morseCode = morseCode + "   " + t; break;
					case "u": morseCode = morseCode + "   " + u; break;
					case "v": morseCode = morseCode + "   " + v; break;
					case "w": morseCode = morseCode + "   " + w; break;
					case "x": morseCode = morseCode + "   " + x; break;
					case "y": morseCode = morseCode + "   " + y; break;
					case "z": morseCode = morseCode + "   " + z; break;
					default:  morseCode = "Destination Not Found; Try again!\n"; break;
		}
		return morseCode;	
		
	}
	/**
	 * Takes in a String 
	 * returns the String in morse code. 
	 * String characters must be between a-z and contain exactly 3 letters
	 * @param id
	 * @return
	 */
	public String setStationID(String id){
		String morseCode = "";
		if(id.length() != 3){
			return "ID needs to be 3 characters. Try again\n";
		}
		char[] list = new char[3];
				id.getChars(0, id.length(), list, 0);
				
				for(int i = 0; i <list.length; i++){
					if(list[i] < 'a' || list[i] > 'z'){
						return "Found character not between a - z. Try again\n";
					}else if(i == list.length -1){
						morseCode += alphaMorse[list[i] - 'a'];
					}else{
						morseCode += alphaMorse[list[i] - 'a'] + " , ";
					}
				}
				return morseCode;
	}
	
}


