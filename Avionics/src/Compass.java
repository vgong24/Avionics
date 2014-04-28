import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;
import javax.swing.*;
import javax.imageio.ImageIO;

public class Compass extends JPanel implements KeyListener {
	/************************************************************************ Instance variables
	 * 
	 */
	VOR vor;
	double OBSDegrees;
	double dirDegrees;
	double needleDegrees;
	/**
	 * Created global variable If we want to edit the images and place them back
	 * into the graphics Then we can use a method to rerender the compass with
	 * the edited images
	 */
	Graphics2D graphics = null;
	/**
	 * Image of the Compass
	 */
	BufferedImage compassImg = null;
	/**
	 * Image of OBS
	 */
	BufferedImage obsImg = null;
	/**
	 * Image of Direction
	 */
	BufferedImage direct = null;
	/**
	 * Image of needle
	 */
	BufferedImage needle = null;
	
	/**************************************************************************** Constructor
	 * 
	 */
	/**
	 * gets OBS degrees from the radio I thiiink the dirDegrees should match the
	 * OBS degrees or turn slower than when the OBS is spinning needleDegrees
	 * should be calculated from where the plan is relative to VOR
	 */
	public Compass(VOR vor) {
		this.setPreferredSize(new Dimension(512,512));
		this.vor = vor;
		updateVariables();
		addKeyListener(this);
	}
	/**************************************************************************** Setup Interface
	 * 
	 */
	/*public Dimension getPreferredSize() {
		return new Dimension(512, 512);
	}*/
	public void addNotify(){
		super.addNotify();
		requestFocus();
	}
	protected void paintComponent(Graphics g) {
		try {
			super.paintComponent(g);
			final Graphics2D graphics = (Graphics2D)g.create();
			//graphics = (Graphics2D) g;
			
			compassImg = ImageIO.read(getClass().getResourceAsStream(
					"Compass.png"));

			graphics.drawImage(compassImg, 0, 0, this);
			obsImg = ImageIO
					.read(getClass().getResourceAsStream("obsIcon.png"));

			double rad = Math.toRadians(OBSDegrees);
			double w = obsImg.getWidth() / 2;
			double h = obsImg.getHeight() / 2;
			
			
			
			AffineTransform at = AffineTransform.getRotateInstance(rad, w, h);
			AffineTransformOp op = new AffineTransformOp(at,
					AffineTransformOp.TYPE_BILINEAR);
			graphics.drawImage(op.filter(obsImg, null), 15, 400, this);

			direct = ImageIO.read(getClass().getResourceAsStream(
					"Directions.png"));

			rad = Math.toRadians(dirDegrees);
			w = direct.getWidth() / 2;
			h = direct.getHeight() / 2;
			at = AffineTransform.getRotateInstance(rad, w, h);
			op = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
			graphics.drawImage(op.filter(direct, null), 0, 0, this);

			needle = ImageIO.read(getClass().getResourceAsStream(
					"Needle2.png"));
			
			rad = Math.toRadians(needleDegrees);
			//We need to rotate at the top pivot point
			//UPDATE: We just used a larger needle picture to rotate
			w = needle.getWidth()/2;
			h = needle.getHeight()/2;
			at = AffineTransform.getRotateInstance(rad, 255, 120);
			int needlex = (compassImg.getWidth() / 2);
			int needley = compassImg.getHeight() / 4;
			op = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
			
			graphics.drawImage(op.filter(needle, null), 0, 0, this);
			
			setFocusable(true);
			requestFocusInWindow();

		} catch (Exception e) {
			System.out.println(e);
		}

	}
	/**************************************************************************** Event Listeners
	 * 
	 */
	
	public void keyPressed(KeyEvent event) {
		
		char ch = event.getKeyChar();

		if (ch == 'a' || ch == 'b' || ch == 'c') {

			System.out.println(event.getKeyChar());

		}

		if (event.getKeyCode() == KeyEvent.VK_UP) {

			System.out.println("Key codes: UP, " + event.getKeyCode());

		}else if(event.getKeyCode() == KeyEvent.VK_LEFT){
			vor.rotateOBS(1);
			updateVariables();
			System.out.println(vor.getOBS());
		
		}else{
			System.out.println("Key codes: " + event.getKeyCode());
		}
		repaint();

	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}
	/**************************************************************************** Mutators
	 * 
	 */
	/**
	 * I think we just need 1 rotating method By rotating the OBS it causes the
	 * both the OBS and compass to rotate The needle changes based on the planes
	 * position according to the OBS direction
	 * 
	 * The direction should point according to what the vor.obs is pointing to
	 * 
	 * @param degrees
	 */
	public void rotateOBS(int degrees) {
		dirDegrees = 0 - degrees;
		OBSDegrees = 0 - degrees;
	}
	
	/** Value entered will change the degrees the needle should point to.
	 * 'degrees' is retrieved from the VOR needle variable
	 * vor.getNeedle()
	 * 
	 * @param degrees
	 */
	public void rotateNeedle(double degrees) {
		needleDegrees = 0 - degrees;
	}
	//Save time method. Put in the vor you want to use
	public void updateVariables(){
		rotateOBS(vor.getOBS());
		rotateNeedle(vor.getNeedle());
	}

	// move the picture to the side to see
	
	public static void main(String[] s){
		JFrame f = new JFrame();
		f.getContentPane().add(new Compass(new VOR(0, new SimulatedRadio(5, true))));
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setVisible(true);
	}

}
