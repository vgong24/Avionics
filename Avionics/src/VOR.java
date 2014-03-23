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
	}
	
	//VOR ELEMENTS
	/*
	 * int degrees
	 * Sets the direction of where the VOR is pointing to
	 * Gets degrees from the dial
	 */
	int degrees;
	
	/*
	 * boolean to/from
	 * Where the object is relative to VOR
	 * If the plane is on the opposite direction of the perpendicular sides of the degrees
	 * Then it is 'to' else 'from'
	 */
	boolean isTo;
	
	//Coordinates of VOR
	int[] coord;
	
	/**
	 * Constructor
	 */
	public VOR(int posx, int posy){
		degrees = 0;
		isTo = true;
		coord = new int[2];
		coord[0]= posx;
		coord[1]= posy;
	}
	/**
	 * Get the radio signal/dial from the plane
	 * input is stored in VOR
	 * @param degree
	 */
	public void radioDegree(int degree){
		degrees = degree;
	}
	
}
