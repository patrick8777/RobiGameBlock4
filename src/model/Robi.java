package model;
import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;

/**
* Robi dients zur Verwaltung und zum Zeichnen von Robi-Objekten
*
*/
public class Robi{

  // Attribute definieren
  private int myColor;
  private int speed;
  public PVector position;
  private PShape robiForm; 
  private PApplet myWindow;
  private int score;
  
  
  /*
   * Puktestand vom Robi um 1 erhöhen
   */
  public void incrementScore() {
	  score++;
  }
  
  public int getScore() {
	  return score;
  }

  /**
   * Konstruktor
   * @param myWindow PApplet-Objekt mit Spielfeld
   * @param position initiale Position im Spielfeld
   * @param speed  initiale Geschwindigkeit
   * @param myColor  Farbe
   */
  public Robi(PApplet myWindow, PVector position, int speed, int myColor){
    // Parameterwerte an Attribute Ã¼bergeben
    this.position = position;
    this.speed = speed;
    this.myColor = myColor;
    this.myWindow = myWindow; // 
    robiForm = makeRobiShape();    
  }
  
  /**
  * creates a new shape object for robi
  *
  */
  PShape makeRobiShape(){
    PShape shapeGruppe = myWindow.createShape(PApplet.GROUP);
    PShape frame = myWindow.createShape(PApplet.RECT, -20, -10, 40, 20, 10);
    frame.setFill(myColor);
    PShape leftEye = myWindow.createShape(PApplet.ELLIPSE, -10,0,15,15);
    PShape rightEye = myWindow.createShape(PApplet.ELLIPSE, 10,0,15,15);
    leftEye.setFill(255);
    leftEye.setStroke(100);
    rightEye.setFill(255);
    rightEye.setStroke(100);
    PShape leftEyeP = myWindow.createShape(PApplet.ELLIPSE, -10,0,5,5);
    PShape rightEyeP = myWindow.createShape(PApplet.ELLIPSE, 10,0,5,5);
    leftEyeP.setFill(0);
    rightEyeP.setFill(0);
    shapeGruppe.addChild(frame);
    shapeGruppe.addChild(leftEye);
    shapeGruppe.addChild(rightEye);
    shapeGruppe.addChild(leftEyeP);
    shapeGruppe.addChild(rightEyeP);
    return shapeGruppe;
  }
  
  /**
  * draws robot at a given position
  * param p position-vector of robot
  */
  public void draw(){
    myWindow.shape(robiForm, position.x, position.y);
  }

  public void moveLeft(){
    position.x = position.x - speed;
  }

  public void moveRight(){
    position.x = position.x + speed;
  }

  public void moveUp(){
    position.y = position.y - speed;
  }

  public void moveDown(){
    position.y = position.y + speed;
  }

  public float getX() {
	// TODO Auto-generated method stub
	return position.x;
  }
  
  public float getY() {
		// TODO Auto-generated method stub
		return position.y;
  }
	
}