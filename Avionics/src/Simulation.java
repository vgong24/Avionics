import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.*;
import java.awt.*;

public class Simulation extends JPanel implements KeyListener {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SimulatedRadio radio = new SimulatedRadio(5, true);
		VOR vor = new VOR(0, radio);
		Compass myCompass = new Compass(vor);
		//Simulation simul = new Simulation(vor);
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		/****************************************
		 * Initial setup
		 * 
		 * Compass myCompass = new Compass(); JFrame frame = new JFrame();
		 * frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); JPanel panel =
		 * new JPanel(); panel.add(myCompass); panel.setVisible(true);
		 * frame.add(panel); frame.pack(); frame.setVisible(true);
		 */
		//panel.addKeyListener(myCompass);
		panel.add(myCompass);
		panel.updateUI();
		panel.setVisible(true);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);

		// create simulated radio TESTING STARTS HERE
		// SimulatedRadio radio = new SimulatedRadio(5, true);
		// VOR vor = new VOR(180, radio);
		// we would have an eventListner to change the OBS
		// vor.rotateOBS(-10);
		/**
		 * Trying to display i many JFrames to see if the needle will slowly
		 * rotate according to the current radial
		 * 
		 * for(int obs = 0; obs < 180; obs+=5){ for(int i = 0; i < 360; i +=5){
		 * /*frame = new JFrame();
		 * frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 * 
		 * //change values vor.setOR(obs,i); myCompass.updateVariables(vor);
		 * 
		 * panel.add(myCompass); panel.updateUI(); panel.setVisible(true);
		 * frame.add(panel); frame.pack(); frame.setVisible(true);
		 * System.out.println(i+"  Direction: "+ vor.getDirection()); try {
		 * Thread.sleep(300); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } } }
		 * 
		 * /*vor.setOR(0,355); myCompass.updateVariables(vor);
		 * panel.add(myCompass); frame.add(panel);
		 */
		System.out.println("If radial is between " + (vor.getOBS() + 180) % 360
				+ " and " + vor.getOBS() + " should point right");
		System.out.println("Radial: " + vor.getRadial());
		System.out.println("Needle should point " + vor.needleDirection());
		System.out.println(vor.mod(-5, 360));
	}

	private Compass myCompass;
	private VOR vor;
	private JFrame frame;
	private JPanel panel;

	public Simulation(Compass myCompass) {
		this.setVisible(true);

		myCompass = new Compass(vor);
		this.vor = vor;
		myCompass.updateVariables();
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
		panel.add(myCompass);

		panel.setVisible(true);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
		addKeyListener(this);

	}

	public Simulation() {

	}

	public void keyPressed(KeyEvent e) {
		System.out.println("pass");
		int key = e.getKeyCode();
		System.out.println("yup");
		/*
		 * if (key == KeyEvent.VK_LEFT) { System.out.println("LeFT");
		 * vor.setOBS(vor.getOBS() + 1); vor.setRadioRadial(vor.getRadial() +
		 * 1); vor.direction(vor.getOBS() + 1, vor.getRadial() + 1);
		 * vor.needleDirection(); myCompass.updateVariables(vor);
		 * 
		 * panel.add(myCompass); panel.updateUI(); panel.setVisible(true);
		 * frame.add(panel); frame.pack(); frame.setVisible(true); } else {
		 * vor.setOBS(vor.getOBS() + 1); System.out.println(key);
		 * panel.updateUI(); }
		 */

		System.out.println(key);
		char car = e.getKeyChar();
		System.out.println(car);
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

}
