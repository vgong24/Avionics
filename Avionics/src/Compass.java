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
	public Compass() {
		OBSDegrees = 0;
		dirDegrees = 0;
		needleDegrees = 0;
		addKeyListener(this);
	}
	/**************************************************************************** Setup Interface
	 * 
	 */
	public Dimension getPreferredSize() {
		return new Dimension(512, 512);
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
		
			
			/**************** LOOOOOOOOOOOOOK HEEEEEEEEEERRRRRRRRRREEEEEEEEEEEEEEE******************
			 * Something needs to be changed here in order to make 
			 * the Needle completely visible... It shows up completely visible if 
			 * w = needle.getWidth() and h = needle.getHeight()
			 * but not when w = 0 and h= 0. There is a little piece of the image,
			 * and it is placed where it should be.
			 * 
			 * 
			 * edit: I think since the picture originally starts on the left corner
			 * it will be cut off the window if you rotated it.
			 */
			
			rad = Math.toRadians(needleDegrees);
			//We need to rotate at the top pivot point
			w = needle.getWidth()/2;
			h = needle.getHeight()/2;
			at = AffineTransform.getRotateInstance(rad, 255, 120);
			int needlex = (compassImg.getWidth() / 2);
			int needley = compassImg.getHeight() / 4;
			//graphics.drawImage(needle, needlex, needley, this);
			// you can put it back in
			op = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
			
			graphics.drawImage(op.filter(needle, null), 0, 0, this);
			
			setFocusable(true);
			requestFocusInWindow();

		} catch (Exception e) {
			System.out.println(e);
		}

		// **********************
		// need to at least do the needle still
	}
	/**************************************************************************** Event Listeners
	 * 
	 */
	
	public void keyPressed(KeyEvent e) {
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
	public void updateVariables(VOR vor){
		rotateOBS(vor.getOBS());
		rotateNeedle(vor.getNeedle());
	}

	// move the picture to the side to see

}
