import javax.swing.*;
import java.awt.*;
public class VOR {
  public static void main(String[] args) {
    Compass myCompass = new Compass();
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JPanel panel = new JPanel();
    panel.add(myCompass);
    panel.setVisible(true);
    frame.add(panel);
    frame.pack();
    frame.setVisible(true);
  }
}   
