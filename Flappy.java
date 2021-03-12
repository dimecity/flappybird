import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;


public class Flappy extends Canvas implements Runnable, KeyListener{
	
	private static final long serialVersionUID = 1L; //keep eclipse happy 
	static final int SCREEN_WIDTH = 700;	
	static final int SCREEN_HEIGHT = 480;
	private boolean running = false;
	private Thread thread;
	public static double score = 0.0;
	public static Room room;
	public Bird bird;
	
	public Flappy() {
		setPreferredSize(new Dimension(Flappy.SCREEN_WIDTH, Flappy.SCREEN_HEIGHT));
		addKeyListener(this);
		room = new Room(60);
		bird = new Bird(100, Flappy.SCREEN_HEIGHT/2, room.tubes);
	}
		
	public synchronized void startGame() { 
		if(running) return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void gameOver() {
		if(!running) 
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Flappy Bird");
		Flappy flappy = new Flappy();
		frame.add(flappy);
		frame.setResizable(false);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);	
		frame.setVisible(true);		
		flappy.startGame();
	}	
	
	@Override
	public void run() {	
		long lastTime = System.nanoTime(); //Set the render time for the tubes and the bird
		double nanoSecond = 1000000000 / 60;
		double delta = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nanoSecond;
			lastTime = now;
			while (delta >= 1) {
				update();
				render();
				delta --;
			}			
		}
		gameOver();
	}
	
	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.cyan);
		g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		room.render(g);
		bird.render(g);
		g.setColor(Color.black);
		g.setFont(new Font("Calibri",Font.BOLD,19));
		g.drawString("Score: " + (int)score, 10, 20);
		g.dispose();
		bs.show();
	}
	
	private void update() {
		bird.update();
		room.update();
	}

	@Override
	public void keyTyped(KeyEvent e) {		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			bird.isPressed = true;
		}	
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			bird.isPressed = false;
		}			
	}	
}	

