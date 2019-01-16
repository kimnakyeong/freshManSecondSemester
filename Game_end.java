package gameTyping;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Game_end extends JPanel {
	
	private static final int SCREEN_WIDTH = 960;
	private static final int SCREEN_HEIGHT = 540;
	private Image screenImage;
	private Graphics screenGraphic;
	
	
	private ImageIcon exitButtonEnteredImage = new ImageIcon(Game_end.class.getResource("../images/exitButtonEntered.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(Game_end.class.getResource("../images/exitButtonBasic.png"));
	
	private Image background = new ImageIcon(Game_end.class.getResource("../images/zzang92.jpg")).getImage();
	private JLabel menuBar = new JLabel(new ImageIcon(Game_end.class.getResource("../images/menuBar.png")));
	
	private JButton exitButton = new JButton(exitButtonBasicImage);
	
	private int mouseX, mouseY;
	
	
	private ImageIcon GameStartEntered = new ImageIcon(Game_end.class.getResource("../images/GameStartEntered.png"));
	private ImageIcon GameStart = new ImageIcon(Game_end.class.getResource("../images/GameStart.png"));
	private ImageIcon GameWayEntered = new ImageIcon(Game_end.class.getResource("../images/GameWayEntered.png"));
	private ImageIcon GameWay = new ImageIcon(Game_end.class.getResource("../images/GameWay.png"));
	private ImageIcon backButtonEnteredImage = new ImageIcon(Game_end.class.getResource("../images/backButtonEntered.png"));
	private ImageIcon backButtonBasicImage = new ImageIcon(Game_end.class.getResource("../images/backButtonBasic.png"));
	
	private ImageIcon storeButtonEnteredImage = new ImageIcon(Game_end.class.getResource("../images/mainAddButtonPressed.png"));
	private ImageIcon storeButtonBasicImage = new ImageIcon(Game_end.class.getResource("../images/mainAddButton.png"));
	
	
	private JButton startButton = new JButton(GameStart);
	private JButton quitButton = new JButton(GameWay);
	private JButton backButton = new JButton(backButtonBasicImage);
	private JButton storeButton = new JButton(storeButtonBasicImage); // 저장 버튼
	
	private JPanelTest win;
	
	public Game_end(JPanelTest win) {
		this.win = win;
		setSize(this.SCREEN_WIDTH, this.SCREEN_HEIGHT);
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);
		
		
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
		                  Thread.sleep(100); // 0.1초 후에 스레드가 문을 닫는다.
		               } catch(InterruptedException ex) {
		                  ex.printStackTrace();
		               }
		               System.exit(0);
		            }else {
		               
		            }
		            
			}
		});
		
		add(exitButton);
		
		
		// 게임시작 버튼
					startButton.setBounds(700, 250, 200, 100);
					startButton.setBorderPainted(false);
					startButton.setContentAreaFilled(false);
					startButton.setFocusPainted(false);
					startButton.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							startButton.setIcon(GameStartEntered);
							startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
							Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
							buttonEnteredMusic.start();
						}
						@Override
						public void mouseExited(MouseEvent e) {
							startButton.setIcon(GameStart);
							startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
						}
						@Override
						public void mousePressed(MouseEvent e) {
							//게임 시작 이벤트 (추가해야함)
							win.change("panel02");
						}
					});
/*					startButton.addActionListener(new MyActionListener());*/
					add(startButton);
					
					storeButton.setBounds(700, 395, 200, 100);
					storeButton.setBorderPainted(false);
					storeButton.setContentAreaFilled(false);
					storeButton.setFocusPainted(false);
					storeButton.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							storeButton.setIcon(storeButtonEnteredImage);
							storeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
							Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
							buttonEnteredMusic.start();
						}
						@Override
						public void mouseExited(MouseEvent e) {
							storeButton.setIcon(storeButtonBasicImage);
							storeButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
						}
						@Override
						public void mousePressed(MouseEvent e) {
							//게임 시작 이벤트 (추가해야함)
							win.change("panel03");
						}
					});
/*					startButton.addActionListener(new MyActionListener());*/
					add(storeButton);
					
					
					// quit 버튼이 아니고 사실 게임 방법 버튼..
					quitButton.setBounds(700, 330, 200, 100);
					quitButton.setBorderPainted(false);
					quitButton.setContentAreaFilled(false);
					quitButton.setFocusPainted(false);
					quitButton.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							quitButton.setIcon(GameWayEntered);
							quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
							Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
							buttonEnteredMusic.start();
						}
						@Override
						public void mouseExited(MouseEvent e) {
							quitButton.setIcon(GameWay);
							quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
						}
						@Override
						public void mousePressed(MouseEvent e) {
							//게임 시작 이벤트 (추가해야함)
							startButton.setVisible(false);
							quitButton.setVisible(false);
							storeButton.setVisible(false);
							background = new ImageIcon(Game_end.class.getResource("../images/howto.png")).getImage();
							backButton.setVisible(true);
						}
					});
					add(quitButton);	
		
		backButton.setVisible(false);
		backButton.setBounds(20, 50, 100, 100);
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
				backMain();
			}
		});
		add(backButton);
					
					
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
		
		
		
		/*super(title);
		this.setLocation(new Point(350, 350));
		this.setVisible(true);
		this.setPreferredSize(new Dimension(960, 540));
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().add(new MyPanel1());*/
		
		Music introMusic = new Music("GameMusic1.mp3", true); // true이기 때문에 직접 종료시키기 전까지는 계속 실행됨.
		introMusic.start();
		
				
		
		
	}
	
	public void paint(Graphics g) {
		screenImage = createImage(this.SCREEN_WIDTH, this.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, this);
	}
	
	public void screenDraw(Graphics g) {
		g.drawImage(background, 0, 0, this); // image를 background 에 담아서 screenImage에서 draw 해준다.
		paintComponents(g); // drawimage로 그리는 방법, paintComponents 로 그리는 방법으로 나뉜다.
		this.repaint();
	}
	
	public void backMain() {
		background = new ImageIcon(Game_end.class.getResource("../images/zzang92.jpg")).getImage();
		startButton.setVisible(true);
		quitButton.setVisible(true);
		storeButton.setVisible(true);
		backButton.setVisible(false);
	}
	
	/*class MyActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			win.change("panel02");
		}
		
	}*/
}


