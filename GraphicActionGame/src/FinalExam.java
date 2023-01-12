import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class FinalExam extends JFrame implements Runnable, ActionListener, KeyListener, MouseListener, MouseMotionListener {

	Thread th;
	JPanel upPanel;
	JButton keyBtn, mouseBtn;
	JLabel inputStatus;
	boolean status = true;
	boolean gameStart = false;
	int x, y;
	int charSelect=0;
	boolean cleanJFrame = false;
	ImageIcon heroImgIcon[] = new ImageIcon[4];
	Image heroImg[] = new Image[4];
	
	public FinalExam() {
	    // GUI
		setSize(500, 400);
	    setVisible(true);

		
	    //패널 생성하여 패널에 IP 주소 입력을 위한 텍스트필드와 연결 버튼 추가
	    upPanel = new JPanel();
	    
	    keyBtn = new JButton("키보드 입력");
	    upPanel.add(keyBtn);
	    
	    mouseBtn = new JButton("마우스 입력");
	    upPanel.add(mouseBtn);
	    
	    inputStatus = new JLabel("키보드 입력");
	    inputStatus.setForeground(Color.RED);
	    upPanel.add(inputStatus);
	    
	    // 생성된 패널을 보더레이아웃의 위쪽에 위치
	    add("North", upPanel);
	    setVisible(true);
		
		
		// Hero 객체 생성 
		for(int i=0; i<4; i++) {
			heroImgIcon[i] = new ImageIcon("./images/hero"+i+".png");
			heroImg[i] = heroImgIcon[i].getImage();
		}
	    
		keyBtn.addActionListener(this);
		mouseBtn.addActionListener(this);
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
	    
		x = 100;
		y = 200;

		th = new Thread(this);
		th.start();
	    
	}
	
	public void paint(Graphics g) {

		if(cleanJFrame) {
			g.setColor(Color.white);
			g.fillRect(0, 0, 500, 400);
			cleanJFrame = false;
		}
		else {
			g.setColor(Color.white);
			g.fillRect(0,60, 500, 400);
		}
		if (charSelect == 0) {
			charSelect = 1;
			g.drawImage(heroImg[charSelect], x, y, null);

		} else if (charSelect == 1) {
			charSelect = 0;
			g.drawImage(heroImg[charSelect], x, y, null);

		} else if (charSelect == 3) {
			charSelect = 3;
			g.drawImage(heroImg[charSelect], x, y, null);

		} else if (charSelect == 2) {
			charSelect = 2;
			g.drawImage(heroImg[charSelect], x, y+10, null);

		}

	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			repaint();
		}
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource()==keyBtn) {
			status = false;
			//cleanJFrame = true;
			this.requestFocus();
			inputStatus.setText("키보드 입력");
			System.out.println("Key:" + status);
		}
		else if(e.getSource()==mouseBtn) {
			status = true;
			//cleanJFrame = true;
			this.requestFocus();

			inputStatus.setText("마우스 입력");
			System.out.println("Mouse:"+status);

		}
		
	}
	

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
			if(status==false) return;
			
			this.x = e.getX();
			this.y = e.getY();
			repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(status==false) return;

		this.charSelect = 3;
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if(status==false) return;

		this.charSelect = 0;
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		status = true;
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		status = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//if(status==true) return;
		if(status==true) return;

		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			this.x += 5;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			this.x -= 5;
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			charSelect = 3;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			charSelect = 2;
		}
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//if(status==true) return;
		if(status==true) return;

		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			charSelect = 0;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			charSelect = 0;
		}

		repaint();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FinalExam().setTitle("키보드 마우스 입력");
	}

}
