package gameTyping;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AddWord extends JPanel implements ActionListener {

	ArrayList<String> word = new ArrayList<>();   //�ܾ� �迭
	int i = 0;   				//�ܾ� �迭 �ε��� ����
	int findW;
	int z = 0;
	JTextField inputW;   		//�ܾ� �߰� �ؽ�Ʈ�ʵ�
	JTextArea listW;   			//�ܾ� ����Ʈ �ؽ�Ʈ������
	JLabel information;   		//�ȳ� ���̺�
	JButton addButton;   		//�߰� ��ư
	JButton sortButton;			//���� ��ư
	JButton saveButton;			//���� ��ư
	JButton backButton;			//�ڷΰ��� ��ư
	JButton modifyButton;		//�����ϱ� ��ư
	JScrollPane listScroll;   	//����Ʈ ��ũ��
	Font font = new Font("", Font.PLAIN, 18);	//��Ʈ, ũ�� ����
	FileWriter out;				//���� ����
	char[] ch;					//���Ͽ� ������ �� ����ϴ� charŸ�� �迭
	ImageIcon addW = new ImageIcon(AddWord.class.getResource("../images/addW.png"));
	ImageIcon addW2 =new ImageIcon(AddWord.class.getResource("../images/addW2.png"));
	ImageIcon saveW = new ImageIcon(AddWord.class.getResource("../images/save.png"));
	ImageIcon saveW2 = new ImageIcon(AddWord.class.getResource("../images/save2.png"));
	ImageIcon sortW = new ImageIcon(AddWord.class.getResource("../images/sort.png"));
	ImageIcon sortW2 = new ImageIcon(AddWord.class.getResource("../images/sort2.png"));
	ImageIcon modify = new ImageIcon(AddWord.class.getResource("../images/modify.png"));
	ImageIcon modify2 = new ImageIcon(AddWord.class.getResource("../images/modify2.png"));
	ImageIcon backGround1 = new ImageIcon(AddWord.class.getResource("../images/backGround1.png"));
	ImageIcon backGround = new ImageIcon(AddWord.class.getResource("../images/sketchbook.png")); //sketchbook ���� �ٲ�
	ImageIcon back = new ImageIcon(AddWord.class.getResource("../images/backButton.png"));
		
	   private int mouseX, mouseY;
	   private JLabel menuBar = new JLabel(new ImageIcon(AddWord.class.getResource("../images/menuBar.png")));
	   private ImageIcon exitButtonBasicImage = new ImageIcon(AddWord.class.getResource("../images/exitButtonBasic.png"));
	   private ImageIcon exitButtonEnteredImage = new ImageIcon(AddWord.class.getResource("../images/exitButtonEntered.png"));
	   private JButton exitButton = new JButton(exitButtonBasicImage);
	
	
	JPanelTest win;
	//

	public AddWord(JPanelTest win) {      //������
	   this.win = win;
		setSize(960, 540);   //ȭ�� ������ ;
	   setLayout(null);
	   
	   menuBar.setBounds(0, 0, 960, 30);
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				win.setLocation(x - mouseX, y - mouseY);
			}
		});
		add(menuBar);
		
		exitButton.setBounds(920, 0, 30, 30); // ���� �׳� ��ġ�� ũ��
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonEnteredImage);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPresseddMusic.mp3", false);
				buttonEnteredMusic.start();
				/*try {
					Thread.sleep(1000);
				} catch(InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);*/
				int result = JOptionPane.showConfirmDialog(null, "��.. ���� ����..?","����..?",
		                  JOptionPane.YES_NO_OPTION);
		            if(result == JOptionPane.CLOSED_OPTION) {
		               
		            }else if(result == JOptionPane.YES_NO_OPTION) {
		               try {
		                  Thread.sleep(100); // 10�� �Ŀ� �����尡 ���� �ݴ´�.
		               } catch(InterruptedException ex) {
		                  ex.printStackTrace();
		               }
		               System.exit(0);
		            }else {
		               
		            }
		            
			}
		});
		
		menuBar.add(exitButton);
	   
	   
	   //�г� ����
	   //�ܾ� �߰� �г� ����
	   JPanel inputPanel = new JPanel() {
		   public void paintComponent(Graphics g) {
			   g.drawImage(backGround1.getImage(), 0, 0, null);
			   setOpaque(false);
			   super.paintComponent(g);
		   }
	   };
	   inputPanel.setLayout(null);
	   inputPanel.setBounds(0, 30, 480, 450);
	   
	   //�ܾ� ����Ʈ �г� ����
	   JPanel listPanel = new JPanel() {
		   public void paintComponent(Graphics g) {
			   g.drawImage(backGround.getImage(), 0, 0, null);
			   setOpaque(false);
			   super.paintComponent(g);
		   }
	   };
	   listPanel.setLayout(null);
	   listPanel.setBounds(480, 30, 480, 450);
	   
	   //�߰� �˸� �г� ����
	   JPanel notice = new JPanel(); 
	   notice.setBackground(Color.PINK);
	   notice.setLayout(null);
	   notice.setBounds(0, 480, 960, 60);
		  
	   //�߰� ��ư ����
	   addButton = new JButton(addW);
	   addButton.setBounds(367, 200, 50, 50);
	   addButton.addActionListener(this);
	   addButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				addButton.setIcon(addW2);
				addButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				addButton.setIcon(addW);
				addButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
	   addButton.setBorderPainted(false);	//��ư �׵θ� ���ֱ�
	   addButton.setContentAreaFilled(false);	//��ư ���� ��� ǥ�� ���ֱ�
	   addButton.setFocusPainted(false);		//��Ŀ�� ���ֱ�
	   
	   //���� ��ư ����
	   sortButton = new JButton(sortW);
	   sortButton.setBounds(355, 310, 98, 62);
	   sortButton.addActionListener(this);
	   sortButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				sortButton.setIcon(sortW2);
				sortButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				sortButton.setIcon(sortW);
				sortButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
	   sortButton.setBorderPainted(false);		//��ư �׵θ� ���ֱ�
	   sortButton.setContentAreaFilled(false);	//��ư ���� ��� ǥ�� ���ֱ�
	   sortButton.setFocusPainted(false);		//��Ŀ�� ���ֱ�
	   
	   //���� ��ư ����
	   saveButton = new JButton(saveW);
	   saveButton.setBounds(355, 380, 98, 62);
	   saveButton.addActionListener(this);
	   saveButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {	//���ٴ��� ��
				saveButton.setIcon(saveW2);
				saveButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e) {		//���� ��
				saveButton.setIcon(saveW);
				saveButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
	   saveButton.setBorderPainted(false);		//��ư �׵θ� ���ֱ�
	   saveButton.setContentAreaFilled(false);	//��ư ���� ��� ǥ�� ���ֱ�
	   saveButton.setFocusPainted(false);		//��Ŀ�� ���ֱ�
	   
	   //�ڷΰ��� ��ư ����
	   backButton = new JButton(back);
	   backButton.setBounds(10,10,50,50);
	   backButton.addActionListener(this);
	   backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {	//���ٴ��� ��
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				
			}
			@Override
			public void mouseExited(MouseEvent e) {		//���� ��
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				//back��ư�� ����ȭ������ ���ư��� �̺�Ʈ
				win.change("panel01");
			}
		});
	   backButton.setBorderPainted(false);			//��ư �׵θ� ���ֱ�
	   backButton.setContentAreaFilled(false);		//��ư ���� ��� ǥ�� ���ֱ�
	   backButton.setFocusPainted(false);			//��Ŀ�� ���ֱ�
	   
	   //�����ϱ� ��ư
	   modifyButton = new JButton(modify);
	   modifyButton.setBounds(430, 200, 50, 50);
	   modifyButton.addActionListener(this);
	   modifyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {	//���ٴ��� ��
				modifyButton.setIcon(modify2);
				modifyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {		//���� ��
				modifyButton.setIcon(modify);
				modifyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
	   modifyButton.setBorderPainted(false);		//��ư �׵θ� ���ֱ�
	   modifyButton.setContentAreaFilled(false);	//��ư ���� ��� ǥ�� ���ֱ�
	   modifyButton.setFocusPainted(false);			//��Ŀ�� ���ֱ�
	   
	   //�ܾ� �Է�â
	   inputW = new JTextField();
	   inputW.addActionListener(this);
	   inputW.setFont(font);
	   inputW.setBounds(10, 200, 345, 50);
	           
	   //�ܾ� ����Ʈ
	   listW = new JTextArea();
	   listW.setBounds(90, 125, 200, 300);
	   listW.setEditable(false);
	   listW.setFont(font);
	   
	   //����Ʈ ��ũ��
	   listScroll = new JScrollPane(listW);
	   listScroll.setBounds(90, 128, 200, 300);
	       
	   // ����
	   information = new JLabel();
	   information.setBounds(400, 19, 300, 30);
	   information.setFont(font);
	   
	   //�г�, ���̾ƿ� ����
	   inputPanel.add(inputW);      	//�߰� �гο� �ؽ�Ʈ�ʵ� ����
	   inputPanel.add(addButton);   	//�߰� �гο� �߰� ��ư ����
	   inputPanel.add(backButton);		//�߰� �гο� �ڷΰ��� ��ư ����
	   inputPanel.add(modifyButton);	//�߰� �гο� ���� ��ư ����
	   listPanel.add(listScroll);   	//����Ʈ �гο� ����Ʈ ������ ����
	   listPanel.add(sortButton);		//����Ʈ �гο� ���� ��ư ����
	   listPanel.add(saveButton);		//����Ʈ �гο� ���� ��ư ����
	   notice.add(information);   		//�˸� �гο� �ȳ� ���̺� ����
	   
	   add(inputPanel); // ����
	   add(listPanel); 	// ������
	   add(notice); 	// �ϴ�
	   setVisible(true);
	}	

	//�ܾ� �˻��ؼ� �����ϴ� �޼���
	int search(String modifyW) {
		for(int j=0 ; j<word.size(); j++) { 
		    if(modifyW.equals(word.get(j)) ) { 
		    	findW = j;
		    	return 1;
		   }
	}
		return 0;
	}
	
	//�ܾ� �߰� �̺�Ʈ
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// textField���� Enter
        if(e.getSource() == inputW || e.getSource() == addButton) {
        	if(i==0) {
        		word.add(inputW.getText());
        		inputW.setText("");
        		listW.append(word.get(i) + " " + "\n");
        		information.setText("�ܾ��忡 �߰��߽��ϴ٢�");
        		i++;
        	} else if(i>0 && i<100) { 
        		for(int j=0; j<word.size(); j++) {
        			if(!word.contains(inputW.getText())){
        				word.add(inputW.getText());
        				listW.append(word.get(i) + " " + "\n");
        				information.setText("�ܾ��忡 �߰��߽��ϴ٢�");
        				i++;
        				inputW.setText("");
        				break;
        			}
        			information.setText("���̹� �߰��� �ܾ��Դϴ١�");   
        		}
        		inputW.setText("");
        	} else {
        		information.setText("�ܾ����� �� á��� ��_��");
        	}
        }
		
		//����
		if(e.getSource() == sortButton) {
			Collections.sort(word);
			listW.setText("");
			for(i=0; i<word.size(); i++) {
				listW.append(word.get(i) + " \n");
				information.setText("���ĵǾ����ϴ٢�");
			}
		}
		
		//�ܾ����� ���Ϸ� ����
		if(e.getSource() == saveButton) {
			String wordList = listW.getText();
			ch = new char[wordList.length()];
			for(int p=0; p<wordList.length(); p++) {
				ch[p]=wordList.charAt(p);
			}
			try {
				out = new FileWriter("c:\\java\\word.txt");	
				for(int j=0; j<ch.length; j++) {
					out.write(ch[j]);
				}
				out.close();
				information.setText("�� �ܾ����� �����߽��ϴ١�");
			} catch(Exception fe){
				fe.printStackTrace();
			}
		}
		
		//�ڷΰ��� ��ư
		if(e.getSource() == backButton) {
			//�ʱ�ȭ�� �г� ����
		}
		
		//�����ϱ� ��ư
		if(e.getSource() == modifyButton) {
			String modifyW = JOptionPane.showInputDialog("������ �ܾ �Է��ϼ���!");  
			int a = search(modifyW);
			if(a==1) {	//���� ���� �ִٸ�
				String reW = JOptionPane.showInputDialog("�ܾ �������ּ���!"); 
				word.set(findW, reW);
				listW.append(word.get(findW) + "\n");
				information.setText("�����߽��ϴ٢�");
			} else if(a==0) {
				information.setText("����ġ�ϴ� �ܾ �����ϴ١�");
			}
		}
	}
}