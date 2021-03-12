import java.awt.*;
import java.util.*;

public class Bird extends Rectangle {	
	
	private static final long serialVersionUID = 1L;
	private int cord = 6;		//initiallize the y cordinate at the start of the game 
	public boolean isPressed = false;
	private ArrayList<Rectangle> tubes; 
	
	public Bird(int x, int y, ArrayList<Rectangle> tubes) {		//set tubes' size
		setBounds(x, y, 32, 32);
		this.tubes = tubes;
	}
	
	public void update() {
		if (isPressed == true) {	//y cordinate will be changed when up arrow is press
			y -= cord;
		}
		else 
			y += cord;
		
		for (int i = 0; i < tubes.size(); i++) {
			if(this.intersects(tubes.get(i))) {	//if intersects score is 0, restart the game 
					Flappy.room = new Room(60);
					tubes = Flappy.room.tubes;
					y= Flappy.SCREEN_HEIGHT / 2;
					Flappy.score = 0;
					break;
			}
			if (y >= Flappy.SCREEN_HEIGHT) { 	//if the bird moves out of y bounds then restart the game too 
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
