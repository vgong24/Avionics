import javax.swing.JFrame;
import javax.swing.JPanel;


public class Simulation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello World");
		System.out.println("Welcome Aboard.");
		System.out.println("Thank you for flying Juneau Airlines! :)");
		System.out.println("----------------------------------------");

		/**************************************** Initial setup
		 * 
		 * Compass myCompass = new Compass();
		 * JFrame frame = new JFrame();
		 * frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 * JPanel panel = new JPanel();
		 * panel.add(myCompass);
		 * panel.setVisible(true);
		 * frame.add(panel);
		 * frame.pack();
		 * frame.setVisible(true);
		 */
		Compass myCompass = new Compass();

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		/*panel.add(myCompass);
		panel.setVisible(true);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
		 */
		// create simulated radio TESTING STARTS HERE
		SimulatedRadio radio = new SimulatedRadio(5, true);
		VOR vor = new VOR(180, radio);
		// we would have an eventListner to change the OBS
		//vor.rotateOBS(-10);
		/**
		 * Trying to display i many JFrames to see if the needle
		 * will slowly rotate according to the current radial
		 */
		for(int obs = 0; obs < 180; obs+=5){
			for(int i = 0; i < 360; i +=5){
				/*frame = new JFrame();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				 */
				panel = new JPanel();
				//change values
				vor.setOR(obs,i);
				myCompass.updateVariables(vor);

				panel.add(myCompass);
				panel.setVisible(true);
				frame.add(panel);
				frame.pack();
				frame.setVisible(true);
				System.out.println(i+"   To exit, drag box to the side and press x");
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		/*vor.setOR(0,355);
		myCompass.updateVariables(vor);
		panel.add(myCompass);
		frame.add(panel);*/
		System.out.println("If radial is between " + (vor.getOBS() + 180)%360 + " and " + vor.getOBS() +" should point right");
		System.out.println("Radial: "+vor.getRadial());
		System.out.println("Needle should point "+vor.needleDirection());
		System.out.println(vor.mod(-5,360));
	}

}
