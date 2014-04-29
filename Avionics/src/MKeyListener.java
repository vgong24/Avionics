import java.awt.event.KeyAdapter;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;
import javax.swing.*;
import javax.imageio.ImageIO;


import java.awt.event.KeyEvent;
import javax.swing.JFrame;

import javax.swing.JTextField;

public class MKeyListener {
	public MKeyListener() {
		
	}

	public static void main(String[] argv) throws Exception {
		MKeyListener mk = new MKeyListener();
		JTextField textField = new JTextField();
		SimulatedRadio radio = new SimulatedRadio(5, true);
		VOR vor = new VOR(0, radio);
		Compass myCompass = new Compass(vor);
		JPanel panel = new JPanel();
		//panel.add(myCompass);
		panel.setVisible(true);
		JFrame jframe = new JFrame();

		jframe.add(panel);

		jframe.setSize(400, 350);

		jframe.setVisible(true);
		panel.requestFocusInWindow();
		panel.addKeyListener(new MyKeyListener(myCompass, vor));
		/*
		 * JTextField textField = new JTextField();
		 * 
		 * textField.addKeyListener(new MyKeyListener());
		 * 
		 * JFrame jframe = new JFrame();
		 * 
		 * jframe.add(textField);
		 * 
		 * jframe.setSize(400, 350);
		 * 
		 * jframe.setVisible(true);
		 */
	}

}

class MyKeyListener extends JPanel implements KeyListener {
	private VOR vor;
	JFrame frame;
	JPanel panel;

	public MyKeyListener(Compass myCompass, VOR vor){
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
		panel.requestFocusInWindow();
		addKeyListener(this);
	}

	@Override
	public void keyPressed(KeyEvent event) {

		char ch = event.getKeyChar();

		if (ch == 'a' || ch == 'b' || ch == 'c') {

			System.out.println(event.getKeyChar());

		}

		if (event.getKeyCode() == KeyEvent.VK_UP) {

			System.out.println("Key codes: " + event.getKeyCode());

		} else {
			System.out.println("Key codes: " + event.getKeyCode());
		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
