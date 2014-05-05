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
	SimulatedRadio radio;
	double OBSDegrees;
	double dirDegrees;
	double needleDegrees;
	double rad,h,w;
	AffineTransform at = null;
	AffineTransformOp op = null;
	final String sTo = "TO";
	final String sFrom = "FROM";
	final String sBad = "OFF";
	String vorDirection = "";
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
	
	BufferedImage imgTo, imgFrom, imgBad;
	
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
		radio = vor.getRadio();
		updateVariables();
		addKeyListener(this);
		
		//set images
		try{
			compassImg = ImageIO.read(getClass().getResourceAsStream(
					"Compass.png"));
			direct = ImageIO.read(getClass().getResourceAsStream(
					"Directions.png"));
			needle = ImageIO.read(getClass().getResourceAsStream(
					"Needle2.png"));
			obsImg = ImageIO
					.read(getClass().getResourceAsStream("obsIcon.png"));
			imgTo = ImageIO.read(getClass().getResourceAsStream("to.jpg"));
			imgFrom = ImageIO.read(getClass().getResourceAsStream("from.jpg"));
			imgBad = ImageIO.read(getClass().getResourceAsStream("bad.jpg"));
		}catch(Exception e){
			
		}
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
			
			
			//draws the stationary piece
			graphics.drawImage(compassImg, 0, 0, this);
			vorDirection = vor.getDirection();
			switch(vorDirection){
			case sTo: graphics.drawImage(imgTo,0,0,this);break;
			case sFrom: graphics.drawImage(imgFrom,0,0,this);break;
			case sBad: graphics.drawImage(imgBad,0,0,this);break;
			default: graphics.drawImage(imgBad,0,0,this);break;
			}
			
			//draws the obs
			rad = Math.toRadians(OBSDegrees);
			w = obsImg.getWidth() / 2;
			h = obsImg.getHeight() / 2;
			at = AffineTransform.getRotateInstance(rad, w, h);
			op = new AffineTransformOp(at,
					AffineTransformOp.TYPE_BILINEAR);
			graphics.drawImage(op.filter(obsImg, null), 15, 400, this);

			
			//draws the radians
			rad = Math.toRadians(dirDegrees);
			w = direct.getWidth() / 2;
			h = direct.getHeight() / 2;
			at = AffineTransform.getRotateInstance(rad, w, h);
			op = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
			graphics.drawImage(op.filter(direct, null), 0, 0, this);

			
			//draws the needle
			rad = Math.toRadians(needleDegrees);
			w = needle.getWidth()/2;
			h = needle.getHeight()/2;
			at = AffineTransform.getRotateInstance(rad, 255, 120);
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

		if (event.getKeyCode() == KeyEvent.VK_UP) {
			vor.rotateRadial(1);
		}else if(event.getKeyCode() == KeyEvent.VK_DOWN){
			vor.rotateRadial(-1);
			
		}else if(event.getKeyCode() == KeyEvent.VK_LEFT){
			vor.rotateOBS(-1);
		
		}else if(event.getKeyCode() == KeyEvent.VK_RIGHT){
			vor.rotateOBS(1);
		}
		vor.needleDirection();
		updateVariables();
		
		System.out.println("OBS currently set at : "+vor.getOBS()+ "; Radial currently set at : "+vor.getRadial() + " Signal Strength: " + vor.getSignal());
		//if(vor.getSignalbool()){
		repaint();
		//}

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
	public void rotateOBS(double degrees) {
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
		vor.direction();
	}

}
