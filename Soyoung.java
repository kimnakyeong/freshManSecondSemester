package gameTyping;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Soyoung extends JPanel implements ActionListener, Runnable {
	/*   static String[] word = {"apple", "banana", "mango", "cherry", "pencil", "phone",
	         "student", "teacher", "book", "bag"};
	*/ 
		static ArrayList<String> list = new ArrayList<String>();
	   
	   JPanel topPanel, wordPanel, inputPanel, gamePanel;
	   JLabel scoreLabel, randomString, imgLabel, timeLabel;
	   JTextField input;
	   ImageIcon icon1, icon2, icon3, icon4, icon5, icon6, icon7; 
	   ImageIcon wordBack, gameBack;
	   
	   int score; //점수 (=키cm)
	   int delayTime;
	   int age;
	   int timer;
	   
	   boolean check;
	   static String answer;
	   
	   Thread clock;
	   MediaTracker myTracker;
	   
	   Image icon1Img, icon2Img, icon3Img, icon4Img, icon5Img, icon6Img, icon7Img;
	   Image chgIcon1Img, chgIcon2Img, chgIcon3Img, chgIcon4Img, chgIcon5Img, chgIcon6Img, chgIcon7Img;
	   ImageIcon chgIcon1, chgIcon2, chgIcon3, chgIcon4, chgIcon5, chgIcon6;
	   
	   private int mouseX, mouseY;
	   private JLabel menuBar = new JLabel(new ImageIcon(Soyoung.class.getResource("../images/menuBar.png")));
	   private ImageIcon exitButtonBasicImage = new ImageIcon(Soyoung.class.getResource("../images/exitButtonBasic.png"));
	   private ImageIcon exitButtonEnteredImage = new ImageIcon(Soyoung.class.getResource("../images/exitButtonEntered.png"));
		
	   private JButton exitButton = new JButton(exitButtonBasicImage);

	   private ImageIcon backButtonEnteredImage = new ImageIcon(Soyoung.class.getResource("../images/soyoungBackButtonPressed.png"));
	   private ImageIcon backButtonBasicImage = new ImageIcon(Soyoung.class.getResource("../images/soyoungBackButton.png"));
		
	   private JButton backButton = new JButton(backButtonBasicImage);
	   
	   private JPanelTest win;
	   
	   public void init() throws IOException {
	      score = 55;
	      delayTime = 1000;
	      age = 0;
	      timer = 20;
	      
	      list.add("start");
	      
	      myTracker = new MediaTracker(this);
	      setLayout(new BorderLayout());
	    	exitButton.setBounds(920, 0, 30, 30);
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
					int result = JOptionPane.showConfirmDialog(null, "힝.. 벌써 가요?","오잉..?",
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
			
			add(exitButton);
	    // exitButton 만들기
	      
	      
	      //게임 윗 부분(설명, 타이틀 부분)
	      topPanel = new JPanel();
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
	 				setLocation(x - mouseX, y - mouseY);
	 			}
	 		});
	 	add(menuBar, BorderLayout.NORTH);
	      
	      //단어가 뜨는 화면 부분
	      wordPanel = new JPanel() {
	         public void paintComponent(Graphics g) {
	                g.drawImage(wordBack.getImage(), 0, 0, null);
	                setOpaque(false);
	                super.paintComponent(g);
	               }
	      };
	      
	      //단어가 뜨는 화면 배경
	      wordBack = new ImageIcon(Soyoung.class.getResource("../images/background.png"));
	      
	      //캐릭터가 있는 화면 배경
	      gameBack = new ImageIcon(Soyoung.class.getResource("../images/집1.png"));
	      
	      wordPanel.setPreferredSize(new Dimension(400, 540));
	      wordPanel.setLayout(new BorderLayout());
	      
	      timeLabel = new JLabel("");
	      timeLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
	      timeLabel.setForeground(Color.red);
	      wordPanel.add(timeLabel, BorderLayout.NORTH);
	      
	      randomString = new JLabel(getRandomString((int)(Math.random() * list.size())));
	      randomString.setHorizontalAlignment(JLabel.CENTER);
	      
	      randomString.setFont(new Font("맑은 고딕", Font.BOLD, 35));
	      wordPanel.add(randomString, BorderLayout.CENTER);
	      
	      add(wordPanel, BorderLayout.WEST);
	      
	      //점수, 답 입력 부분
	      inputPanel = new JPanel();
	      input = new JTextField(20);
	      scoreLabel = new JLabel(score + "cm(" + age + "세)");
	      scoreLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
	      
	      inputPanel.add(scoreLabel);
	      inputPanel.add(input);
	      
	      backButton.setVisible(true);
			backButton.setPreferredSize(new Dimension(30,30));
			backButton.setBorderPainted(false);
			backButton.setContentAreaFilled(false);
			backButton.setFocusPainted(false);
			backButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					backButton.setIcon(backButtonEnteredImage);
					backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					backButton.setIcon(backButtonBasicImage);
					backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
				@Override
				public void mousePressed(MouseEvent e) {
					//back버튼은 메인화면으로 돌아가는 이벤트
					
						win.change("panel01");
				}
			});
		inputPanel.add(backButton);
	      
	      add(inputPanel, BorderLayout.SOUTH);
	      
	      //그림 나타내는 부분
	      gamePanel = new JPanel() {
	         public void paintComponent(Graphics g) {
	                g.drawImage(gameBack.getImage(), 0, 0, null);
	                setOpaque(false);
	                super.paintComponent(g);
	               }
	      };
	      imgLabel = new JLabel();
	      
	      icon1 = new ImageIcon(Soyoung.class.getResource("../images/jj1.png"));
	      icon2 = new ImageIcon(Soyoung.class.getResource("../images/jj2.png"));
	      icon3 = new ImageIcon(Soyoung.class.getResource("../images/jj3.png"));
	      icon4 = new ImageIcon(Soyoung.class.getResource("../images/jj4.png"));
	      icon5 = new ImageIcon(Soyoung.class.getResource("../images/jj5.png"));
	      icon6 = new ImageIcon(Soyoung.class.getResource("../images/jj6.png"));
	      
	      icon1Img = icon1.getImage();
	      icon2Img = icon2.getImage();
	      icon3Img = icon3.getImage();
	      icon4Img = icon4.getImage();
	      icon5Img = icon5.getImage();
	      icon6Img = icon6.getImage();
	      
	      chgIcon1Img = icon1Img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
	      chgIcon2Img = icon2Img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
	      chgIcon3Img = icon3Img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
	      chgIcon4Img = icon4Img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
	      chgIcon5Img = icon5Img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
	      chgIcon6Img = icon6Img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
	      
	      chgIcon1 = new ImageIcon(chgIcon1Img);
	      chgIcon2 = new ImageIcon(chgIcon2Img);
	      chgIcon3 = new ImageIcon(chgIcon3Img);
	      chgIcon4 = new ImageIcon(chgIcon4Img);
	      chgIcon5 = new ImageIcon(chgIcon5Img);
	      chgIcon6 = new ImageIcon(chgIcon6Img);
	      
	      gamePanel.setBackground(Color.pink);
	      add(gamePanel, BorderLayout.CENTER);
	   }
	   
	   public void start() throws IOException {
		   FileReader fr;
			BufferedReader br;
			String str = "";
			try {
				fr = new FileReader("C:\\java\\word.txt");
				br = new BufferedReader(fr);
				String line;
				while((line = br.readLine())!=null) {
				str = str+line;}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			list = new ArrayList<String>();
			StringTokenizer strToken = new StringTokenizer(str, " ");
			while(strToken.hasMoreTokens()) {
				list.add(strToken.nextToken());
			}
		   
	      System.out.println("start!");
	      input.addActionListener(this);
	      changeImg();
	      
	      check = true;
	      clock = new Thread(this);
	      clock.start();
	   }
	   
	   public void stop() {
	      System.out.println("stop!");
	      if((clock != null) &&(clock.isAlive())) {
	         check = false;
	      }
	   }
	   
	   public void paint() {}
	   
	   public void scoreCheck() {
	      if ((score > 71) && (score < 129)) {
	         delayTime = 1000 - ((score - 70) * 16);
	      }
	   }
	   
	   public void changeImg() {
	      if (score < 71) {
	         age = 0;
	         imgLabel.setIcon(chgIcon1);
	      } else if (score < 87) {
	         age = 1;
	         gameBack = new ImageIcon(Soyoung.class.getResource("../images/집2-1.png"));
	         imgLabel.setIcon(chgIcon2);
	      } else if (score < 103) {
	         age = 2;
	         gameBack = new ImageIcon(Soyoung.class.getResource("../images/집3.png"));
	         imgLabel.setIcon(chgIcon3);
	      } else if (score < 119) {
	         age = 3;
	         gameBack = new ImageIcon(Soyoung.class.getResource("../images/집4.png"));
	         imgLabel.setIcon(chgIcon4);
	      } else if (score < 135) {
	         age = 4;
	         gameBack = new ImageIcon(Soyoung.class.getResource("../images/집5.png"));
	         imgLabel.setIcon(chgIcon5);
	      } else if (score < 151) {
	         age = 5;
	         gameBack = new ImageIcon(Soyoung.class.getResource("../images/집6-1.png"));
	         imgLabel.setIcon(chgIcon6);
	      } else if (score == 151) {
	         check = false;
	         timer = 20;
	         gameBack = new ImageIcon(Soyoung.class.getResource("../images/end.jpg"));
	         imgLabel.setVisible(false);
	      }
	      
	      repaint();
	      
	      gamePanel.setLayout(new BorderLayout());
	      imgLabel.setHorizontalAlignment(JLabel.CENTER);
	      gamePanel.add(imgLabel, BorderLayout.SOUTH);
	      scoreLabel.setText(score + "cm(" + age + "세)");
	      
	   }
	   
	   public void nextWord() {
	      timer = 20;
	      
	      randomString.setText(getRandomString((int)(Math.random() * list.size())));
	   }
	   
	   static public String getRandomString(int length) {
	      StringBuffer buffer = new StringBuffer();
	      Random r = new Random();
	      
	      for (int i = 0; i < 1; i++) {
	         answer = list.get(r.nextInt(list.size()));
	         buffer.append(answer);
	      }
	      
	      return buffer.toString();
	   }
	   
	   public Soyoung(JPanelTest win) throws IOException {
	      this.win = win;
	      super.setSize(960, 540);
	      this.init();
	      this.start();
	      super.setVisible(true);
	   }
	   
	   @Override
	   public void run() {
	      while(check) {
	         for (timer = 20; timer >= 0; timer--) {      
	            scoreCheck();
	            try {
	               clock.sleep(delayTime);
	               timeLabel.setText("" + timer);
	            } catch (InterruptedException e) {}
	         }
	         
	         if (check) {
	            nextWord();
	         }
	      }
	   }

	   @Override
	   public void actionPerformed(ActionEvent e) {
	      if(check != false) {
	         if(e.getSource() == input) {         
	            for (int i = 0; i < list.size(); i++) {
	               if (answer.equals(input.getText())) {
	                  score += 2;
	                  scoreLabel.setText(score + "cm(" + age + "세)");
	                  input.setText("");
	                  nextWord();
	               } else {
	                  input.setText("");
	               }
	            }
	         }
	      }
	      
	      changeImg();
	      
	      if (check == false) {
	         input.setText("♥♥♥♥♥♥");
	         randomString.setFont(new Font("",Font.BOLD, 25));
	         randomString.setText("짱구가 6살이 되었습니다!");
	         scoreLabel.setText(score + "cm(6세)");
	      }
	   }
	   
	  
	}