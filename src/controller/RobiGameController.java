package controller;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import model.Highscore;
import model.Robi;
import model.Star;
import processing.core.PApplet;
import processing.core.PVector;
import javax.swing.JOptionPane;

/*
 * @author Aco Aleks & Patrick
 */

public class RobiGameController extends PApplet{
	/*
	 * mögliche Zustände definieren
	 */
	enum GameState {Start, Gaming, End}
	
	GameState currentGameState = GameState.Start;
	Robi r1; 
	ArrayList<Robi> enemies;
	ArrayList<Star> stars;
	boolean hasGameEnded = false;
	String nickname = "";
	HighscoreController controller = new HighscoreController();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("juhu, I'm running");
		PApplet.main("controller.RobiGameController");
		
	}
	
	/**
	 * setzt einmalige Einstellung beim Programmstart
	 */
	public void setup() {
		
		frameRate(100);
		startGame();
	}
	
	
	/**
	 * Spiel-Objekte erstellen
	 */
	private void startGame() {
		r1 = new Robi(this, new PVector(100,100), 10, 0xFF00FF00);
		Random r = new Random();
		
		enemies = new ArrayList<>();
		for (int i=0; i < 3; i ++) {
			enemies.add(new Robi(this, 
					new PVector(r.nextInt(width), r.nextInt(height)),
					1, 0xFFFF0000
					));
		}
		/**
		 * Spiel-Objekte Sterne erstellen
		 */
		stars = new ArrayList<>();
		for (int i=0; i < 10; i ++) {
			stars.add(new Star(
					new PVector(r.nextInt(width), r.nextInt(height)),
					this
					));
		}
	}
	
	/**
	 * Definiert die Fenstergrösse
	 */
	public void settings() {
		size(800,600);
		
	}
	/**
	 * Hier läuft das Spiel
	 * 3Phasen vom Spiel
	 */
	public void draw() {
		switch (currentGameState) {
		case Gaming: drawGaming(); 
		break;
		
		case Start: drawStart();
		break;
		
		case End: drawEnd();
		break;
		}
	}
	
	/**
	 * Definiert Spiel Startbildschirm
	 */
	public void drawStart() {
		background(0);
		textSize(50);
		//NickName=JOptionPane.showInputDialog(null,"Hello, Wie ist dein Nickname.");
		text("Willkommen im Spiel", width/2-200, height/2);
		textSize(25);
		text("Press SPACE to Start",width/2-10, height/2+80 );
		
	}
	
	/**
	 * Definiert Spiel Ende Bildschirm
	 */
	public void drawEnd() {
		background(0);
		textSize(50);
		text("Das Spiel is fertig", width/2-200, height/2);
		int playerHighscore = r1.getScore();
		int enemiesHighscore = 0;
		
		if (hasGameEnded == false) 
		{
			nickname = JOptionPane.showInputDialog("Nickname eingeben:");
			Highscore playerScore = new Highscore(nickname, playerHighscore);
			controller.addHighscore(playerScore);
			controller.writeHighscores();
		}
		
		for (Robi r: enemies) 
		{
			enemiesHighscore += r.getScore();
		}
		
		if (playerHighscore > enemiesHighscore)
		{
			text(nickname + "hat gewonnen: " + playerHighscore, width/2-200, 100);
		}
		else {
			text("Gegner hat gewonnen: " + enemiesHighscore, width/2-250, 150);  
		}	
		
		text("Highscore: "+ controller.readFile(), width/2-250, 450);
		hasGameEnded = true;
	}
	
	/**
	 * Definiert Spiel spielen
	 */
	public void drawGaming() {
		background(0);
		r1.draw();
		for (Robi r: enemies) {
			r.draw();
			ki(r, stars);
			int i = 0;
			while (i < stars.size()) {
				Star s = stars.get(i);
			if (checkCollision(r, s)) {
				r.incrementScore();
				stars.remove(s);
				System.out.println(r.getScore());
			}
				else { i++;
				}
			}
			text("HIGHSCORE R : " + r.getScore(), 10,60);
		}
		
		for (Star s: stars) {
			s.draw();
		}
		
		int i = 0;
		while (i < stars.size()) {
			Star s = stars.get(i);
		if (checkCollision(r1, s)) {
			r1.incrementScore();
			stars.remove(s);
			System.out.println(r1.getScore());
		}
			else { i++;
			}
		}

		textSize(30);
		text("HIGHSCORE R1 : " + r1.getScore(), 10,30);
		checkStars();
		
		
	}
	
	public void keyPressed() {
		switch(currentGameState) {
		case Start: keyPressedStart();break;
		case Gaming: keyPressedGaming();break;
		}
	}
	
	
	/**
	 * Start Taste wird definiert
	 */
	public void keyPressedStart() {
		if(key == ' ') {
			currentGameState = GameState.Gaming;
		}
	}
	
	/**
	 * Gaming Tasten werden defniniert für die bewegung
	 */
	public void keyPressedGaming() {
		switch (keyCode){
	     case UP:
	    	 if (r1.getY()>0)
	    		 r1.moveUp();
	       break;
	     case DOWN:
	    	 if (r1.getY()<height)
	    		 r1.moveDown();
	       break;
	     case LEFT:
	    	 if (r1.getX()>0)
	    		 r1.moveLeft();
	       break;
	     case RIGHT:
	    	 if (r1.getX() < width)
	    		 r1.moveRight();
	        break;
	       
	   }
	}
	
	/**
	 * prüft, ob ein Robi einen Stern berührt 
	 * @param r: Robi-Objekt
	 * @param s: Stern-Objekt
	 * @return true wenn Abstand zwischen Robi und Stern klein ist
	 */
	private boolean checkCollision(Robi r, Star s) {
		if (r.position.dist(s.position) < 20) {
			return true;
		}
		return false;
	}
	
	
	/**
	 * Kontollcheck ob noch Sterne im Spielfeld sind, Falls nicht Gamestatus Ende
	 */
	public void checkStars() {
		if (stars.size()== 0) {
			currentGameState = GameState.End;
		}
	}
	
	
	
	/**
	 * Eine KI Methode, die Gegner selbsst zum Ziel steuert
	 * 
	 * @param r
	 *            - Robi, welcher gesteuert wird
	 * @param starList
	 *            - ArrayList aller Sterne
	 */
	private void ki(Robi r, ArrayList<Star> stars) {
		float closestDistance = 9999;
		PVector targetPosition = new PVector(0, 0);
		for (Star s: stars) {
			if (r.position.dist(s.position) < closestDistance) {
				closestDistance = r.position.dist(s.position);
				targetPosition = s.position;
			}
		}
			if((r.position.x - targetPosition.x) > 10) {
				r.moveLeft();
				return;
			}
			if ((r.position.x - targetPosition.x) < -10) {
				r.moveRight();
				return;
			}
			if ((r.position.y - targetPosition.y) > 10) {
				r.moveUp();
				return;
			}
			if ((r.position.y - targetPosition.y) < -10) {
				r.moveDown();
				return;
			}		
	}

}
