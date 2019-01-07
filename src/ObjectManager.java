import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class ObjectManager {
	Rocketship rocket;
	ArrayList<Projectile> projectile = new ArrayList<Projectile>();
	ArrayList<Alien> alien = new ArrayList<Alien>();
	long enemyTimer = 0;
	int enemySpawnTime = 1000;
	int score = 0;
	ObjectManager(Rocketship rocket){
		this.rocket = rocket;
	}
	int getScore() {
		return score;
	}
	void update() {
		rocket.update();
		for (Projectile projectile : projectile) {
			projectile.update();
		}
		for (Alien alien : alien) {
			alien.update();
		}

	}
	void draw(Graphics g) {
		rocket.draw(g);
		for (Projectile projectile : projectile) {
			projectile.draw(g);
		}
		for (Alien alien : alien) {
			alien.draw(g);
		}

	}
	void addProjectile(Projectile projectile) {
		this.projectile.add(projectile);
	}
	void addAlien(Alien alien) {
		this.alien.add(alien);
	}
	public void manageEnemies(){
        if(System.currentTimeMillis() - enemyTimer >= enemySpawnTime){
                addAlien(new Alien(new Random().nextInt(LeagueInvader.WIDTH), 0, 50, 50));
                enemyTimer = System.currentTimeMillis();
        }
	}
	void purgeObjects() {
		for (int i = projectile.size() -1; i >= 0; i--) {
			if(projectile.get(i).isAlive == false) {
				projectile.remove(i);
			}
		}
		for (int j = alien.size() - 1; j >= 0; j--) {
			if(alien.get(j).isAlive == false) {
				alien.remove(j);
				
			}
		}
	}
	void checkCollision() {
		for (Alien alien : alien) {
			if(rocket.collisionBox.intersects(alien.collisionBox)){
                rocket.isAlive = false;
        }
		for (Projectile projectile : projectile) {
				if(projectile.collisionBox.intersects(alien.collisionBox)){
	                alien.isAlive = false;
	                score++;
	        }
		}
	}
	}
}
