import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener{
	Timer time;
	Rocketship rocket;
	ObjectManager objectManager;
	Font titleFont;
	Font pressEnter;
	Font pressSpace;
	Font gameOver;
	Font enemies;
	Font restart;
	final int MENU_STATE = 0;
	final int GAME_STATE = 1;
	final int END_STATE = 2;
	int currentState =  MENU_STATE;
	//hi Keith
	GamePanel(){
		time = new Timer(1000/60, this);
		rocket = new Rocketship(250, 700, 50, 50);
		objectManager = new ObjectManager(rocket);
		titleFont = new Font("Arial", Font.PLAIN, 48);
		pressEnter = new Font("Arial",Font.BOLD, 24);
		pressSpace = new Font("Arial", Font.BOLD, 24);
		gameOver = new Font("Arial", Font.PLAIN, 48);
		enemies = new Font("Arial", Font.BOLD, 24);
		restart = new Font("Arial", Font.BOLD, 24);
	}
	void startGame() {
		time.start();
	}
	void updateMenuState() {
		
	}
	void updateGameState() {
		objectManager.update();
		objectManager.manageEnemies();
		objectManager.checkCollision();
		objectManager.purgeObjects();
		if(rocket.isAlive == false) {
			currentState = END_STATE;
		}
	}
	void updateEndState() {
		
	}
	void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, LeagueInvader.WIDTH, LeagueInvader.HEIGHT);    
		g.setFont(titleFont);
		g.setColor(Color.yellow);
      	g.drawString("LEAGUE INVADERS", 20, 200);
      	g.setFont(pressEnter);
      	g.drawString("Press ENTER to start", 140, 400);
      	g.setFont(pressSpace);
      	g.drawString("Press SPACE for instructions", 110, 600);
      	}
	void drawGameState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, LeagueInvader.WIDTH, LeagueInvader.HEIGHT);  
		objectManager.draw(g);
	}
	void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, LeagueInvader.WIDTH, LeagueInvader.HEIGHT); 
		g.setFont(gameOver);
		g.setColor(Color.BLACK);
		g.drawString("Game Over", 120, 200);
		g.setFont(enemies);
		g.drawString("You killed" + objectManager.getScore() +" enemies", 120, 400);
		g.setFont(restart);
		g.drawString("Press ENTER to restart", 120, 600);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		repaint();
		 if(currentState == MENU_STATE){
			 updateMenuState();
		 }
		 else if(currentState == GAME_STATE){
             updateGameState();
		 }
		 else if(currentState == END_STATE){
             updateEndState();
     }
	}
	
	@Override
	public void paintComponent(Graphics g){
		if(currentState == MENU_STATE){
			 drawMenuState(g);
		 }
		 else if(currentState == GAME_STATE){
            drawGameState(g);
		 }
		 else if(currentState == END_STATE){
            drawEndState(g);
    }
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("keyTyped");
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		//menu/game/end states
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			if(currentState == END_STATE){
                currentState = MENU_STATE;
			}
			else if(currentState == MENU_STATE) {
				currentState = GAME_STATE;
			}
			else if(currentState == GAME_STATE) {
				currentState = END_STATE;
			}
		}
		//rocket
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			rocket.horizontalSpeed = 5;
		}
		else if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			rocket.horizontalSpeed = -5;
		}
		else if(e.getKeyCode()==KeyEvent.VK_UP) {
			rocket.verticalSpeed = -5;
		}
		else if(e.getKeyCode()==KeyEvent.VK_DOWN) {
			rocket.verticalSpeed = 5;
		}
		//projectile
		if(e.getKeyCode()==KeyEvent.VK_SPACE) {
			 objectManager.addProjectile(new Projectile(rocket.x+20, rocket.y, 10, 10));
		}

	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_RIGHT || e.getKeyCode()==KeyEvent.VK_LEFT) {
			rocket.horizontalSpeed = 0;
		}
		else if(e.getKeyCode()==KeyEvent.VK_UP || e.getKeyCode()==KeyEvent.VK_DOWN) {
			rocket.verticalSpeed = 0;
		}
		
		
	}
	              
}
