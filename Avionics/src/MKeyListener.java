import java.awt.event.KeyAdapter;

import java.awt.event.KeyEvent;
import javax.swing.JFrame;

import javax.swing.JTextField;

public class MKeyListener {
	public MKeyListener() {
		JTextField textField = new JTextField();

		textField.addKeyListener(new MyKeyListener());

		JFrame jframe = new JFrame();

		jframe.add(textField);

		jframe.setSize(400, 350);

		jframe.setVisible(true);

	}

	public static void main(String[] argv) throws Exception {
		MKeyListener mk = new MKeyListener();
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

class MyKeyListener extends KeyAdapter {

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

}
