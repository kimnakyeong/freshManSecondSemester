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

	ArrayList<String> word = new ArrayList<>();   //단어 배열
	int i = 0;   				//단어 배열 인덱스 변수
	int findW;
	int z = 0;
	JTextField inputW;   		//단어 추가 텍스트필드
	JTextArea listW;   			//단어 리스트 텍스트에리어
	JLabel information;   		//안내 레이블
	JButton addButton;   		//추가 버튼
	JButton sortButton;			//정렬 버튼
	JButton saveButton;			//저장 버튼
	JButton backButton;			//뒤로가기 버튼
	JButton modifyButton;		//수정하기 버튼
	JScrollPane listScroll;   	//리스트 스크롤
	Font font = new Font("", Font.PLAIN, 18);	//폰트, 크기 지정
	FileWriter out;				//파일 저장
	char[] ch;					//파일에 저정할 때 사용하는 char타입 배열
	ImageIcon addW = new ImageIcon(AddWord.class.getResource("../images/addW.png"));
	ImageIcon addW2 =new ImageIcon(AddWord.class.getResource("../images/addW2.png"));
	ImageIcon saveW = new ImageIcon(AddWord.class.getResource("../images/save.png"));
	ImageIcon saveW2 = new ImageIcon(AddWord.class.getResource("../images/save2.png"));
	ImageIcon sortW = new ImageIcon(AddWord.class.getResource("../images/sort.png"));
	ImageIcon sortW2 = new ImageIcon(AddWord.class.getResource("../images/sort2.png"));
	ImageIcon modify = new ImageIcon(AddWord.class.getResource("../images/modify.png"));
	ImageIcon modify2 = new ImageIcon(AddWord.class.getResource("../images/modify2.png"));
	ImageIcon backGround1 = new ImageIcon(AddWord.class.getResource("../images/backGround1.png"));
	ImageIcon backGround = new ImageIcon(AddWord.class.getResource("../images/sketchbook.png")); //sketchbook 으로 바꿈
	ImageIcon back = new ImageIcon(AddWord.class.getResource("../images/backButton.png"));
		
	   private int mouseX, mouseY;
	   private JLabel menuBar = new JLabel(new ImageIcon(AddWord.class.getResource("../images/menuBar.png")));
	   private ImageIcon exitButtonBasicImage = new ImageIcon(AddWord.class.getResource("../images/exitButtonBasic.png"));
	   private ImageIcon exitButtonEnteredImage = new ImageIcon(AddWord.class.getResource("../images/exitButtonEntered.png"));
	   private JButton exitButton = new JButton(exitButtonBasicImage);
	
	
	JPanelTest win;
	//

	public AddWord(JPanelTest win) {      //생성자
	   this.win = win;
		setSize(960, 540);   //화면 사이즈 ;
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
		
		exitButton.setBounds(920, 0, 30, 30); // 단지 그냥 위치와 크기
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
				int result = JOptionPane.showConfirmDialog(null, "힝.. 벌써 가요..?","오잉..?",
		                  JOptionPane.YES_NO_OPTION);
		            if(result == JOptionPane.CLOSED_OPTION) {
		               
		            }else if(result == JOptionPane.YES_NO_OPTION) {
		               try {
		                  Thread.sleep(100); // 10초 후에 스레드가 문을 닫는다.
		               } catch(InterruptedException ex) {
		                  ex.printStackTrace();
		               }
		               System.exit(0);
		            }else {
		               
		            }
		            
			}
		});
		
		menuBar.add(exitButton);
	   
	   
	   //패널 생성
	   //단어 추가 패널 생성
	   JPanel inputPanel = new JPanel() {
		   public void paintComponent(Graphics g) {
			   g.drawImage(backGround1.getImage(), 0, 0, null);
			   setOpaque(false);
			   super.paintComponent(g);
		   }
	   };
	   inputPanel.setLayout(null);
	   inputPanel.setBounds(0, 30, 480, 450);
	   
	   //단어 리스트 패널 생성
	   JPanel listPanel = new JPanel() {
		   public void paintComponent(Graphics g) {
			   g.drawImage(backGround.getImage(), 0, 0, null);
			   setOpaque(false);
			   super.paintComponent(g);
		   }
	   };
	   listPanel.setLayout(null);
	   listPanel.setBounds(480, 30, 480, 450);
	   
	   //추가 알림 패널 생성
	   JPanel notice = new JPanel(); 
	   notice.setBackground(Color.PINK);
	   notice.setLayout(null);
	   notice.setBounds(0, 480, 960, 60);
		  
	   //추가 버튼 생성
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
	   addButton.setBorderPainted(false);	//버튼 테두리 없애기
	   addButton.setContentAreaFilled(false);	//버튼 영역 배경 표시 없애기
	   addButton.setFocusPainted(false);		//포커스 없애기
	   
	   //정렬 버튼 생성
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
	   sortButton.setBorderPainted(false);		//버튼 테두리 없애기
	   sortButton.setContentAreaFilled(false);	//버튼 영역 배경 표시 없애기
	   sortButton.setFocusPainted(false);		//포커스 없애기
	   
	   //저장 버튼 생성
	   saveButton = new JButton(saveW);
	   saveButton.setBounds(355, 380, 98, 62);
	   saveButton.addActionListener(this);
	   saveButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {	//갖다댔을 떄
				saveButton.setIcon(saveW2);
				saveButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e) {		//뗐을 때
				saveButton.setIcon(saveW);
				saveButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
	   saveButton.setBorderPainted(false);		//버튼 테두리 없애기
	   saveButton.setContentAreaFilled(false);	//버튼 영역 배경 표시 없애기
	   saveButton.setFocusPainted(false);		//포커스 없애기
	   
	   //뒤로가기 버튼 생성
	   backButton = new JButton(back);
	   backButton.setBounds(10,10,50,50);
	   backButton.addActionListener(this);
	   backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {	//갖다댔을 떄
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				
			}
			@Override
			public void mouseExited(MouseEvent e) {		//뗐을 때
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				//back버튼은 메인화면으로 돌아가는 이벤트
				win.change("panel01");
			}
		});
	   backButton.setBorderPainted(false);			//버튼 테두리 없애기
	   backButton.setContentAreaFilled(false);		//버튼 영역 배경 표시 없애기
	   backButton.setFocusPainted(false);			//포커스 없애기
	   
	   //수정하기 버튼
	   modifyButton = new JButton(modify);
	   modifyButton.setBounds(430, 200, 50, 50);
	   modifyButton.addActionListener(this);
	   modifyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {	//갖다댔을 떄
				modifyButton.setIcon(modify2);
				modifyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {		//뗐을 때
				modifyButton.setIcon(modify);
				modifyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
	   modifyButton.setBorderPainted(false);		//버튼 테두리 없애기
	   modifyButton.setContentAreaFilled(false);	//버튼 영역 배경 표시 없애기
	   modifyButton.setFocusPainted(false);			//포커스 없애기
	   
	   //단어 입력창
	   inputW = new JTextField();
	   inputW.addActionListener(this);
	   inputW.setFont(font);
	   inputW.setBounds(10, 200, 345, 50);
	           
	   //단어 리스트
	   listW = new JTextArea();
	   listW.setBounds(90, 125, 200, 300);
	   listW.setEditable(false);
	   listW.setFont(font);
	   
	   //리스트 스크롤
	   listScroll = new JScrollPane(listW);
	   listScroll.setBounds(90, 128, 200, 300);
	       
	   // 정보
	   information = new JLabel();
	   information.setBounds(400, 19, 300, 30);
	   information.setFont(font);
	   
	   //패널, 레이아웃 부착
	   inputPanel.add(inputW);      	//추가 패널에 텍스트필드 부착
	   inputPanel.add(addButton);   	//추가 패널에 추가 버튼 부착
	   inputPanel.add(backButton);		//추가 패널에 뒤로가기 버튼 부착
	   inputPanel.add(modifyButton);	//추가 패널에 수정 버튼 부착
	   listPanel.add(listScroll);   	//리스트 패널에 리스트 에리어 부착
	   listPanel.add(sortButton);		//리스트 패널에 정렬 버튼 부착
	   listPanel.add(saveButton);		//리스트 패널에 저장 버튼 부착
	   notice.add(information);   		//알림 패널에 안내 레이블 부착
	   
	   add(inputPanel); // 왼쪽
	   add(listPanel); 	// 오른쪽
	   add(notice); 	// 하단
	   setVisible(true);
	}	

	//단어 검색해서 수정하는 메서드
	int search(String modifyW) {
		for(int j=0 ; j<word.size(); j++) { 
		    if(modifyW.equals(word.get(j)) ) { 
		    	findW = j;
		    	return 1;
		   }
	}
		return 0;
	}
	
	//단어 추가 이벤트
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// textField에서 Enter
        if(e.getSource() == inputW || e.getSource() == addButton) {
        	if(i==0) {
        		word.add(inputW.getText());
        		inputW.setText("");
        		listW.append(word.get(i) + " " + "\n");
        		information.setText("단어장에 추가했습니다♥");
        		i++;
        	} else if(i>0 && i<100) { 
        		for(int j=0; j<word.size(); j++) {
        			if(!word.contains(inputW.getText())){
        				word.add(inputW.getText());
        				listW.append(word.get(i) + " " + "\n");
        				information.setText("단어장에 추가했습니다♥");
        				i++;
        				inputW.setText("");
        				break;
        			}
        			information.setText("※이미 추가된 단어입니다※");   
        		}
        		inputW.setText("");
        	} else {
        		information.setText("단어장이 꽉 찼어요 ㅠ_ㅠ");
        	}
        }
		
		//정렬
		if(e.getSource() == sortButton) {
			Collections.sort(word);
			listW.setText("");
			for(i=0; i<word.size(); i++) {
				listW.append(word.get(i) + " \n");
				information.setText("정렬되었습니다♥");
			}
		}
		
		//단어장을 파일로 저장
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
				information.setText("내 단어장을 저장했습니다★");
			} catch(Exception fe){
				fe.printStackTrace();
			}
		}
		
		//뒤로가기 버튼
		if(e.getSource() == backButton) {
			//초기화면 패널 부착
		}
		
		//수정하기 버튼
		if(e.getSource() == modifyButton) {
			String modifyW = JOptionPane.showInputDialog("수정할 단어를 입력하세요!");  
			int a = search(modifyW);
			if(a==1) {	//같은 것이 있다면
				String reW = JOptionPane.showInputDialog("단어를 수정해주세요!"); 
				word.set(findW, reW);
				listW.append(word.get(findW) + "\n");
				information.setText("수정했습니다♥");
			} else if(a==0) {
				information.setText("※일치하는 단어가 없습니다※");
			}
		}
	}
}