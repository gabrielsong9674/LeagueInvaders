import java.awt.Color;
import java.awt.Graphics;

public class Rocketship extends GameObject {
	int horizontalSpeed;
	int verticalSpeed;
	Rocketship(int x, int y, int width, int height){
		super(x, y, width, height);
		horizontalSpeed = 0;
		verticalSpeed = 0;
	}
	void update() {
		x += horizontalSpeed;
		y += verticalSpeed;
	}
	void draw(Graphics g) {
		 g.setColor(Color.BLUE);
	     g.fillRect(x, y, width, height);
	}
	
}
