import java.awt.*;
import java.util.*;

public class Bird extends Rectangle {	
	
	private static final long serialVersionUID = 1L;
	private int cord = 6; 
	public boolean isPressed = false;
	private ArrayList<Rectangle> tubes; 
	
	public Bird(int x, int y, ArrayList<Rectangle> tubes) {
		setBounds(x, y, 32, 32);
		this.tubes = tubes;
	}
	
	public void update() {
		if (isPressed == true) {
			y -= cord;
		}
		else 
			y += cord;
		
		for (int i = 0; i < tubes.size(); i++) {
			if(this.intersects(tubes.get(i))) {
					Flappy.room = new Room(60);
					tubes = Flappy.room.tubes;
					y= Flappy.SCREEN_HEIGHT / 2;
					Flappy.score = 0;
					break;
			}
			if (y >= Flappy.SCREEN_HEIGHT) {
				Flappy.room = new Room(60);
				tubes = Flappy.room.tubes;
				y= Flappy.SCREEN_HEIGHT / 2;
			}
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillOval(x, y, width, height);		
	}
}
