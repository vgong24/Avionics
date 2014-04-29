import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.*;
import java.awt.*;

public class Simulation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SimulatedRadio radio = new SimulatedRadio(0, true);
		VOR vor = new VOR(180, radio);
<<<<<<< HEAD
		// we would have an eventListner to change the OBS
		//vor.rotateOBS(-10);
		/**
		 * Trying to display i many JFrames to see if the needle
		 * will slowly rotate according to the current radial
		 */
		for(int obs = 0; obs <= 180; obs+=10){
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
				System.out.println(i+"  Direction: "+ vor.getDirection());
				try {
					Thread.sleep(30);
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
=======
		Compass compass = new Compass(vor);
		JFrame f = new JFrame();
		f.getContentPane().add(compass);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setVisible(true);
>>>>>>> 869cc721892a15e2d383357913ab299bc1a11da4
	}
	
}
