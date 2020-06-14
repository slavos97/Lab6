import java.awt.Color;
import java.awt.Graphics; 
import java.awt.Graphics2D; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; 
import java.util.ArrayList;
import javax.swing.JPanel; 
import javax.swing.Timer; 
 
@SuppressWarnings("serial") 
public class Field extends JPanel {    // ���� ������������������ ��������  
	private boolean paused;  // ������������ ������ �������� �����
	private boolean pausedMiniBalls;
	private ArrayList<BouncingBall> balls = new ArrayList<BouncingBall>(10); 
	//public boolean magnetizm = false;
	 
	private Timer repaintTimer = new Timer(10, new ActionListener() {  
		public void actionPerformed(ActionEvent ev) {     
			repaint();  
			}  
		}); 
	
	// ����������� ������ FIeld  
	public Field() {   // ���������� ���� ������� ���� �����   
		setBackground(Color.WHITE);   // ��������� ������   
		repaintTimer.start(); 
		}    // �������������� �� JPanel ����� ����������� ���������� 
	public void paintComponent(Graphics g) {   // ������� ������ ������, �������������� �� ������  
		super.paintComponent(g);   
		Graphics2D canvas = (Graphics2D) g;   // ��������������� ��������� ���������� �� ���� ����� �� ������  
		for (BouncingBall ball: balls) {   
			ball.paint(canvas);  
			}  
		}    // ����� ���������� ������ ���� � ������  
	public void addBall() {   //����������� � ���������� � ������ ������ ���������� BouncingBall   // ��� ������������� ���������, ��������, �������, �����  // BouncingBall ��������� ��� � ������������   
		balls.add(new BouncingBall(this)); 
		}   
	

 
 // ����� ������������������, �.�. ������ ���� ����� �����  // ������������ ���� ������ 
public synchronized void pause() {   // �������� ����� �����   
	paused = true;  }    // ����� ������������������, �.�. ������ ���� ����� �����  // ������������ ���� ������
public synchronized void pauseMiniBalls() {     
	pausedMiniBalls = true;  }  
public synchronized void resume() {   // ��������� ����� �����
	paused = false;   // ����� ��� ��������� ����������� ������
	pausedMiniBalls = false;
	notifyAll();  }    // ������������������ ����� ��������, ����� �� ��� ���������  // (�� ������� �� ����� �����?)
public synchronized void canMove(BouncingBall ball) throws InterruptedException {  
	if (paused) {    // ���� ����� ����� �������, �� �����, ��������  // ������ ������� ������, ��������   
		wait();   
		} 
	}
public synchronized void stopMiniBalls(BouncingBall ball,int radius) throws InterruptedException {  
	if (pausedMiniBalls && radius<=20) {    // ���� ����� ����� �������, �� �����, ��������  // ������ ������� ������, ��������   
		wait();   
		} 
	}

}

