import java.awt.Dimension;

import javax.swing.JFrame;

public class LeagueInvader {
	JFrame window;
	static final int WIDTH = 500;
	static final int HEIGHT = 800;
	GamePanel game;
	LeagueInvader(){
		window = new JFrame();
		game = new GamePanel();
	}
	public static void main(String[] args) {
		LeagueInvader invader = new LeagueInvader();
		invader.setup();
	}
	void setup() {
		window.add(game);
		window.setVisible(true);
		window.setSize(WIDTH, HEIGHT);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setPreferredSize(new Dimension(WIDTH, HEIGHT));
        window.pack();
        game.startGame();
        window.addKeyListener(game);
	}
}
