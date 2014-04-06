import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;
import javax.swing.*;
import javax.imageio.ImageIO;

public class Compass extends JPanel implements KeyListener {
  double OBSDegrees;
  double dirDegrees;
  double needleDegrees;
  
  public Compass() {
    double OBSDegrees = 0;
    double dirDegrees = 0;
    double needleDegrees = 0;
    addKeyListener(this);
  }

  public Dimension getPreferredSize() {
    return new Dimension(512, 512);
  }
  
  protected void paintComponent(Graphics g) {
    try {
      super.paintComponent(g);
      //test if this executes
      System.out.println("");
      
      Graphics2D g2D = (Graphics2D) g;
      //original code BufferedImage image = ImageIO.read(new File("Compass.png"));
      BufferedImage image = ImageIO.read(getClass().getResourceAsStream("Compass.png"));
      //Test if it gets past
      System.out.println("Passed1");
      
      g2D.drawImage(image, 0, 0, this);
      //original image = ImageIO.read(new File("obsIcon.png"));
      image = ImageIO.read(getClass().getResourceAsStream("obsIcon.png"));
      
      double rad = Math.toRadians(OBSDegrees); 
      double w = image.getWidth() / 2; 
      double h = image.getHeight() / 2; 
      AffineTransform at = AffineTransform.getRotateInstance(rad, w, h); 
      AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR); 
      g2D.drawImage(op.filter(image, null), 15, 400, this);
    
      //original code image = ImageIO.read(new File("Directions.png"));
      image = ImageIO.read(getClass().getResourceAsStream("Directions.png"));
      
      rad = Math.toRadians(dirDegrees); 
      w = image.getWidth() / 2; 
      h = image.getHeight() / 2; 
      at = AffineTransform.getRotateInstance(rad, w, h); 
      op = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR); 
      g2D.drawImage(op.filter(image, null), 0, 0, this);
      setFocusable(true); 
      requestFocusInWindow();

    } catch (Exception e) {
    	System.out.println(e);
    }
    

//**********************
//need to at least do the needle still
  } 
  
  public void keyPressed(KeyEvent e) {}
  public void keyReleased(KeyEvent e) {}
  public void keyTyped (KeyEvent e) {}
}
    
 
