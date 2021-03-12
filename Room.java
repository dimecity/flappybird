import java.awt.*;
import java.util.*;

public class Room {
	
	public ArrayList<Rectangle> tubes;
	private int time;
	private int currentTime = 0;
	private int density = 5;	//how many tubes in the frame 
	private Random random;
	private final int SPACE_TUBES = 150; 
	private final int WIDTH_TUBES = 64;
	
	public Room(int time) {
		tubes = new ArrayList<Rectangle>();
		this.time = time;
		random = new Random();
	}
	
	public void update() {
		currentTime++;	//like density, kinda, it currenttime reaches time in the constructor then a new tube will be spawned 
		if(currentTime == time) {
			currentTime = 0;
			
			int height1 = random.nextInt(Flappy.SCREEN_HEIGHT / 2);	//create random y cordinate of the tubes  each time 
			
			int y2 = height1 + SPACE_TUBES; 				//calculate the dimension of the other opposite tubes
			int height2 = Flappy.SCREEN_HEIGHT - y2;		
		
			tubes.add(new Rectangle(Flappy.SCREEN_WIDTH, 0, WIDTH_TUBES,height1)); //add them to the list 
			tubes.add(new Rectangle(Flappy.SCREEN_WIDTH, y2, WIDTH_TUBES, height2));
		}
		
		for(int i = 0; i <  tubes.size(); i++) {
			Rectangle rect = tubes.get(i); 	//new rectangle object 
			rect.x -= density;		//the spaces between the horizontal tubes 
			
			if(rect.x + rect.width <= 0) {	//remove the tubes so that the list won't be infinite 
				tubes.remove(i--);
				Flappy.score += 0.5;	//add score each time the bird passes the tubes, 0.5 but pass to int so it'll be 1
				continue;
			}
		}
	}
	
	public void render(Graphics g) {	//Draw the tubes 
		g.setColor(Color.green);
		
		for(int i = 0; i < tubes.size(); i++) {
			Rectangle rect = tubes.get(i);
			g.fillRect(rect.x, rect.y, rect.width, rect.height);
		}
	}	
}
