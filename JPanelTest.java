package gameTyping;

import java.awt.Container;
import java.io.IOException;

import javax.swing.JFrame;

public class JPanelTest extends JFrame {
	
	public Game_end jpanel01 = null;
	public Soyoung jpanel02 = null;
	public AddWord words = null;
	
	public void change(String panelName) {
		
		if(panelName.equals("panel01")) {
			this.getContentPane().removeAll();
			this.getContentPane().add(jpanel01);
			this.revalidate();
			this.repaint();
		}else if(panelName.equals("panel02")){
			this.getContentPane().removeAll();
			this.getContentPane().add(jpanel02);
			this.revalidate();
			this.repaint();
		}else if(panelName.equals("panel03")) {
			this.getContentPane().removeAll();
			this.getContentPane().add(words);
			this.revalidate();
			this.repaint();
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		JPanelTest win = new JPanelTest();
		win.setLocation(100, 100);
		win.setTitle("Baby Game");
		win.jpanel01 = new Game_end(win);
		win.jpanel02 = new Soyoung(win);
		win.words = new AddWord(win);
		
		
		
		win.setUndecorated(true);
		win.setResizable(false);
		win.getContentPane().add(win.jpanel01);;
		win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		win.setSize(960,540);
		win.setVisible(true);
		
	}
}
